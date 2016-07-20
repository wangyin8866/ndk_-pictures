LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ImageNdk
LOCAL_SRC_FILES := com_wy_imagendk_ImageNdkUtils.c

include $(BUILD_SHARED_LIBRARY)
