#include <string>
#include <seeta/FaceDetector.h>
#include <seeta/CFaceInfo.h>

#include "io_vincentwinner_toolset_ai_seeta6jni_FaceDetector.h"
#include "JFieldStruct.h"
#include "Util.h"
#include "ClassConvertUtil.h"

#define LOG printf

using namespace seeta::SEETA_FACE_DETECTOR_NAMESPACE_VERSION;

static bool MODEL_LOADED = false;

seeta::FaceDetector* FD = NULL;

JNIEXPORT void JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceDetector_loadModel
(JNIEnv* env, jobject obj, jstring modelPath)
{
	char* modelPathP = jstringToChar(env,modelPath);
	seeta::ModelSetting setting;
	setting.append(modelPathP);
	FD = new seeta::FaceDetector(setting);
	MODEL_LOADED = true;
}

JNIEXPORT jobjectArray JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceDetector_detect
(JNIEnv* env, jobject obj, jobject seetaImageData)
{
	if (!MODEL_LOADED)
	{
		LOG("人脸检测模型 \"face_detector.csta\" 未加载\n");
		return NULL;
	}
	SeetaImageData* image = NULL;
	jclass clazz = NULL;
	jobjectArray jFaceInfoArray = NULL;
	try {
		image = toSeetaImageData(env,seetaImageData);
		SeetaFaceInfoArray detectResultArray = FD->detect(*image);
		if (detectResultArray.size > 0)
		{
			JSeetaFaceInfo jface_info = getJSeetaFaceInfo(env);
			clazz = env->FindClass(jface_info.className);
			jFaceInfoArray = env->NewObjectArray(detectResultArray.size,clazz,NULL);
			for (int i = 0; i < detectResultArray.size; i++)
			{
				jobject faceinfo = newJObject(env,jface_info);
				env->SetIntField(faceinfo, jface_info.x,detectResultArray.data[i].pos.x);
				env->SetIntField(faceinfo, jface_info.y, detectResultArray.data[i].pos.y);
				env->SetIntField(faceinfo, jface_info.width, detectResultArray.data[i].pos.width);
				env->SetIntField(faceinfo, jface_info.height, detectResultArray.data[i].pos.height);
				env->SetFloatField(faceinfo,jface_info.score, detectResultArray.data[i].score);
				env->SetObjectArrayElement(jFaceInfoArray,i,faceinfo);
				env->DeleteLocalRef(faceinfo);
			}
		}
	}catch (std::exception &e) {
		LOG("JNI Exception: %s", e.what());
	}
	if (image != NULL)
	{
		delete[] image->data;
		image->data = NULL;
		delete[] image;
		image = NULL;
	}
	if (clazz)
	{
		env->DeleteLocalRef(clazz);
	}
	return jFaceInfoArray;
}