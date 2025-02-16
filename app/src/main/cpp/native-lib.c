#include <jni.h>

Java_com_example_myjnisample_MainActivity_addNumbers(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a + b;
}