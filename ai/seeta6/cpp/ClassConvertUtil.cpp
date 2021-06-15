#include "ClassConvertUtil.h"

using namespace std;

static JSeetaImageData _JSeetaImageData((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaImageData;");
static JSeetaPoint     _JSeetaPoint((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaPoint;");
static JSeetaPointF    _JSeetaPointF((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaPointF;");
static JSeetaRect      _JSeetaRect((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaRect;");
static JSeetaRegion    _JSeetaRegion((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaRegion;");
static JSeetaSize      _JSeetaSize((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaSize;");
static JSeetaFaceInfo  _JSeetaFaceInfo((char*)"Lio/vincentwinner/toolset/ai/seeta6jni/structs/SeetaFaceInfo;");

jobject newJObject(JNIEnv* env, JClassDef classDef)
{
	jclass clazz = env->FindClass(classDef.className);
	jobject obj = env->NewObject(clazz, classDef.constructor);
	env->DeleteLocalRef(clazz);
	return obj;
}

JSeetaImageData getJSeetaImageData(JNIEnv* env)
{
	if (!_JSeetaImageData.init) {
		jclass clazz = env->FindClass(_JSeetaImageData.className);
		_JSeetaImageData.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaImageData.data = env->GetFieldID(clazz, "data", "[B");
		_JSeetaImageData.width = env->GetFieldID(clazz, "width", "I");
		_JSeetaImageData.height = env->GetFieldID(clazz, "height", "I");
		_JSeetaImageData.channels = env->GetFieldID(clazz, "channels", "I");
		_JSeetaImageData.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaImageData;
}

SeetaImageData* toSeetaImageData(JNIEnv* env, jobject obj)
{
	JSeetaImageData jclass = getJSeetaImageData(env);
	SeetaImageData* imgdata = new SeetaImageData();
	jbyteArray jbytes = (jbyteArray)env->GetObjectField(obj, jclass.data);
	imgdata->data = (unsigned char*)env->GetByteArrayElements(jbytes, 0);
	imgdata->width = env->GetIntField(obj, jclass.width);
	imgdata->height = env->GetIntField(obj, jclass.height);
	imgdata->channels = env->GetIntField(obj, jclass.channels);
	env->DeleteLocalRef(jbytes);
	return imgdata;
}

JSeetaPoint getJSeetaPoint(JNIEnv* env)
{
	if (!_JSeetaPoint.init) {
		jclass clazz = env->FindClass(_JSeetaPoint.className);
		_JSeetaPoint.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaPoint.x = env->GetFieldID(clazz, "x", "I");
		_JSeetaPoint.y = env->GetFieldID(clazz, "y", "I");
		_JSeetaPoint.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaPoint;
}

SeetaPoint* toSeetaPoint(JNIEnv* env, jobject obj)
{
	JSeetaPoint jclass = getJSeetaPoint(env);
	SeetaPoint* point = new SeetaPoint();
	point->x = env->GetIntField(obj,jclass.x);
	point->y = env->GetIntField(obj, jclass.y);
	return point;
}


JSeetaPointF getJSeetaPointF(JNIEnv* env)
{
	if (!_JSeetaPointF.init) {
		jclass clazz = env->FindClass(_JSeetaPointF.className);
		_JSeetaPointF.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaPointF.x = env->GetFieldID(clazz, "x", "D");
		_JSeetaPointF.y = env->GetFieldID(clazz, "y", "D");
		_JSeetaPointF.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaPointF;
}

SeetaPointF* toSeetaPointF(JNIEnv* env, jobject obj)
{

	JSeetaPointF jclass = getJSeetaPointF(env);
	SeetaPointF* pointf = new SeetaPointF();
	pointf->x = env->GetIntField(obj, jclass.x);
	pointf->y = env->GetIntField(obj, jclass.y);
	return pointf;
}

SeetaPointF* toSeetaPointFArray(JNIEnv* env, jobjectArray objArray)
{
	JSeetaPointF jpointf = getJSeetaPointF(env);
	int point_num = env->GetArrayLength(objArray);
	SeetaPointF* pointfs = new SeetaPointF[point_num];
	for (int i = 0; i < point_num; i++)
	{
		jobject jpoint = env->GetObjectArrayElement(objArray,i);
		(pointfs + i)->x = env->GetDoubleField(jpoint, jpointf.x);
		(pointfs + i)->y = env->GetDoubleField(jpoint, jpointf.y);
		env->DeleteLocalRef(jpoint);
	}
	return pointfs;
}

JSeetaRect getJSeetaRect(JNIEnv* env)
{
	if (!_JSeetaRect.init) {
		jclass clazz = env->FindClass(_JSeetaRect.className);
		_JSeetaRect.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaRect.x = env->GetFieldID(clazz, "x", "I");
		_JSeetaRect.y = env->GetFieldID(clazz, "y", "I");
		_JSeetaRect.width = env->GetFieldID(clazz, "width", "I");
		_JSeetaRect.height = env->GetFieldID(clazz, "height", "I");
		_JSeetaRect.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaRect;
}

SeetaRect* toSeetaRect(JNIEnv* env, jobject obj)
{

	JSeetaRect jclass = getJSeetaRect(env);
	SeetaRect* rect = new SeetaRect();
	rect->x = env->GetIntField(obj,jclass.x);
	rect->y = env->GetIntField(obj, jclass.y);
	rect->width = env->GetIntField(obj, jclass.width);
	rect->height = env->GetIntField(obj, jclass.height);
	return rect;
}

JSeetaRegion getJSeetaRegion(JNIEnv* env)
{
	if (!_JSeetaRegion.init) {
		jclass clazz = env->FindClass(_JSeetaRegion.className);
		_JSeetaRegion.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaRegion.top = env->GetFieldID(clazz, "top", "I");
		_JSeetaRegion.bottom = env->GetFieldID(clazz, "bottom", "I");
		_JSeetaRegion.left = env->GetFieldID(clazz, "left", "I");
		_JSeetaRegion.right = env->GetFieldID(clazz, "right", "I");
		_JSeetaRegion.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaRegion;
}

SeetaRegion* toSeetaRegion(JNIEnv* env, jobject obj)
{
	JSeetaRegion jclass = getJSeetaRegion(env);
	SeetaRegion* reg = new SeetaRegion();
	reg->top = env->GetIntField(obj,jclass.top);
	reg->bottom = env->GetIntField(obj, jclass.bottom);
	reg->left = env->GetIntField(obj, jclass.left);
	reg->right = env->GetIntField(obj, jclass.right);
	return reg;
}

JSeetaSize getJSeetaSize(JNIEnv* env)
{
	if (!_JSeetaSize.init) {
		jclass clazz = env->FindClass(_JSeetaSize.className);
		_JSeetaSize.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaSize.width = env->GetFieldID(clazz, "width", "I");
		_JSeetaSize.height = env->GetFieldID(clazz, "height", "I");
		_JSeetaSize.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaSize;
}

SeetaSize* toSeetaSize(JNIEnv* env, jobject obj)
{
	JSeetaSize jclass = getJSeetaSize(env);
	SeetaSize* size = new SeetaSize();
	size->width = env->GetIntField(obj,jclass.width);
	size->height = env->GetIntField(obj, jclass.height);
	return size;
}

JSeetaFaceInfo getJSeetaFaceInfo(JNIEnv* env)
{
	if (!_JSeetaFaceInfo.init) {
		jclass clazz = env->FindClass(_JSeetaFaceInfo.className);
		_JSeetaFaceInfo.constructor = env->GetMethodID(clazz, "<init>", "()V");
		_JSeetaFaceInfo.score = env->GetFieldID(clazz,"score","F");
		_JSeetaFaceInfo.x = env->GetFieldID(clazz,"x","I");
		_JSeetaFaceInfo.y = env->GetFieldID(clazz, "y", "I");
		_JSeetaFaceInfo.width = env->GetFieldID(clazz, "width", "I");
		_JSeetaFaceInfo.height = env->GetFieldID(clazz, "height", "I");
		_JSeetaSize.init = true;
		env->DeleteLocalRef(clazz);
	}
	return _JSeetaFaceInfo;
}
