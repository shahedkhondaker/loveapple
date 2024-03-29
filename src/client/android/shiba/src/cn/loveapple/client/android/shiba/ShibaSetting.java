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
package cn.loveapple.client.android.shiba;

import static cn.loveapple.client.android.util.DateUtil.isTestTimeOver;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;
import cn.loveapple.client.android.shiba.database.entity.ProxyServer;
import cn.loveapple.client.android.shiba.listener.SetProxyServerOnPreferenceListenter;

/**
 * オプションメニューを表示するアクティビティ
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class ShibaSetting extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//xmlで定義した設定用の内容を読み込み
		addPreferencesFromResource(R.xml.shiba_setting);
		
		// プロキシサーバテキストボックスのリスナーを設定
		ListPreference proxy_server = (ListPreference) findPreference("proxy_server");
		proxy_server.setOnPreferenceChangeListener(new SetProxyServerOnPreferenceListenter(this));
		//proxy_server.setOnPreferenceClickListener(onPreferenceClickListener)
		EditTextPreference http_proxy_server = (EditTextPreference) findPreference("http_proxy_server");
		http_proxy_server.setOnPreferenceChangeListener(new SetProxyServerOnPreferenceListenter(this));
		
		//テキスト版のプロキシサーバリストを生成
		ProxyServerAdapter proxyServerAdapter = new ProxyServerAdapter(this, R.layout.list_proxy_server);
		int listCount = proxyServerAdapter.getCount();
		//
		if(listCount < 1){
			Toast.makeText(this, R.string.invalidProxyList, Toast.LENGTH_LONG).show();
		}
		
		String[] entries = new String[listCount];
		String[] entriesName = new String[listCount];
		for (int i = 0; i < listCount; i++) {
			ProxyServer proxyServer = proxyServerAdapter.getProxyServerList().getProxyServerList().get(i);
			entries[i] = proxyServer.getHost();
			entriesName[i] = proxyServer.getLocal() + ":" + proxyServer.getHost();
		}
		
		proxy_server.setEntries(entriesName);
		proxy_server.setEntryValues(entries);
		
		
	}
	
	
	public static boolean ableHttpProxy(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("able_http_proxy", false);
	}
	public static boolean ableProxy(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("able_proxy", false);
	}
	public static String getHttpProxyServerHost(Context context){
		if(!ableHttpProxy(context) || isTestTimeOver()){
			return null;
		}
		return PreferenceManager.getDefaultSharedPreferences(context).getString(
				"http_proxy_server",
				context.getResources().getStringArray(R.array.http_proxy_server_adds)[0]);
	}
	public static String getProxyServerHost(Context context){
		if(!ableProxy(context) || isTestTimeOver()){
			return null;
		}
		return PreferenceManager.getDefaultSharedPreferences(context).getString(
				"proxy_server",
				context.getResources().getStringArray(R.array.proxy_server_adds)[0]);
	}
}
