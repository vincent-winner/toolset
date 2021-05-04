#include <string>
#include <seeta/FaceLandmarker.h>

#include "io_vincentwinner_toolset_ai_seeta6jni_FaceLandmark.h"
#include "JFieldStruct.h"
#include "Util.h"
#include "ClassConvertUtil.h"

#define LOG printf

static bool MODEL_LOADED = false;

seeta::FaceLandmarker* FL = NULL;

JNIEXPORT void JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceLandmark_loadModel
(JNIEnv* env, jobject obj, jstring modelPath)
{
	char* modelPathP = jstringToChar(env, modelPath);
	seeta::ModelSetting setting;
	setting.append(modelPathP);
	FL = new seeta::FaceLandmarker(setting);
	MODEL_LOADED = true;
}

JNIEXPORT jobjectArray JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceLandmark_mark
(JNIEnv* env, jobject obj, jobject imageData, jobject face)
{
	if (!MODEL_LOADED)
	{
		LOG("人脸关键点检测模型 \"face_landmarker_pts5.csta\" 未加载\n");
		return NULL;
	}
	SeetaImageData* image = NULL;
	SeetaRect* faceRect = NULL;
	jclass clazz = NULL;
	jobjectArray jPointFArray = NULL;
	try {
		image = toSeetaImageData(env, imageData);
		faceRect = toSeetaRect(env, face);
		std::vector<SeetaPointF> resultVector = FL->mark(*image,*faceRect);
		if (resultVector.size() > 0)
		{
			JSeetaPointF jpoint = getJSeetaPointF(env);
			clazz = env->FindClass(jpoint.className);
			jPointFArray = env->NewObjectArray(resultVector.size(), clazz, NULL);
			for (int i = 0; i < resultVector.size(); i++)
			{
				jobject jpointObj = newJObject(env, jpoint);
				env->SetDoubleField(jpointObj, jpoint.x, resultVector[i].x);
				env->SetDoubleField(jpointObj, jpoint.y, resultVector[i].y);
				env->SetObjectArrayElement(jPointFArray, i, jpointObj);
				env->DeleteLocalRef(jpointObj);
			}
		}
	}
	catch (std::exception& e) {
		LOG("JNI Exception: %s", e.what());
	}
	if (image != NULL)
	{
		delete[] image->data;
		image->data = NULL;
		delete[] image;
		image = NULL;
	}
	if (faceRect != NULL)
	{
		delete[] faceRect;
		faceRect = NULL;
	}
	if (clazz)
	{
		env->DeleteLocalRef(clazz);
	}
	return jPointFArray;
}