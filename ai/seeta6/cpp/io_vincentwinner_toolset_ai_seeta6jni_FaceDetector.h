/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class io_vincentwinner_toolset_ai_seeta6jni_FaceDetector */

#ifndef _Included_io_vincentwinner_toolset_ai_seeta6jni_FaceDetector
#define _Included_io_vincentwinner_toolset_ai_seeta6jni_FaceDetector
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     io_vincentwinner_toolset_ai_seeta6jni_FaceDetector
 * Method:    loadModel
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceDetector_loadModel
  (JNIEnv *, jobject, jstring);

/*
 * Class:     io_vincentwinner_toolset_ai_seeta6jni_FaceDetector
 * Method:    detect
 * Signature: (Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaImageData;)[Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaFaceInfo;
 */
JNIEXPORT jobjectArray JNICALL Java_io_vincentwinner_toolset_ai_seeta6jni_FaceDetector_detect
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
