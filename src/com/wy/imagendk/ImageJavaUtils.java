package com.wy.imagendk;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ImageJavaUtils {
	public static float brighness = 0.2f, constrat = 0.2f;

	public static Bitmap getImage(Bitmap bitmap) {
		// 像素处理
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		// 处理过的图片
		Bitmap result = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		
		// 开始处理
		int a, r, g, b;
		// 调黑亮度
		int bab = (int) (255 * brighness);
		// 对比度
		float ca = (float) (1.0f + constrat);
		int cab = (int) ((ca * 65536)+1);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// 拿到每一个像素进行处理
				int color = bitmap.getPixel(x, y);
				a = Color.alpha(color);
				r = Color.red(color);
				g = Color.green(color);
				b = Color.blue(color);
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

				result.setPixel(x, y, Color.argb(a, r, g, b));
			}

		}
		return result;

	}
}
