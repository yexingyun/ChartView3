package chartview.com.chartview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/11/6.
 */

public class ColumnView extends BaseGraphView {
    public ColumnView(Context context) {
        this(context, null);
    }

    public ColumnView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColumnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawAxisScaleMarkX(Canvas canvas, Paint paint) {
        float cellwidth = width / axisDivideSizeX;
        int i;
        for (i = 0; i < axisDivideSizeX - 1; i++) {
            canvas.drawLine(originX + (i * cellwidth), originY, originX + i * cellwidth, originY - 20, paint);
        }
    }

    @Override
    protected void drawAxisScaleMarkY(Canvas canvas, Paint paint) {
        float cellheight = height / axisDivideSizeY;
        int i;
        for (i = 0; i < axisDivideSizeY - 1; i++) {
            canvas.drawLine(originX, originY -(i * cellheight), originX+20, originY -(i * cellheight) , paint);
        }
    }

    @Override
    protected void drawAxisScaleMarkValueX(Canvas canvas, Paint paint) {
//设置画笔绘制文字的属性
        paint.setColor(Color.GRAY);
        paint.setTextSize(12);
        paint.setFakeBoldText(true);
        float cellwidth = width / axisDivideSizeX;
        float cellvalue = maxAxisValueX / axisDivideSizeX;
        int i;
        for (i = 0; i < axisDivideSizeX; i++) {
            canvas.drawText(String.valueOf(cellvalue * i), originX + cellwidth * i, originY + 20, paint);
        }

    }

    @Override
    protected void drawAxisScaleMarkValueY(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setTextSize(12);
        paint.setFakeBoldText(true);
        float cellheight = height / axisDivideSizeY;
        float cellvalue = maxAxisValueY / axisDivideSizeY;
        int i;
        for (i = 0; i < axisDivideSizeX; i++) {
           // canvas.drawText(String.valueOf(cellvalue * i), originX + cellheight * i, originY + 20, paint);
            canvas.drawText(String.valueOf( i), originX-35, originY  - cellheight * i, paint);
        }
    }

    @Override
    protected void drawColumn(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setTextSize(12);
        paint.setFakeBoldText(true);
        float cellwidth = width / axisDivideSizeY;

        int i;
        for (i = 0; i < columnInfo.length; i++) {
            paint.setColor(columnInfo[i][1]);
            float lefttop = originY - height* columnInfo[i][0] / maxAxisValueY ;
            canvas.drawRect(i==0?originX:originX + cellwidth * i, lefttop,originX + cellwidth * (i+1),originY,paint );
        }
    }
}
