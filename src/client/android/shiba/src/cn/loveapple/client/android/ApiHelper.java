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
package cn.loveapple.client.android;

import static cn.loveapple.client.android.Constant.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import cn.loveapple.client.android.shiba.database.entity.ProxyServer;
import cn.loveapple.client.android.util.ApiUtil;

/**
 * プロキシサーバアドレスデータ同期のサービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class ApiHelper {
	private static final String PROXY_SERVER_LIST_JSON_URL = "http://loveapple.sourceforge.jp/serverList.php";

	public static ProxyServerList readProxyServerList(PackageManager packageManager) throws JSONException {

		String json = ApiUtil.getHttpBody(PROXY_SERVER_LIST_JSON_URL, packageManager);
		
		JSONArray jsonArray = new JSONArray(json);
		JSONObject jsonObject = new JSONObject(json);
		Log.d(LOG_TAG, json);
		Log.d(LOG_TAG,ToStringBuilder.reflectionToString(jsonArray));
		Log.d(LOG_TAG,ToStringBuilder.reflectionToString(jsonObject));
		// TODO
		return null;
	}

	public static class ProxyServerList {
		private Date lastUpdate;
		private List<ProxyServer> proxyServerList;

		/**
		 * @return the lastUpdate
		 */
		public Date getLastUpdate() {
			return lastUpdate;
		}

		/**
		 * @param lastUpdate
		 *            the lastUpdate to set
		 */
		public void setLastUpdate(Date lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		/**
		 * @return the proxyServerList
		 */
		public List<ProxyServer> getProxyServerList() {
			return proxyServerList;
		}

		/**
		 * @param proxyServerList
		 *            the proxyServerList to set
		 */
		public void setProxyServerList(List<ProxyServer> proxyServerList) {
			this.proxyServerList = proxyServerList;
		}
	}
}
