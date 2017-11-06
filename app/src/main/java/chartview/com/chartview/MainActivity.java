package chartview.com.chartview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import chartview.com.chartview.view.ColumnView;

public class MainActivity extends AppCompatActivity {

    private ColumnView mColumnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColumnView = (ColumnView) findViewById(R.id.columnView);
        mColumnView.setXAxisValue(10, 9);
        mColumnView.setYAxisValue(10,7);
        mColumnView.setGrapthTitle("安卓图形绘制直方图");

        int columnInfo[][] = new int[][]{
                {6, Color.BLUE},//0,0
                {5, Color.GREEN},
                {4, Color.RED},
                {3, Color.BLUE},
                {5, Color.YELLOW},
                {3, Color.LTGRAY},
                {2, Color.BLUE}
        };
        mColumnView.setColumnInfo(columnInfo);
    }
}
