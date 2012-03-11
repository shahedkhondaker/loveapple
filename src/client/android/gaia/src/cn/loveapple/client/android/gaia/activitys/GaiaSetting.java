/*
 * $HeadURL: https://loveapple.googlecode.com/svn/trunk/src/client/android/gaia/src/cn/loveapple/android/client/gaia/activitys/GaiaSetting.java $
 * $Author: hao0323@gmail.com $
 * $Revision: 374 $
 * $Date: 2012-03-11 14:09:21 +0900 (日, 11 3 2012) $
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
package cn.loveapple.client.android.gaia.activitys;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;
import cn.loveapple.client.android.gaia.R;

/**
 * オプションメニューを表示するアクティビティ
 * 
 * @author $Author: hao0323@gmail.com $
 * @version $Revision: 374 $
 * @date $Date: 2012-03-11 14:09:21 +0900 (日, 11 3 2012) $
 * @id $Id: GaiaSetting.java 374 2012-03-11 05:09:21Z hao0323@gmail.com $
 */
public class GaiaSetting extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//xmlで定義した設定用の内容を読み込み
		addPreferencesFromResource(R.xml.setting);
	}

	public static boolean getIsEnglish(Context context) {
		Toast.makeText(context, "Option", Toast.LENGTH_LONG).show();
		//languageキーで設定を読み出し
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("language", false);
	}
}
