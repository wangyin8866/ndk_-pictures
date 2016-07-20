#include <stdlib.h>
#include <android/bitmap.h>

jintArray JNICALL Java_com_wy_imagendk_ImageNdkUtils_getImage(JNIEnv * env,
		jclass jclz, jintArray buffer, jint width, jint height) {
	//java中的int数组转成c中的int 数组
	jint* source = (*env)->GetByteArrayElements(env, buffer, 0);
	//像素数组长度
	int newSize = width * height;
	float brightness = 0.2f, constrat = 0.2f;
	// 开始处理
	int a, r, g, b;
	// 调黑亮度
	int bab = (int) (255 * brightness);
	// 对比度
	float ca = (float) (1.0f + constrat);
	int cab = (int) ((ca * 65536) + 1);
	int x=0;
	int y=0;
	for (x=0 ; x < width; x++) {
		for (y=0; y < height; y++) {
			// 拿到每一个像素进行处理
			int color = source[y * width + x];
			a = (color >> 24) & 0xFF;
			r = (color >> 16) & 0xFF;
			g = (color >> 8) & 0xFF;
			b = color & 0xFF;
			int ri = r - bab;
			int gi = g - bab;
			int bi = b - bab;
			r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
			g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
			b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
			// 变化对比度
			ri = r - 128;
			gi = g - 128;
			bi = b - 128;
			ri = (ri * cab) >> 16;
			gi = (gi * cab) >> 16;
			bi = (bi * cab) >> 16;

			ri = ri + 128;
			gi = gi + 128;
			bi = bi + 128;
			r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
			g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
			b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);

			source[y * width + x] = (a << 24) | (r << 16) | (g << 8) | b;
		}
	}
	//c转java中的数组jintArray
	jintArray result = (*env)->NewIntArray(env, newSize);
	(*env)->SetIntArrayRegion(env,result,0,newSize,source);
	(*env)->ReleaseIntArrayElements(env,buffer,source,0);
	return result;
}
