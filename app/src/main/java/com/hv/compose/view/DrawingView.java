package com.hv.compose.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hv.compose.view.StickerObject;
import com.hv.compose.view.TextObject;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    public DrawingView (Context context) {
        super(context); init();
    }

    private Paint paint;
    private Path path;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint bitmapPaint;
    private boolean isDrawingAllowed = true;
    private List<TextObject> textObjects;
    private List<StickerObject> stickerObjects = new ArrayList<>();

    public void setOnTouch(OnTouch onTouch) {
        this.onTouch = onTouch;
    }

    private OnTouch onTouch;

    public interface OnTouch {
        void currentTextOjectUse(TextObject currentObject);

        void currentTextOjectMove(TextObject currentTextObject);

        void currentOjectNone();

        void currentStickerOjectUse(StickerObject currentStickerObject);

        void currentStickerOjectMove(StickerObject currentStickerObject);
    }

    private TextObject currentTextObject;
    private StickerObject currentStickerObject;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(-56243);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(40);

        path = new Path();
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        textObjects = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println(h);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制贴纸
        for (StickerObject stickerObject : stickerObjects) {
            stickerObject.draw(canvas);
        }

        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.drawPath(path, paint);

        for (TextObject textObject : textObjects) {
            textObject.draw(canvas);
        }
    }

    public Bitmap exportBitmapWithouText() {
        //返回不带文字的图像
        return bitmap;
    }

    public Bitmap exportBitmap() {
        //返回最后的图像
        return bitmap;
    }

    float oldx, oldy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isDrawingAllowed) return false;

        float x = event.getX();
        float y = event.getY();
        float dx, dy;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = x;
                oldy = y;
                // 检查是否点击在一个文字对象上
                for (TextObject textObject : textObjects) {
                    if (textObject.contains(x, y)) {
                        currentTextObject = textObject;
                        onTouch.currentTextOjectUse(currentTextObject); // 触发文字对象的使用事件
                        break;
                    }
                }
                // 检查是否点击在一个小贴纸对象上
                if (currentTextObject == null) {
                    for (StickerObject stickerObject : stickerObjects) {
                        if (stickerObject.contains(x, y)) {
                            currentStickerObject = stickerObject;
                            onTouch.currentStickerOjectUse(currentStickerObject); // 可以根据需要触发小贴纸的使用事件
                            break;
                        }
                    }
                }

                // 如果既不是文字对象也不是小贴纸对象，开始画线
                if (currentTextObject == null && currentStickerObject == null) {
                    path.moveTo(x, y);
                    onTouch.currentOjectNone(); // 没有点击到任何文本对象或小贴纸
                }

                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                if (currentTextObject != null) {
                    dx = event.getX() - oldx;
                    dy = event.getY() - oldy;
                    oldx = x;
                    oldy = y;

                    currentTextObject.move(dx, dy); // 移动当前的文字对象
                    onTouch.currentTextOjectMove(currentTextObject); // 触发文字对象的移动事件
                } else if (currentStickerObject != null) {
                    dx = event.getX() - oldx;
                    dy = event.getY() - oldy;
                    oldx = x;
                    oldy = y;

                    currentStickerObject.move(dx, dy); // 移动当前的小贴纸对象
                    onTouch.currentStickerOjectMove(currentStickerObject); // 可以根据需要触发小贴纸的移动事件
                } else {
                    path.lineTo(x, y); // 在画布上绘制线条
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                // 释放文字对象和小贴纸对象
                if (currentTextObject != null) {
                    currentTextObject = null;
                } else if (currentStickerObject != null) {
                    currentStickerObject = null;
                } else {
                    canvas.drawPath(path, paint); // 绘制路径
                    path.reset(); // 重置路径
                }
                invalidate();
                break;
        }
        return true;
    }

    public void clear() {
        textObjects.clear();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public void setPaintColor(int color) {
        paint.setColor(color);
    }

    public void setStrokeWidth(int width) {
        paint.setStrokeWidth(width);
    }

    public void setStyle(Paint.Style style) {
        paint.setStyle(style);
    }

    public void setEraserMode(boolean isEraser) {
        if (isEraser) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        } else {
            paint.setXfermode(null);
        }
    }

//    public void setBackgroundBitmap(Bitmap bitmap) {
//        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
//    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public void delteTextOjbect(TextObject textObject) {
        textObjects.remove(textObject);
        invalidate();
    }

    public TextObject addTextObject(String text) {
        TextObject textObject = new TextObject(text, 80, 500, 500);
        textObjects.add(textObject);
        textObject.setTemplateWhitebgBrown();
        invalidate();
        return textObject;
    }

    public StickerObject addSticker(Bitmap stickerBitmap, float x, float y, float width, float height) {
        StickerObject stickerObject = new StickerObject(stickerBitmap, x, y, width, height);
        stickerObjects.add(stickerObject);
        invalidate();
        return stickerObject;
    }

    public void deleteSticker(StickerObject stickerObject) {
        stickerObjects.remove(stickerObject);
        invalidate();
    }
}
