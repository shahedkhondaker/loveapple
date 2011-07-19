package cn.loveapple.client.android.bbt;

import org.afree.chart.AFreeChart;
import org.afree.graphics.geom.RectShape;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * JFreeChartテスト
 * 
 * @author loveapple
 * @since 2011/07/19
 * @version $Revision$
 */
public class SamplePieChartView extends View {
	private AFreeChart chart;

	public SamplePieChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		RectShape chartArea = new RectShape(0.0, 0.0, 200.0, 200.0);
		this.chart.draw(canvas, chartArea);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(200, 200);
	}

	public void setChart(AFreeChart chart) {
		this.chart = chart;
	}
}
