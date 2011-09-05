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
package cn.loveapple.client.android.damtomo.service;

import java.util.Map;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.loveapple.client.android.damtomo.service.binder.HttpBinder;

/**
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id: FinishActivityListener.java 289 2011-09-04 09:00:33Z
 *     hao0323@gmail.com $
 * 
 */
public class HttpService extends Service {

	private final HttpBinder httpBinder;
	private final Random mGenerator;
	public HttpService(){
		httpBinder = new HttpBinder(this);
		mGenerator = new Random();
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return httpBinder;
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public String basicRequest(String url, Map<String, Object> params){
		return null;
	}

	public int getRandomNumber() {
		return mGenerator.nextInt(100);
	}
}
