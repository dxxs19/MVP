#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_wei_mvp_ui_home_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
