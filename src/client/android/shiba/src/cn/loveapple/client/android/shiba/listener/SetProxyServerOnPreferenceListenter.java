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
package cn.loveapple.client.android.shiba.listener;

import cn.loveapple.client.android.shiba.R;
import android.app.Activity;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

/**
 * プロキシサーバ、又は、HTTPプロキシサーバを設定した際に実行するリスナー
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class SetProxyServerOnPreferenceListenter implements
		OnPreferenceChangeListener {
	private Activity active;
	
	public SetProxyServerOnPreferenceListenter(Activity active){
		this.active = active;
	}

	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if(newValue != null){
			preference.setSummary(
					active.getResources().getString(R.string.proxy_server) 
					+ " : " + newValue.toString());
			return true;
		}
		
		return false;
	}

}
