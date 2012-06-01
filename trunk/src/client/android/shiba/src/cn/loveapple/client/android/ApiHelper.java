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

import static cn.loveapple.client.android.Constant.LOG_TAG;
import static cn.loveapple.client.android.util.DateUtil.parseDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	
	private static ProxyServerList proxyServerListStorage = null;
	private static final String JSON_UPDATE_DATE_PATTERN = "yyyy/MM/dd HH:mm";
	
	/**
	 * 
	 * @param packageManager
	 * @return
	 */
	public static ProxyServerList getProxyServerListStorage(){
		return proxyServerListStorage;
	}

	public static ProxyServerList reloadProxyServerList(PackageManager packageManager) throws JSONException {

		String json = ApiUtil.getHttpBody(PROXY_SERVER_LIST_JSON_URL, packageManager);

		proxyServerListStorage = new ProxyServerList();
		JSONObject jsonObject = new JSONObject(json);
		JSONArray hostArray = jsonObject.getJSONArray("hosts");
		
		
		// 最終更新日時を設定
		String lastUpdate = jsonObject.getString("update").trim();
		int updateStrLength = lastUpdate.length();
		String timeZone = lastUpdate.substring(updateStrLength - 4).trim();
		lastUpdate = lastUpdate.substring(0, updateStrLength - 4).trim();
		proxyServerListStorage.setLastUpdate(parseDate(lastUpdate, JSON_UPDATE_DATE_PATTERN, timeZone));
		
		// ホストリストを設定
		List<ProxyServer> hostList = new ArrayList<ProxyServer>(hostArray.length());
		for(int i = 0; i < hostArray.length(); i++){
			JSONObject hostObj = hostArray.getJSONObject(i);
			ProxyServer host = new ProxyServer();
			host.setLocal(hostObj.getString("local"));
			host.setType(hostObj.getString("type"));
			host.setHost(hostObj.getString("host"));
			hostList.add(host);
		}
		proxyServerListStorage.setProxyServerList(hostList);
		return proxyServerListStorage;
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
