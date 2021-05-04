#pragma once
#ifndef CLASS_CONVERT_UTIL_H
#define CLASS_CONVERT_UTIL_H

#include <jni.h>
#include <string>
#include <seeta/CStruct.h>
#include <seeta/CFaceInfo.h>

#include "JFieldStruct.h"

jobject newJObject(JNIEnv* env, JClassDef classDef);

JSeetaImageData getJSeetaImageData(JNIEnv* env);
SeetaImageData* toSeetaImageData(JNIEnv* env, jobject obj);

JSeetaPoint getJSeetaPoint(JNIEnv* env);
SeetaPoint* toSeetaPoint(JNIEnv* env, jobject obj);

JSeetaPointF getJSeetaPointF(JNIEnv* env);
SeetaPointF* toSeetaPointF(JNIEnv* env, jobject obj);
SeetaPointF* toSeetaPointFArray(JNIEnv* env, jobjectArray objArray);

JSeetaRect getJSeetaRect(JNIEnv* env);
SeetaRect* toSeetaRect(JNIEnv* env, jobject obj);

JSeetaRegion getJSeetaRegion(JNIEnv* env);
SeetaRegion* toSeetaRegion(JNIEnv* env, jobject obj);

JSeetaSize getJSeetaSize(JNIEnv* env);
SeetaSize* toSeetaSize(JNIEnv* env, jobject obj);

JSeetaFaceInfo getJSeetaFaceInfo(JNIEnv* env);

#endif // !CLASS_CONVERT_UTIL_H
