package cn.loveapple.client.android.bbt.view;

import static cn.loveapple.client.android.Constant.*;

import java.util.TimerTask;

import android.app.Activity;
import android.util.Log;

public class BannerTimerTask extends TimerTask {
	
	private Activity activity;
	private long startTime;
	
	public BannerTimerTask(Activity activity){
		startTime = System.currentTimeMillis();
		this.activity = activity;
	}

	@Override
	public void run() {
		long millis = System.currentTimeMillis() - startTime;
		int seconds = (int) (millis / 1000);
		int minutes = seconds / 60;
		seconds     = seconds % 60;
		
		Log.d(LOG_TAG, String.format("Timmer: %d:%02d", minutes, seconds));
	}

}
