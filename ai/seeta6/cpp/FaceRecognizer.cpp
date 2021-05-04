#include <string>
#include <seeta/FaceLandmarker.h>
#include <seeta/FaceRecognizer.h>

#include "io_vincentwinner_toolset_ai_seeta6jni_FaceRecognizer.h"
#include "JFieldStruct.h"
#include "Util.h"
#include "ClassConvertUtil.h"

#define LOG printf

static bool MODEL_LOADED = false;

seeta::FaceRecognizer* FR = NULL;

JNIEXPORT void JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceRecognizer_loadModel
(JNIEnv* env, jobject obj, jstring modelPath)
{
	char* modelPathP = jstringToChar(env, modelPath);
	seeta::ModelSetting setting;
	setting.append(modelPathP);
	FR = new seeta::FaceRecognizer(setting);
	MODEL_LOADED = true;
}

JNIEXPORT jfloatArray JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceRecognizer_extractFaceFeature
(JNIEnv* env, jobject obj, jobject img, jobjectArray landmarks)
{
	if (!MODEL_LOADED)
	{
		LOG("人脸识别模型 \"face_recognizer.csta\" 未加载\n");
		return NULL;
	}
	SeetaImageData* image = NULL;
	SeetaPointF* pointfs = NULL;
	jfloatArray jFeaturesArray = NULL;
	int featureSize = FR->GetExtractFeatureSize();
	float* features = new float[featureSize];
	try {
		image = toSeetaImageData(env,img);
		pointfs = toSeetaPointFArray(env,landmarks);
		bool success = FR->Extract(*image,pointfs,features);
		if (!success || features == NULL)
		{
			return NULL;
		}
		jFeaturesArray = env->NewFloatArray(featureSize);
		env->SetFloatArrayRegion(jFeaturesArray,0, featureSize,features);
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
	if (pointfs != NULL)
	{
		delete[] pointfs;
		pointfs = NULL;
	}
	return jFeaturesArray;
}

JNIEXPORT jfloat JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceRecognizer_compare
(JNIEnv* env, jobject obj, jfloatArray feature1, jfloatArray feature2)
{
	if (!MODEL_LOADED)
	{
		LOG("人脸识别模型 \"face_recognizer.csta\" 未加载\n");
		return NULL;
	}
	jfloat* feature1Array = env->GetFloatArrayElements(feature1,JNI_FALSE);
	jfloat* feature2Array = env->GetFloatArrayElements(feature2,JNI_FALSE);
	float similar = FR->CalculateSimilarity(feature1Array,feature2Array);
	return similar;
}
