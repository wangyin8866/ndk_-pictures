package com.wy.imagendk;

public class ImageNdkUtils {
	//���ط�������
	static{
		System.loadLibrary("ImageNdk");
	}
	public static native int [] getImage(int[] buffer,int width,int height);
}