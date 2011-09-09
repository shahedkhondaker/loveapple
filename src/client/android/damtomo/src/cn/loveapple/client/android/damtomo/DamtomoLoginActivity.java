/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.sourceforge.jp
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
package cn.loveapple.client.android.damtomo;

import java.util.HashMap;
import java.util.Map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.loveapple.client.android.damtomo.listener.FinishActivityListener;
import cn.loveapple.client.android.damtomo.net.DamtomoApiAsyncTask;
import cn.loveapple.client.android.damtomo.net.bean.Document;
import cn.loveapple.client.android.damtomo.net.http.HttpGetXmlActivity;
import cn.loveapple.client.android.damtomo.service.HttpService;
import cn.loveapple.client.android.damtomo.service.binder.HttpBinder;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 * 
 */
public class DamtomoLoginActivity extends BaseActivity {
	private HttpService damtomoLoginService;
	private boolean mBound = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initView() {
		super.initView();
		Button cancele = (Button) findViewById(R.id.cancele);
		cancele.setOnClickListener(new FinishActivityListener());
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Bind to LocalService
		Intent intent = new Intent(this, HttpService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Unbind from the service
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}

	/**
	 * Called when a button is clicked (the button in the layout file attaches
	 * to this method with the android:onClick attribute)
	 */
	@SuppressWarnings("unchecked")
	public void onLoginButtonClick(View v) {
		if (mBound) {
			// Call a method from the LocalService.
			// However, if this call were something that might hang, then this
			// request should
			// occur in a separate thread to avoid slowing down the activity
			// performance.
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("loginId", ((TextView)findViewById(R.id.loginIdText)).getText());
		params.put("password", ((TextView)findViewById(R.id.passwordText)).getText());
		DamtomoApiAsyncTask task = new DamtomoApiAsyncTask(this);
		Map<String, Object> url = new HashMap<String, Object>(1);
		url.put(DamtomoApiAsyncTask.URL_KEY, getText(R.string.damtomo_login_uri));
		task.execute(url, params);
	}

	public HttpService getService() {
		return damtomoLoginService;
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// We've bound to LocalService, cast the IBinder and get
			// LocalService instance
			HttpBinder binder = (HttpBinder) service;
			damtomoLoginService = (HttpService) binder.getService();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};
}
