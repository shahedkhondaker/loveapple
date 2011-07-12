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

import static cn.loveapple.client.android.Constant.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.MethodNotSupportedException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	private List<String> temperatureList;
	private static final int MENU_HELP = 0;
	private static final int MENU_OPT = 1;
	
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
		
		temperatureList = new ArrayList<String>(8);
		for(int i = 35; i < 43; i++){
			temperatureList.add(String.valueOf(i));
		}
	}
	
	private void initView(){
		
		setContentView(R.layout.main);
		
		// 直近の体温情報を取得
		TemperatureEntity entity = dao.findByDate(today);

		// データ取得できない場合、初期設定を終了
		if(entity == null){
			return;
		}
		
		// 体温の初期化
       initTemperature(entity);
       

       // 下り物セレクトボックス初期化
       Spinner leukorrhea = (Spinner) findViewById(id.leukorrhea);
       ArrayAdapter leukorrheAdapter = ArrayAdapter.createFromResource(this, R.array.measureList, android.R.layout.simple_spinner_item);
		leukorrheAdapter.setDropDownViewResource(R.layout.leukorrhea_spinner_item);
       leukorrhea.setAdapter(leukorrheAdapter);
		if(entity.getLeukorrhea() != null){
			for (int i = 0; i < 3; i++) {
				if(String.valueOf(i).equals(entity.getLeukorrhea())){
					leukorrhea.setSelection(i);
					break;
				}
			}
		}
        
		// セックス
		CheckBox coitus = (CheckBox)findViewById(id.coitus);
		coitus.setChecked(FLG_ON.equals(entity.getCoitusFlg()));
		
		// 生理
		CheckBox menstruation = (CheckBox)findViewById(id.menstruation);
		menstruation.setChecked(FLG_ON.equals(entity.getMenstruationFlg()));
		
		// 生理痛
		CheckBox dysmenorrhea = (CheckBox) findViewById(id.dysmenorrhea);
		dysmenorrhea.setChecked(FLG_ON.equals(entity.getDysmenorrheaFlg()));
        
	}
	
	/**
	 * 体温セレクトボックスを初期化
	 * @param entity
	 */
	private void initTemperature(TemperatureEntity entity){
		// 温度セレクトボックスの初期化
		Spinner temperature = (Spinner) findViewById(id.temperature);
		ArrayAdapter<String> tempperatureAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        tempperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for(int i = 0; i < temperatureList.size(); i++){
        	String temperatureValue = temperatureList.get(i);
        	if(entity.getTemperature() != null){
        		if(Math.abs(entity.getTemperature()) == Integer.parseInt(temperatureValue)){
        			temperature.setSelection(i);
        		}
        	}
        	tempperatureAdapter.add(temperatureValue);
        }
        temperature.setAdapter(tempperatureAdapter);        
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        temperature.setOnItemSelectedListener(new TemperatureSelectedListener(this));
        
        if(entity.getTemperature() != null){
        	return ;
        }
        // 体温テキストを初期化
        double value = entity.getTemperature();
        value = Math.round(value*100);
        EditText temperatureText = (EditText) findViewById(id.temperatureText);
        String valueStr = String.valueOf(value);
        temperatureText.setText(valueStr.substring(valueStr.length() - 2));
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
		try{
			dao.save(createEntity());
			TemperatureEntity result = dao.findByDate(today);
		
			Toast.makeText(this, "submit!!" 
					+ "date:" + result, Toast.LENGTH_LONG).show();
		}catch (Exception e) {
			Toast.makeText(this, "Exception!!" 
					+ e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
	
	private TemperatureEntity createEntity(){
		TemperatureEntity entity = new TemperatureEntity();
		EditText temperatureText = (EditText) findViewById(id.temperatureText);
		Spinner temperature = (Spinner) findViewById(id.temperature);
		CheckBox coitus = (CheckBox)findViewById(id.coitus);
		CheckBox menstruation = (CheckBox)findViewById(id.menstruation);
		CheckBox dysmenorrhea = (CheckBox) findViewById(id.dysmenorrhea);
		Spinner leukorrhea = (Spinner) findViewById(id.leukorrhea);

		// 温度の選択値
		StringBuilder temperatureValue = new StringBuilder(10);
		temperatureValue.append(temperatureList.get(temperature.getSelectedItemPosition()));
		temperatureValue.append('.');
		temperatureValue.append(temperatureText.getText());
	
		
		entity.setCoitusFlg(coitus.isChecked()?FLG_ON:FLG_OFF);
		entity.setTemperature(new Double(temperatureValue.toString()));
		entity.setDate(today);
		entity.setMenstruationFlg(menstruation.isChecked()?FLG_ON:FLG_OFF);
		entity.setDysmenorrheaFlg(dysmenorrhea.isChecked()?FLG_ON:FLG_OFF);
		entity.setLeukorrhea(String.valueOf(leukorrhea.getSelectedItemPosition()));
		
		
		return entity;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case MENU_HELP:
			Toast.makeText(this, "HELP", Toast.LENGTH_LONG).show();

			
			return true;
		case MENU_OPT:
			Toast.makeText(this, "Option", Toast.LENGTH_LONG).show();
			
			Intent intent = new Intent();
			intent.setClassName(this, BbtSetting.class.getName());
			startActivity(intent);
			return true;
		}
		
		return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0, MENU_OPT, 0, "Option").setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(0, MENU_HELP, 0, "HELP").setIcon(android.R.drawable.ic_menu_help);
		
		return true;
	}
}
