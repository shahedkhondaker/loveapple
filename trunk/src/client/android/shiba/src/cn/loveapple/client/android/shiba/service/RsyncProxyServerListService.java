/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
 *
 * All copyright notices regarding loveapple and loveapple CoreLib
 * MUST remain intact in the scripts, documents and source code.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at loveapple
 *
 * @author: loveapple
 */
package cn.loveapple.client.android.shiba.service;

import static cn.loveapple.client.android.Constant.LOG_TAG;

import org.apache.commons.lang.builder.ToStringBuilder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import cn.loveapple.client.android.ApiHelper;
import cn.loveapple.client.android.ApiHelper.ProxyServerList;

/**
 * プロキシサーバアドレスデータ同期のサービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 * @deprecated TODO timerを使用？
 */
public class RsyncProxyServerListService extends Service {
	private ProxyServerList proxyServerList = null;
	private PendingIntent alarmSender;
	private final IBinder proxyServerListBinder = new ProxyServerListBinder();

	public class ProxyServerListBinder extends Binder {
		ProxyServerListBinder getService() {

			return ProxyServerListBinder.this;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return proxyServerListBinder;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Thread thr = new Thread(null, task, "RsyncProxyServerListService");
		Log.d(LOG_TAG,
				"start thread in service."
						+ ToStringBuilder.reflectionToString(thr));
		thr.start();
	}

	/**
	 * プロキシサーバのリスト
	 */
	@Override
	public void onCreate() {
		super.onCreate();
	}

	/**
	 * 
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private Runnable task = new Runnable() {
		public void run() {
			synchronized (proxyServerListBinder) {
				try {
					proxyServerList = ApiHelper.reloadProxyServerList(getPackageManager());
				} catch (Exception e) {
					Log.e(LOG_TAG, "fail to reload proxy server list.", e);
					return ;
				}
				Log.d(LOG_TAG,
						"reload proxy server list in service."
								+ ToStringBuilder.reflectionToString(proxyServerList));
				
				try{
					long now = System.currentTimeMillis();
					alarmSender = PendingIntent.getService(RsyncProxyServerListService.this, 0,   
		                    new Intent(RsyncProxyServerListService.this, this.getClass()), 0);  
		            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);  
		            am.set(AlarmManager.RTC, now + 10*1000, alarmSender);  
				}catch (Exception e) {
					Log.d(LOG_TAG, e.getMessage(), e);
				}
	              
	            //this.stopSelf(); 
			}

		}
	};

}
