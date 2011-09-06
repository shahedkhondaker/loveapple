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

import java.io.IOException;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import cn.loveapple.client.android.BaseActivity;
import cn.loveapple.client.android.damtomo.listener.FinishActivityListener;
import cn.loveapple.client.android.damtomo.service.HttpService;
import cn.loveapple.client.android.damtomo.service.binder.HttpBinder;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id: DamtomoLoginActivity.java 289 2011-09-04 09:00:33Z hao0323@gmail.com
 *     $
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
	public void onButtonClick(View v) {
		if (mBound) {
			// Call a method from the LocalService.
			// However, if this call were something that might hang, then this
			// request should
			// occur in a separate thread to avoid slowing down the activity
			// performance.
			HttpUriRequest request = new HttpPost("http://www.clubdam.com/app/damtomo/auth/LoginXML.do");
			HttpParams params = new BasicHttpParams();
			params.setParameter("loginId", "loveapple");
			params.setParameter("password", "");
			params.setParameter("procKbn", "1");
			request.setParams(params);
			try {
				//Toast.makeText(this, damtomoLoginService.basicRequest(request, null).toString(), Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
			
		}
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
