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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.loveapple.client.android.bbt.R.id;
import cn.loveapple.client.android.bbt.listener.MessagerOnSeekBarChangeListener;
import cn.loveapple.client.android.bbt.listener.TemperatureSelectedListener;
import cn.loveapple.client.android.bbt.listener.VisibilityOnCheckedChangeListener;
import cn.loveapple.client.android.bbt.listener.VisibilityOnCheckedChangeListener.ViewVisibilityHelper;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.util.ComponentUtil;

/**
 * Loveapple基礎体温(Android版)ファサードアクティビティ
 * 
 * @author $Author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 * 
 */
public class BbtFacadeActivity extends BaseActivity implements OnClickListener {

	/**
	 * 体温：使用数点以降の入力部分
	 */
	private EditText temperatureText;
	/**
	 * 体温：整数部
	 */
	private Spinner temperature;
	/**
	 * セックス
	 */
	private CheckBox coitus;
	/**
	 * 生理
	 */
	private CheckBox menstruation;
	/**
	 * 生理痛
	 */
	private CheckBox dysmenorrhea;
	/**
	 * 下り物
	 */
	private SeekBar leukorrhea;
	/**
	 * 生理の出血量
	 */
	private SeekBar menstruationLevel;
	/**
	 * 送信ボタン
	 */
	private Button submit;
	/**
	 * 下り物を表すラベル
	 */
	private TextView leukorrheaViewMsg;
	/**
	 * 生理の出血量を表すラベル
	 */
	private TextView menstruationLevelViewMsg;
	/**
	 * タイトル
	 */
	private TextView headMsg;
	/**
	 * 生理情報関連項目のブロック
	 */
	private LinearLayout dysmenorrheaHLine;
	/**
	 * 下り物関連項目のブロック
	 */
	private LinearLayout leukorrheaHLine;
	/**
	 * 設定情報
	 */
	private SharedPreferences preferences;
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	protected void initView() {
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		temperatureText = (EditText) findViewById(id.temperatureText);
		temperature = (Spinner) findViewById(id.temperature);
		coitus = (CheckBox) findViewById(id.coitus);
		menstruation = (CheckBox) findViewById(id.menstruation);
		dysmenorrhea = (CheckBox) findViewById(id.dysmenorrhea);
		leukorrhea = (SeekBar) findViewById(id.leukorrhea);
		menstruationLevel = (SeekBar) findViewById(id.menstruationLevel);
		submit = (Button) findViewById(id.submit);
		leukorrheaViewMsg = (TextView) findViewById(id.leukorrheaViewMsg);
		menstruationLevelViewMsg = (TextView) findViewById(id.menstruationLevelViewMsg);
		headMsg = (TextView) findViewById(id.headMsg);
		dysmenorrheaHLine = (LinearLayout) findViewById(R.id.dysmenorrheaHLine);
		leukorrheaHLine = (LinearLayout) findViewById(R.id.leukorrheaHLine);

		// 送信ボタン
		submit.setOnClickListener(this);

		// 直近の体温情報を取得
		TemperatureEntity entity = dao.findByDate(today);

		if (entity == null) {
			entity = new TemperatureEntity();
		}

		String[] measureList = getResources().getStringArray(
				R.array.measureList);
		// 体温の初期化
		initTemperature(entity);

		// 下り物セレクトボックス初期化
		leukorrhea
				.setOnSeekBarChangeListener(new MessagerOnSeekBarChangeListener(
						leukorrheaViewMsg, measureList));
		if (entity.getLeukorrhea() == null) {
			leukorrhea.setProgress(0);
		} else {
			leukorrhea.setProgress(Integer.parseInt(entity.getLeukorrhea()));
		}
		leukorrheaViewMsg.setText(measureList[leukorrhea.getProgress()]);

		// 生理出血量
		menstruationLevel
				.setOnSeekBarChangeListener(new MessagerOnSeekBarChangeListener(
						menstruationLevelViewMsg, measureList));
		if (entity.getMenstruationLevel() == null) {
			menstruationLevel.setProgress(0);
		} else {
			menstruationLevel.setProgress(Integer.parseInt(entity
					.getMenstruationLevel()));
		}
		menstruationLevelViewMsg.setText(measureList[menstruationLevel
				.getProgress()]);

		// セックス
		coitus.setChecked(FLG_ON.equals(entity.getCoitusFlg()));
		

		// 生理
		menstruation.setChecked(FLG_ON.equals(entity.getMenstruationFlg()));
		boolean isViewDysmenorrhea = preferences.getBoolean("dysmenorrhea", false);
		boolean isViewLeukorrhea = preferences.getBoolean("leukorrhea", false);
		boolean isViewMenstruationLevel = preferences.getBoolean("menstruationLevel", false);
		menstruation.setOnCheckedChangeListener(
				new VisibilityOnCheckedChangeListener(
						this,
						new ViewVisibilityHelper[]{ 
								new ViewVisibilityHelper(dysmenorrhea, isViewDysmenorrhea),
								new ViewVisibilityHelper(dysmenorrheaHLine, isViewMenstruationLevel)},
						new ViewVisibilityHelper[]{
								new ViewVisibilityHelper(leukorrheaHLine,isViewLeukorrhea)}
						));
		

		// 生理痛
		dysmenorrhea.setChecked(FLG_ON.equals(entity.getDysmenorrheaFlg()));

		headMsg.setText(String.format(getText(R.string.hello).toString(),
				todayDate));
	}

