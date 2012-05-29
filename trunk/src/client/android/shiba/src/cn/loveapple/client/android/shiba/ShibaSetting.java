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

import static cn.loveapple.client.android.Constant.LOG_TAG;
import static cn.loveapple.client.android.util.DateUtil.*;

import java.util.Calendar;

import cn.loveapple.client.android.shiba.listener.SetProxyServerOnPreferenceListenter;
import cn.loveapple.client.android.shiba.service.RsyncProxyServerListService;

import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

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
		EditTextPreference proxy_server = (EditTextPreference) findPreference("proxy_server");
		proxy_server.setOnPreferenceChangeListener(new SetProxyServerOnPreferenceListenter(this));
		EditTextPreference http_proxy_server = (EditTextPreference) findPreference("http_proxy_server");
		http_proxy_server.setOnPreferenceChangeListener(new SetProxyServerOnPreferenceListenter(this));
		
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
