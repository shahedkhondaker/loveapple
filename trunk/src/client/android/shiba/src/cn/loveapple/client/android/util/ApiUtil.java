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
package cn.loveapple.client.android.util;

import static cn.loveapple.client.android.Constant.LOG_TAG;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;
import android.util.Log;

public class ApiUtil {
	public static String getHttpBody(String url, PackageManager packageManager) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		
		
		String body = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		// SYSTEM INFO
		params.setParameter("", "");
		params.setParameter("", "");
		params.setParameter("", "");
		HttpConnectionParams.setConnectionTimeout(params, 1000);
		HttpConnectionParams.setSoTimeout(params, 1000);
		HttpPost httpRequest = new HttpPost(url);
		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(httpRequest);
		} catch (Exception e) {
			Log.e(LOG_TAG, "http response execute failed.", e);
			return null;
		}
		if (httpResponse != null
				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();

			try {
				body = EntityUtils.toString(httpEntity);
			} catch (Exception e) {
				Log.e(LOG_TAG, "get http response body failed.", e);
				return null;
			} finally {
				try {
					httpEntity.consumeContent();
				} catch (IOException e) {}
			}
		}
		httpClient.getConnectionManager().shutdown();

		return body;
	}
}
