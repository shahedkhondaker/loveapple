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
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
	private String today;
	private Date todayDate;
	private List<String> temperatureList;
	private static final int MENU_HELP = 0;
	private static final int MENU_OPT = 1;
	
	/**
	 * 初期化を行う
	 */
	private void init(){
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
	 * 各コンポーネント表示するための初期化を行う
	 */
	private void initView(){
		
		setContentView(R.layout.main);
		
		// 直近の体温情報を取得
		TemperatureEntity entity = dao.findByDate(today);

		if(entity == null){
			entity = new TemperatureEntity();
		}
		
		// 分量を表すアダプター
		ArrayAdapter measureListAdapter = ArrayAdapter.createFromResource(this, R.array.measureList, android.R.layout.simple_spinner_item);

		// 体温の初期化
       initTemperature(entity);
       
       // 下り物セレクトボックス初期化
       Spinner leukorrhea = (Spinner) findViewById(id.leukorrhea);
       leukorrhea.setAdapter(measureListAdapter);
       if(entity.getLeukorrhea() != null){
    	   for (int i = 0; i < 3; i++) {
				if(String.valueOf(i).equals(entity.getLeukorrhea())){
					leukorrhea.setSelection(i);
					break;
				}
			}
		}

		// 生理出血量
		Spinner menstruationLevel = (Spinner) findViewById(id.menstruationLevel);
		menstruationLevel.setAdapter(measureListAdapter);
		if(entity.getMenstruationLevel() != null){
			for (int i = 0; i < 3; i++){
				if(String.valueOf(i).equals(entity.getMenstruationLevel())){
					menstruationLevel.setSelection(i);
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
        
		TextView headMsg = (TextView) findViewById(id.headMsg);
		headMsg.setText(String.format(getText(R.string.hello).toString(), todayDate));
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
        int selectionPoint = 0;
        for(int i = 0; i < temperatureList.size(); i++){
        	String temperatureValue = temperatureList.get(i);
        	tempperatureAdapter.add(temperatureValue);
        	if(entity.getTemperature() != null){
        		if(entity.getTemperature().intValue() == Integer.parseInt(temperatureValue)){
        			selectionPoint = i;
        		}
        	}
        }
        temperature.setAdapter(tempperatureAdapter);        
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        temperature.setOnItemSelectedListener(new TemperatureSelectedListener(this));
        temperature.setSelection(selectionPoint);
        
        if(entity.getTemperature() == null){
        	return ;
        }        
        // 体温テキストを初期化
        EditText temperatureText = (EditText) findViewById(id.temperatureText);
        double value = entity.getTemperature() == null? 0 : entity.getTemperature();
        if(value == 0.0){
        	temperatureText.setText("0");
        	return ;
        }
        value = (value - entity.getTemperature().intValue())*100;
        temperatureText.setText(String.valueOf(Math.abs(value)));
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
    }

    /**
     * 
     * {@inheritDoc}
     */
	@Override
	public void onClick(View v) {
		save();
	}
	
	private TemperatureEntity save(){
		TemperatureEntity result = null;
		try{
			dao.save(createEntity());
			result = dao.findByDate(today);
			
			Toast.makeText(this, "submit!!" 
					+ "date:" + result, Toast.LENGTH_LONG).show();
			
			return result;
		}catch (Exception e) {
			Toast.makeText(this, "Exception!!" 
					+ e.getMessage(), Toast.LENGTH_LONG).show();
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	private TemperatureEntity createEntity(){
		TemperatureEntity entity = new TemperatureEntity();
		EditText temperatureText = (EditText) findViewById(id.temperatureText);
		Spinner temperature = (Spinner) findViewById(id.temperature);
		CheckBox coitus = (CheckBox)findViewById(id.coitus);
		CheckBox menstruation = (CheckBox)findViewById(id.menstruation);
		CheckBox dysmenorrhea = (CheckBox) findViewById(id.dysmenorrhea);
		Spinner leukorrhea = (Spinner) findViewById(id.leukorrhea);
		Spinner menstruationLevel = (Spinner) findViewById(id.menstruationLevel);

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
		entity.setMenstruationLevel(String.valueOf(menstruationLevel.getSelectedItemPosition()));
		
		
		return entity;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
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
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0, MENU_OPT, 0, getText(R.string.setting))
			.setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(0, MENU_HELP, 0, getText(R.string.help))
			.setIcon(android.R.drawable.ic_menu_help);
		
		return true;
	}
	
	/**
	 * 表示/非表示の初期化<br>
	 * <b>ルール：</b><br>
	 * 1．生理痛、出血量、下り物がオプションで設定される場合のみ表示する。
	 * 2．オプションで表示設定される場合、生理を選択される場合、生理痛、出血量が表示される。
	 * 3．生理を選択される場合、下り物が非表示する。
	 * 
	 */
	public void initVisibility(){
		
	}
	/**
	 * 画面表示の初期化を行う
	 */
	@Override
	public void onResume(){
		super.onResume();
        
        // 表示画面の初期化
        initView();
        
        // 表示/非表示初期化
        initVisibility();
        
        Button submit = (Button) findViewById(id.submit);
        submit.setOnClickListener(this);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		save();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onDestroy(){ 
		super.onDestroy();
		if(dao != null){
			dao.destory();
		}
	}
}
