#pragma once
#ifndef JFIELD_STRUCT_H
#define JFIELD_STRUCT_H

#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif

struct JClassDef
{
	char* className;
	jmethodID constructor;
	bool init;

	JClassDef(char *_className) {
		className = _className;
	}
};

struct JSeetaFaceInfo : JClassDef
{
	jfieldID x;
	jfieldID y;
	jfieldID width;
	jfieldID height;
	jfieldID score;
	JSeetaFaceInfo(char* _className) : JClassDef(_className) {}
};

struct JSeetaImageData : JClassDef
{
	jfieldID width;
	jfieldID height;
	jfieldID channels;
	jfieldID data;
	JSeetaImageData(char* _className) : JClassDef(_className) {}
};

struct JSeetaPoint : JClassDef
{
	jfieldID x;
	jfieldID y;
	JSeetaPoint(char* _className) : JClassDef(_className) {}
};

struct JSeetaPointF : JClassDef
{
	jfieldID x;
	jfieldID y;
	JSeetaPointF(char* _className) : JClassDef(_className) {}
};

struct JSeetaRect : JClassDef
{
	jfieldID x;
	jfieldID y;
	jfieldID width;
	jfieldID height;
	JSeetaRect(char* _className) : JClassDef(_className) {}
};

struct JSeetaRegion : JClassDef
{
	jfieldID top;
	jfieldID bottom;
	jfieldID left;
	jfieldID right;
	JSeetaRegion(char *_className) : JClassDef(_className){}
};

struct JSeetaSize : JClassDef
{
	jfieldID width;
	jfieldID height;
	JSeetaSize(char* _className) : JClassDef(_className) {}
};

struct JSeetaAngle : JClassDef
{
	jfieldID yaw;
	jfieldID pitch;
	jfieldID roll;
	JSeetaAngle(char* _className) : JClassDef(_className) {}
};

#ifdef __cplusplus
}
#endif // __cplusplus
#endif // !JFIELD_STRUCT_H
