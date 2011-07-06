/*
 * $HeadURL:$
 * $Author:$
 * $Revision:$
 * $Date:$
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
package cn.loveapple.client.android.bbt;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import cn.loveapple.client.android.LoveappleHealthDatabaseOpenHelper;
import cn.loveapple.client.android.bbt.R.id;
import cn.loveapple.client.android.bbt.listener.TemperatureSelectedListener;
import cn.loveapple.client.android.database.TemperatureDao;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.database.impl.TemperatureDaoImpl;
import cn.loveapple.client.android.util.DateUtil;

/**
 * Loveapple基礎体温(Android版)ファサードアクティビティ
 * 
 * @author $Author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 *
 */
public class BbtFacadeActivity extends Activity implements OnClickListener {
	private TemperatureDao dao;
	private PackageInfo packageInfo;
	private String activityName;
	private String today;
	
	/**
	 * 初期化を行う
	 */
	private void init(){
		activityName = this.getClass().getName();
		try{
			packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
		}catch (NameNotFoundException e) {
			Log.e(activityName, e.getMessage(), e);
			throw new RuntimeException(e);
		}
		today = DateUtil.toDateString();
		dao = new TemperatureDaoImpl(new LoveappleHealthDatabaseOpenHelper(this, null, packageInfo.versionCode));
	}
	
	public void initView(){
		setContentView(R.layout.main);
		
		// 直近の体温情報を取得
		TemperatureEntity entity = dao.findByDate(today);
		
		// 体温セレクトボックスの初期化
        Spinner temperature = (Spinner) findViewById(id.temperature);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for(int i = 35,j=0; i < 43; i++,j++){
        	if(entity!= null && entity.getTemperature() != null){
        		if(Math.abs(entity.getTemperature()) == i){
        			temperature.setSelection(j);
        		}
        	}
        	adapter.add(String.valueOf(i));
        }
        temperature.setAdapter(adapter);
        
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        temperature.setOnItemSelectedListener(new TemperatureSelectedListener(this));
        
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
        
        // 表示画面の初期化
        initView();
        
        Button submit = (Button) findViewById(id.submit);
        submit.setOnClickListener(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
	@Override
	public void onClick(View v) {
		EditText temperatureText = (EditText) findViewById(id.temperatureText);
		Spinner temperature = (Spinner) findViewById(id.temperature);
		CheckBox coitus = (CheckBox)findViewById(id.coitus);
		CheckBox menstruation = (CheckBox)findViewById(id.menstruation);
		CheckBox dysmenorrhea = (CheckBox) findViewById(id.dysmenorrhea);
		Spinner leukorrhea = (Spinner) findViewById(id.leukorrhea);

		TemperatureEntity entity = new TemperatureEntity();
		entity.setCoitusFlg(coitus.isChecked()?"1":"0");
		entity.setDate("testdate2");
		try{
			dao.save(entity);
			TemperatureEntity result = dao.findByDate("testdate2");
		
			Toast.makeText(this, "submit!!" 
					+ "date:" + result.getDate(), Toast.LENGTH_LONG).show();
		}catch (Exception e) {
			Toast.makeText(this, "Exception!!" 
					+ e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
}
