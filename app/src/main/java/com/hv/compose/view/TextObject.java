package com.hv.compose.view;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextObject {
    private String text;
    public float textSize;
    public float x;
    public float y;
    public float bw;
    public float bh;
    public float minh;
    public float by;
    private Typeface typeface = Typeface.DEFAULT;
    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private boolean Delline = false;
    private boolean background = false;
    private int alpha = 100; //0~100
    private int borderalpha = 100;
    private int backgroundalpha = 100;
    private int left,right,bottom,top;
    private int bgWidthDelta, bgHeightDelta;
    private boolean border = false;
    private int circlex = 0;
    private float bth = 0;
    private Boolean textouterLight=false;
    private Boolean textShadow=false;
    private int shadowRadius = 10;
    private int shadowdx = 5;
    private int shadowdy = 5;
    private int shadowColor = Color.WHITE;
    private BlurMaskFilter.Blur lighttype = BlurMaskFilter.Blur.SOLID;
    private  int lightradius = 10;
    public Paint textPaint, textBorderPaint;
    private Rect textBounds = new Rect();
    private Paint backgroundPaint ;

    public TextObject(String text, float textSize, float x, float y) {
        this.text = text;
        this.textSize = textSize;
        this.x = x;
        this.y = y;
        this.textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint.setColor(Color.BLUE); // 设置文本颜色
        textPaint.setStyle(Paint.Style.FILL); // 设置填充模式
        textPaint.setTextSize(textSize); // 设置文本大小
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        minh = textBounds.height() + 40;

    }

    public void getMinh(){
        if (this.border){
            textBorderPaint.getTextBounds(text, 0, text.length(), textBounds);
        }else{
            textPaint.getTextBounds(text, 0, text.length(), textBounds);
        }

    }

    public void draw(Canvas canvas) {

        if ((this.background) && (!Objects.equals(this.text, ""))){
            canvas.drawRoundRect(left, top, right, bottom, circlex, circlex, backgroundPaint);
        }
        if (this.border) {
            canvas.drawText(text, x, y, textBorderPaint);
        }
        canvas.drawText(text, x, y, textPaint);
    }

    public boolean contains(float touchX, float touchY) {
//        textPaint.getTextBounds(text, 0, text.length(), textBounds);
//        float textWidth = textPaint.measureText(text);
//        float textHeight = textBounds.height();
        getTextWH();
        float baselineOffset = textBounds.bottom;
//        bw = textWidth;
//        bh = textHeight;
        by = y - bh + baselineOffset; //用处是什么？
        return touchX >= x && touchX <= x + bw && touchY >= y - bh && touchY <= y;
    }

    public void move(float dx, float dy) {
        this.x = x+dx;
        this.y = y+dy;
        getTextWH();
        if (this.background){
            setBackgroundRectWidth(this.bgWidthDelta);
            setBackgroundRectHeight(this.bgHeightDelta);
        }
    }

    public void getTextWH(){
        if (border) {
            textBorderPaint.getTextBounds(text, 0, text.length(), textBounds);
        }else{
            textPaint.getTextBounds(text, 0, text.length(), textBounds);
        }
        bw = textPaint.measureText(text);
        bh = textBounds.height();
        bth = textPaint.getFontMetrics().bottom - textPaint.getFontMetrics().top;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(textSize);
        if (this.border) {
            textBorderPaint.setTextSize(textSize);
        }
        getTextWH();
        if (this.background){
            setBackgroundRectWidth(this.bgWidthDelta);
            setBackgroundRectHeight(this.bgHeightDelta);
        }

    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
        getTextWH();
        if (this.background){
            setBackgroundRectWidth(this.bgWidthDelta);
            setBackgroundRectHeight(this.bgHeightDelta);
        }
    }

    public void setFontType(Typeface typetmp){
        this.typeface = typetmp;
        this.textPaint.setTypeface(typetmp);
        if (border){
            this.textBorderPaint.setTypeface(typetmp);
        }
    }

    public Typeface getFontType(){ return typeface;}

    public void setTextOuterLight(int radius, BlurMaskFilter.Blur lighttype){
        this.textouterLight = true;
        this.lighttype = lighttype;
        this.lightradius = radius;
        updateOuterLight();
    }

    public void setTextOuterLightRadius(int radius){
        this.lightradius = radius;
        this.textouterLight = true;
        updateOuterLight();
    }

    public void setTextOuterLightType(BlurMaskFilter.Blur lighttype){
        this.lighttype = lighttype;
        this.textouterLight = true;
        updateOuterLight();
    }
    private void updateOuterLight(){
        if (this.border) {
            this.textBorderPaint.setMaskFilter(new BlurMaskFilter(this.lightradius, this.lighttype));
        }
//        if (this.border) {
//            this.textBorderPaint.setMaskFilter(new BlurMaskFilter(this.lightradius, this.lighttype));
//        } else{
//            this.textPaint.setMaskFilter(new BlurMaskFilter(this.lightradius, this.lighttype));
//        }
    }

    public void setTextNoOuterLight(){
        this.textouterLight = false;
        if (this.border) {
            this.textBorderPaint.setMaskFilter(null);
        } //else {
//            this.textPaint.setMaskFilter(null);
//        }
    }

    public void setTextShadow() {
        this.textShadow = true;
        updateTextShadow();
    }

    public void setTextShadowRadius(int radius) {
        shadowRadius = radius;
        this.textShadow = true;
        updateTextShadow();
    }
    public void setTextShadowDx(int dx) {
        this.shadowdx= dx;
        this.textShadow = true;
        updateTextShadow();
    }
    public void setTextShadowDy(int dy) {
        this.shadowdy= dy;
        this.textShadow = true;
        updateTextShadow();
    }
    public void setTextShadowColor(int color) {
        shadowColor = color;
        updateTextShadow();
    }
    public void setTextNoShadow() {
        if (this.border) {
            this.textBorderPaint.clearShadowLayer();
        }
        this.textShadow = false;
    }
    private void updateTextShadow(){
        if (this.border) {
            this.textBorderPaint.setShadowLayer(this.shadowRadius, this.shadowdx, this.shadowdy, this.shadowColor);
        }
//        if (this.border) {
//            this.textBorderPaint.setShadowLayer(this.shadowRadius, this.shadowdx, this.shadowdy, this.shadowColor);
//        } else{
//            this.textPaint.setShadowLayer(this.shadowRadius, this.shadowdx, this.shadowdy, this.shadowColor);
//        }
    }

    public void setBold(){
        if (this.italic && this.bold){
            typeface = Typeface.create(typeface, Typeface.ITALIC);
        }else if (this.italic && !this.bold){
            typeface = Typeface.create(typeface, Typeface.BOLD_ITALIC);
        }else if (!this.italic && this.bold) {
            typeface = Typeface.create(typeface, Typeface.NORMAL);
        }else{
            typeface = Typeface.create(typeface, Typeface.BOLD);
        }
        this.textPaint.setTypeface(typeface);
        if (border){
            this.textBorderPaint.setTypeface(typeface);
        }
        this.bold = !this.bold;
    }
    public void setItalic(){
        if (this.italic && this.bold){
            typeface = Typeface.create(typeface, Typeface.BOLD);
        }else if (this.italic && !this.bold){
            typeface = Typeface.create(typeface, Typeface.NORMAL);
        }else if (!this.italic && this.bold) {
            typeface = Typeface.create(typeface, Typeface.BOLD_ITALIC);
        }else{
            typeface = Typeface.create(typeface, Typeface.ITALIC);
        }
        this.textPaint.setTypeface(typeface);
        if (border){
            this.textBorderPaint.setTypeface(typeface);
        }
        this.italic = !this.italic;
    }

    public void setUnderline(){
        this.underline = !this.underline;
        this.textPaint.setUnderlineText(underline);

    }

    public void setDelline(){
        this.Delline = !this.Delline;
        this.textPaint.setStrikeThruText(Delline);

    }

    public void setTextColorTransparency(int alpha){
        // 设置颜色，带有透明度
        this.alpha = alpha;
        this.textPaint.setAlpha((int) (alpha *(2.55)));
    }

    public void setTextColor(int color){
        this.textPaint.setColor(color);
    }

    public void setBorder(int color){
        this.textBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textBorderPaint.setColor(color); // 设置描边的颜色
        textBorderPaint.setStyle(Paint.Style.STROKE); // 设置为描边模式
        textBorderPaint.setStrokeWidth(3); // 设置描边的宽度
        textBorderPaint.setTextSize(textSize);
        textBorderPaint.setTypeface(this.typeface);
        this.border = true;
    }

    public void setNoBorder(){
        this.border = false;
    }

    public void setBorderColor(int color){
        if (!this.border){
            setBorder(color);
        } else{
            textBorderPaint.setColor(color);
        }
    }

    public void setBorderStrokeWidth(int width){
        if (!this.border){
            setBorder(Color.BLACK);
        }
        textBorderPaint.setStrokeWidth(width);
    }
    public void setBorderColorTransparency(int alpha){
        if (!this.border){
            setBorder(Color.BLACK);
        }
        this.borderalpha = alpha;
        textBorderPaint.setAlpha((int) (alpha *(2.55)));
    }

    public void setBackgroundRect(int color){
        this.backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.bgWidthDelta = 2;
        this.bgHeightDelta = 2;
        left = (int) (x-bgWidthDelta);
        right = (int)(x+bw+bgWidthDelta);
        top =(int) ((y+(textPaint.getFontMetrics().descent + textPaint.getFontMetrics().ascent - bth) / 2)- bgHeightDelta);
        bottom = (int)((y+(textPaint.getFontMetrics().descent + textPaint.getFontMetrics().ascent + bth) / 2)+ bgHeightDelta);
        this.backgroundPaint.setColor(color);
        this.circlex = 1;
        this.background = true;
        getTextWH();
    }
    public void setBackgroundRectColor(int color){
        if (!this.background){
            setBackgroundRect(color);
        } else {
            this.backgroundPaint.setColor(color);
        }
    }

    public void setBackgroundRectColorTransparency(int alpha){
        this.backgroundalpha = alpha;
        this.backgroundPaint.setAlpha((int) (alpha *(2.55)));
    }

    public void setBackgroundRectWidth(int delta){
        this.bgWidthDelta = delta;
        this.left = (int) (x-bgWidthDelta);
        this.right = (int)(x+bw+bgWidthDelta);
    }

    public void setBackgroundRectHeight(int delta){
        this.bgHeightDelta = delta;
        this.top =(int) ((y+(textPaint.getFontMetrics().descent + textPaint.getFontMetrics().ascent - bth) / 2)- bgHeightDelta);
        this.bottom = (int)((y+(textPaint.getFontMetrics().descent + textPaint.getFontMetrics().ascent + bth) / 2)+ bgHeightDelta);
    }
    public void setBackgroundRectCorner(int radiu){
        this.circlex = radiu;
    }

    public void setNoBackgroundRect(){
        this.background = false;
    }

    public void setTemplateWhiteBlack(){
        setTextColor(Color.WHITE);
        setBorderColor(Color.BLACK);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateBlackWhite(){
        setTextColor(Color.BLACK);
        setBorderColor(Color.WHITE);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateYellowBlack(){
        setTextColor(Color.YELLOW);
        setBorderColor(Color.BLACK);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateWhitePink(){
        setTextColor(Color.WHITE);
        setBorderColor(0XFFEF97A2);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateBlueBlack(){
        setTextColor(0xFFB7DCF6);
        setBorderColor(Color.BLACK);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplatePinkRed(){
        setTextColor(0xFFFFD9E8);
        setBorderColorTransparency(50);
        setBorderColor(0XFFEA6C9B);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateBlueBlue(){
        setTextColor(0xFFB7DCF6);
        setBorderColor(0xFF62A8DB);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateBrownWhite(){
        setTextColor(0xFFAB4A37);
        setBorderColor(Color.WHITE);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateGreenWhite(){
        setTextColor(0xFF69F0AE);
        setBorderColor(Color.WHITE);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateWhitebgBrown(){
        setTextColor(Color.WHITE);
        setNoBorder();
        setBackgroundRectColor(0xFFAB4A37);
        setBackgroundRectHeight((int)(5));
        setBackgroundRectWidth((int)(5));
        setBackgroundRectCorner(20);
        setTextNoShadow();
        setTextNoOuterLight();
    }
    public void setTemplateRedWhiteOL(){
        setTextColor(0xFFE22523);
        setBorderColor(Color.WHITE);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        setTextNoShadow();
        setTextOuterLight(15, BlurMaskFilter.Blur.SOLID);
    }
    public void setTemplateWhiteRedShadowREDOL(){
        setTextColor(Color.WHITE);
        setBorderColor(0xFFCD2890);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        this.shadowColor = 0xFFCD2890;
        setTextShadow();
        setTextOuterLight(20, BlurMaskFilter.Blur.NORMAL);
    }
    public void setTemplateWhiteYellowShadowYellowOL(){
        setTextColor(Color.WHITE);
        setBorderColor(0xFF8E783F);
        setBorderStrokeWidth(10);
        setNoBackgroundRect();
        this.shadowColor = 0xFF8E783F;
        setTextShadow();
        setTextOuterLight(20, BlurMaskFilter.Blur.NORMAL);
    }

}
