package com.wy.imagendk;

public class ImageNdkUtils {
	//本地方法调用
	static{
		System.loadLibrary("ImageNdk");
	}
	public static native int [] getImage(int[] buffer,int width,int height);
}