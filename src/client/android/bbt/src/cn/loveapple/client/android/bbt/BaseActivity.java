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
package cn.loveapple.client.android.bbt;

import static cn.loveapple.client.android.Constant.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.loveapple.client.android.LoveappleHealthDatabaseOpenHelper;
import cn.loveapple.client.android.database.TemperatureDao;
import cn.loveapple.client.android.database.impl.TemperatureDaoImpl;
import cn.loveapple.client.android.util.DateUtil;
import cn.loveapple.client.android.util.StringUtils;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

/**
 * アクティビティの基底クラス。
 * 共通な初期化処理などを行う。
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BaseActivity extends Activity {
	protected TemperatureDao dao;
	protected String today;
	protected Date todayDate;
	protected PackageInfo packageInfo;
	protected List<String> temperatureList;
	protected static final int MENU_HELP = 0;
	protected static final int MENU_OPT = 1;

	/**
	 * 初期化を行う
	 */
	protected void init(){
		try{
			packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
		}catch (NameNotFoundException e) {
			throw new RuntimeException(e);
		}
		todayDate = new Date();
		today = DateUtil.toDateString(todayDate);
		dao = new TemperatureDaoImpl(new LoveappleHealthDatabaseOpenHelper(this, null, packageInfo.versionCode));
		
		temperatureList = new ArrayList<String>(8);
		for(int i = 35; i < 43; i++){
			temperatureList.add(String.valueOf(i));
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 初期化
        init();
        
        // レイアウトを初期化する
        String layoutName = StringUtils.convertClassName2ResourceName(this.getClass().getSimpleName());
        Log.d(LOG_TAG, this.getClass().getSimpleName() + " is onCreate. layout name:" + layoutName);
        try {
			Field layout = R.layout.class.getDeclaredField(layoutName);
			setContentView(layout.getInt(null));
		} catch (Exception e) {
			// 自らレイアウトを定義することはあるので、エラーが発生する場合、握り潰す。
			Log.d(LOG_TAG, e.getMessage(), e);
		}
    }

	/**
	 * 画面表示の初期化を行う
	 */
	@Override
	public void onResume(){
		super.onResume();
        
        initView();
        initVisibility();
        
	}
	
	/**
	 * 表示/非表示初期化
	 */
	protected void initVisibility(){
		
	}
    /**
     * 表示画面の初期化
     */
    protected void initView(){
    	
    }
}
