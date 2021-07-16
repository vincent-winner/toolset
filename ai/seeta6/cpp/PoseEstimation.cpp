#include <string>
#include <seeta/PoseEstimator.h>
#include <seeta/CFaceInfo.h>

#include <io_vincentwinner_toolset_ai_seeta6jni_PoseEstimation.h>
#include "JFieldStruct.h"
#include "Util.h"
#include "ClassConvertUtil.h"

#define LOG printf

using namespace seeta::v6;

static bool MODEL_LOADED = false;

seeta::PoseEstimator* PE = NULL;

JNIEXPORT void JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_PoseEstimation_loadModel
(JNIEnv* env, jobject obj, jstring modelPath)
{
	char* modelPathP = jstringToChar(env, modelPath);
	seeta::ModelSetting setting;
	setting.append(modelPathP);
	PE = new seeta::PoseEstimator(setting);
	MODEL_LOADED = true;
}

JNIEXPORT jobject JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_PoseEstimation_estimationPose
(JNIEnv* env, jobject obj, jobject img, jobject seetaRect)
{
	if (!MODEL_LOADED)
	{
		LOG("人脸姿态估计模型 \"face_detector.csta\" 未加载\n");
		return NULL;
	}
	SeetaImageData* image = NULL;
	SeetaRect* faceRect = NULL;
	jclass clazz = NULL;
	jobject jSeetaAngle = NULL;
	float yaw, pitch, roll;
	try {
		image = toSeetaImageData(env,img);
		faceRect = toSeetaRect(env,seetaRect);
		JSeetaAngle jSeetaAngleDef = getJSeetaAngle(env);
		clazz = env->FindClass(jSeetaAngleDef.className);
		jSeetaAngle = newJObject(env, jSeetaAngleDef);
		PE->Estimate(*image,*faceRect,&yaw,&pitch,&roll);
		env->SetFloatField(jSeetaAngle, jSeetaAngleDef.yaw, yaw);
		env->SetFloatField(jSeetaAngle, jSeetaAngleDef.pitch, pitch);
		env->SetFloatField(jSeetaAngle, jSeetaAngleDef.roll, roll);
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
	return jSeetaAngle;
}