	/**
	 * 体温セレクトボックスを初期化
	 * 
	 * @param entity
	 */
	private void initTemperature(TemperatureEntity entity) {
		// 温度セレクトボックスの初期化
		ArrayAdapter<String> tempperatureAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item);
		tempperatureAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		int selectionPoint = 0;
		for (int i = 0; i < temperatureList.size(); i++) {
			String temperatureValue = temperatureList.get(i);
			tempperatureAdapter.add(temperatureValue);
			if (entity.getTemperature() != null) {
				if (entity.getTemperature().intValue() == Integer
						.parseInt(temperatureValue)) {
					selectionPoint = i;
				}
			}
		}
		temperature.setAdapter(tempperatureAdapter);
		// スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
		temperature.setOnItemSelectedListener(new TemperatureSelectedListener(
				this));
		temperature.setSelection(selectionPoint);

		if (entity.getTemperature() == null) {
			return;
		}
		// 体温テキストを初期化
		double value = entity.getTemperature() == null ? 0 : entity
				.getTemperature();
		if (value == 0.0) {
			temperatureText.setText("0");
			return;
		}
		value = (value - entity.getTemperature().intValue()) * 100;
		temperatureText.setText(String.valueOf((int)value));
		
		Log.d(LOG_TAG, "init temp:" + entity.getTemperature());
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onClick(View v) {
	//	save();

		Intent intent = new Intent();
		intent.setClassName(this, BbtChartActivity.class.getName());
		startActivity(intent);
	}

	private TemperatureEntity save() {
		TemperatureEntity result = null;
		try {
			dao.save(createEntity());
			result = dao.findByDate(today);

//			Toast.makeText(this, "submit!!" + "date:" + result,
//					Toast.LENGTH_LONG).show();

			return result;
		} catch (Exception e) {
//			Toast.makeText(this, "Exception!!" + e.getMessage(),
//					Toast.LENGTH_LONG).show();
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	private TemperatureEntity createEntity() {
		TemperatureEntity entity = new TemperatureEntity();

		// 温度の選択値
		StringBuilder temperatureValue = new StringBuilder(10);
		temperatureValue.append(temperatureList.get(temperature
				.getSelectedItemPosition()));
		temperatureValue.append('.');
		temperatureValue.append(temperatureText.getText());

		entity.setCoitusFlg(coitus.isChecked() ? FLG_ON : FLG_OFF);
		entity.setTemperature(new Double(temperatureValue.toString()));
		entity.setDate(today);
		entity.setMenstruationFlg(menstruation.isChecked() ? FLG_ON : FLG_OFF);
		entity.setDysmenorrheaFlg(dysmenorrhea.isChecked() ? FLG_ON : FLG_OFF);
		entity.setLeukorrhea(String.valueOf(leukorrhea.getProgress()));
		entity.setMenstruationLevel(String.valueOf(menstruationLevel
				.getProgress()));

		return entity;
	}


	/**
	 * 表示/非表示の初期化<br>
	 * <b>ルール：</b><br>
	 * 1．生理痛、出血量、下り物がオプションで設定される場合のみ表示する。<br>
	 * 2．オプションで表示設定される場合、生理を選択される場合、生理痛、出血量が表示される。 <br>
	 * 3．生理を選択される場合、下り物が非表示する。<br>
	 * 
	 */
	@Override
	protected void initVisibility() {

		boolean isViewDysmenorrhea = preferences.getBoolean("dysmenorrhea", false);
		boolean isViewLeukorrhea = preferences.getBoolean("leukorrhea", false);
		boolean isViewMenstruationLevel = preferences.getBoolean("menstruationLevel", false);
		if(menstruation.isChecked()){
			if(isViewDysmenorrhea){
				ComponentUtil.setVisibleList(this, dysmenorrhea);
			}else{
				ComponentUtil.setGoneList(this, dysmenorrhea);
			}
			if(isViewMenstruationLevel){
				ComponentUtil.setVisibleList(this, dysmenorrheaHLine);
			}else{
				ComponentUtil.setGoneList(this, dysmenorrheaHLine);
			}
			ComponentUtil.setGoneList(this, leukorrheaHLine);
		}else{
			if(isViewLeukorrhea){
				ComponentUtil.setVisibleList(this, leukorrheaHLine);
			}else{
				ComponentUtil.setGoneList(this, leukorrheaHLine);
			}
			ComponentUtil.setGoneList(this, dysmenorrhea);
			ComponentUtil.setGoneList(this, dysmenorrheaHLine);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void onPause(){
		super.onPause();
		save();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}
}
