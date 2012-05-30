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

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import cn.loveapple.client.android.ApiHelper;

/**
 * プロキシサーバアドレスデータ同期のサービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class RsyncProxyServerListService extends Service {

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
				Log.d(LOG_TAG,
						"in service."
								+ ToStringBuilder.reflectionToString(ApiHelper
										.getProxyServerListStorage(getPackageManager())));
			}

		}
	};

}
