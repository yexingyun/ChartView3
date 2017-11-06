package chartview.com.chartview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import chartview.com.chartview.R;

/**
 * Created by Administrator on 2017/11/6.
 */

public abstract class BaseGraphView extends View {
    public String mGrapthTitle;
    public String mXAxisName;
    public String mYAxisName;
    public float mAxisTextSize;
    public int mAxisTextColor;
    private Paint paint = null;
    //X坐标轴最大值
    public float maxAxisValueX = 900;
    //X坐标轴刻度线数量
    public int axisDivideSizeX = 9;
    //Y坐标轴最大值
    public float maxAxisValueY = 700;
    //Y坐标轴刻度线数量
    public int axisDivideSizeY = 7;
    //坐标原点位置
    public final int originX = 100;
    public final int originY = 1500;
    public int width;
    public int height;
    public int columnInfo[][];

    public BaseGraphView(Context context) {
        this(context,null);
    }

    public BaseGraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunnyGraphStyle);
        mGrapthTitle = typedArray.getString(R.styleable.SunnyGraphStyle_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.SunnyGraphStyle_X_AxisName);
        mYAxisName = typedArray.getString(R.styleable.SunnyGraphStyle_Y_AxisName);
        mAxisTextColor = typedArray.getColor(R.styleable.SunnyGraphStyle_axisTextColor,context.getResources().getColor(android.R.color.black));
        mAxisTextSize = typedArray.getDimension(R.styleable.SunnyGraphStyle_axisTextSize,12);
        if (typedArray!=null){
            typedArray.recycle();
        }
        if (paint==null){
            paint = new Paint();
            paint.setAntiAlias(true);//抗锯齿，
            paint.setDither(true);//防抖动
        }


    }

    /**
     * 传入柱状图数据
     * @param columnInfo
     */
    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }
    public void setGrapthTitle(String grapthTitle) {
        mGrapthTitle = grapthTitle;
    }

    public void setXAxisName(String XAxisName) {
        mXAxisName = XAxisName;
    }

    public void setYAxisName(String YAxisName) {
        mYAxisName = YAxisName;
    }

    public void setAxisTextColor(int axisTextColor) {
        mAxisTextColor = axisTextColor;
    }

    public void setAxisTextSize(float axisTextSize) {
        mAxisTextSize = axisTextSize;
    }


    /**
     * 手动设置X轴最大值及等份数
     * @param maxAxisValueX
     * @param dividedSize
     */
    public void setXAxisValue(float maxAxisValueX,int dividedSize) {
        this.maxAxisValueX = maxAxisValueX;
        this.axisDivideSizeX = dividedSize;
    }

    /**
     * 手动设置Y轴最大值及等份数
     * @param maxAxisValueY
     * @param dividedSize
     */
    public void setYAxisValue(float maxAxisValueY,int dividedSize) {
        this.maxAxisValueY = maxAxisValueY;
        this.axisDivideSizeY = dividedSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec-100)  ;
      height = MeasureSpec.getSize(heightMeasureSpec-100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth()-originX-100;

        height = getHeight()-400;
        drawXAxis(canvas,paint);
        drawYAxis(canvas,paint);
        drawTitle(canvas,paint);
        drawAxisScaleMarkX(canvas, paint);//X　　Ｙ的刻度
        drawAxisScaleMarkY(canvas, paint);
        drawAxisArrowsX(canvas, paint);//画箭头
        drawAxisArrowsY(canvas, paint);
        drawAxisScaleMarkValueX(canvas, paint);//刻度值
        drawAxisScaleMarkValueY(canvas, paint);
        drawColumn(canvas, paint);
        drawTitle(canvas, paint);

    }

    protected abstract void drawAxisScaleMarkX(Canvas canvas, Paint paint);

    protected abstract void drawAxisScaleMarkY(Canvas canvas, Paint paint);

    protected abstract void drawAxisScaleMarkValueX(Canvas canvas, Paint paint);

    protected abstract void drawAxisScaleMarkValueY(Canvas canvas, Paint paint);

    protected abstract void drawColumn(Canvas canvas, Paint paint);


    private void drawAxisArrowsY(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(originX,originY-height-20);
        path.lineTo(originX-20,originY-height);
        path.lineTo(originX+20,originY-height);
        path.close();//封闭图形
        canvas.drawPath(path,paint);

    }

    private void drawAxisArrowsX(Canvas canvas, Paint paint) {


        Path path = new Path();
        path.moveTo(originX+width+20,originY);
        path.lineTo(originX+width,originY-20);
        path.lineTo(originX+width,originY+20);
        path.close();//封闭图形
        canvas.drawPath(path,paint);
    }

    private void drawTitle(Canvas canvas, Paint paint) {
//        if (TextUtils.isEmpty(mGrapthTitle)){
//            paint.setColor(mAxisTextColor);
//            paint.setTextSize(mAxisTextSize);
//            paint.setFakeBoldText(true);
//            canvas.drawText(mGrapthTitle,(getWidth()/2)-paint.measureText(mGrapthTitle)/2,originY-500,paint);
//        }
        //画标题
        if (mGrapthTitle != null) {
            //设置画笔绘制文字的属性
            paint.setColor(mAxisTextColor);
            paint.setTextSize(mAxisTextSize);
            paint.setFakeBoldText(true);
            canvas.drawText(mGrapthTitle, (getWidth()/2)-(paint.measureText(mGrapthTitle)/2), originY + 100, paint);
        }

    }

    private void drawYAxis(Canvas canvas, Paint paint) {
     paint.setColor(mAxisTextColor);
        canvas.drawLine(originX,originY,originX,originY-height,paint);

    }

    private void drawXAxis(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        //设置画笔宽度
        paint.setStrokeWidth(8);
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        canvas.drawLine(originX,originY,originX+width,originY,paint);
    }
}
