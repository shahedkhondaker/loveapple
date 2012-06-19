package cn.loveapple.client.android.shiba.view;

import static cn.loveapple.client.android.Constant.LOG_TAG;

import java.util.TimerTask;

import org.apache.commons.lang.builder.ToStringBuilder;

import android.app.Activity;
import android.util.Log;
import cn.loveapple.client.android.ApiHelper;
import cn.loveapple.client.android.ApiHelper.ProxyServerList;

public class RsyncProxyServerListTimerTask extends TimerTask {

	private ProxyServerList proxyServerList = null;
	private Activity activity;
	
	public RsyncProxyServerListTimerTask(Activity activity){
		this.activity = activity;
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				proxyServerList = ApiHelper.reloadProxyServerList(activity.getPackageManager());
			} catch (Exception e) {
				Log.e(LOG_TAG, "fail to reload proxy server list.", e);
			}
			Log.d(LOG_TAG,
					"reload proxy server list in service."
							+ ToStringBuilder.reflectionToString(proxyServerList));
			
		}
	}

}
