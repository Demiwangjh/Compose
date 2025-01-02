package com.hv.compose.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class StickerObject {
    private Bitmap stickerBitmap; // 小贴纸的原始图像
    public float x, y; // 小贴纸的坐标
    public float width, height; // 小贴纸的宽高
    private static final float RESIZE_AREA_SIZE = 30; // 调整区域的大小（四个角）

    // 构造函数
    public StickerObject(Bitmap stickerBitmap, float x, float y, float width, float height) {
        // 通过 Matrix 缩放 Bitmap 到指定的宽高
        this.stickerBitmap = Bitmap.createScaledBitmap(stickerBitmap, (int) width, (int) height, true);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // 绘制小贴纸
    public void draw(Canvas canvas) {
        if (stickerBitmap != null) {
            // 绘制贴纸
            canvas.drawBitmap(stickerBitmap, x, y, null);
        }
    }

    // 检测触摸点是否在小贴纸内
    public boolean contains(float touchX, float touchY) {
        return touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height;
    }

    // 移动小贴纸
    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    // 调整小贴纸的大小
    public void resize(float dx, float dy) {
        this.width += dx;
        this.height += dy;
        this.stickerBitmap = Bitmap.createScaledBitmap(stickerBitmap, (int) width, (int) height, true);
    }
}