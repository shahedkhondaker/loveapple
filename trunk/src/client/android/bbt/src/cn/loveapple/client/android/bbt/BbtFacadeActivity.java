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
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import cn.loveapple.client.android.bbt.R.id;
import cn.loveapple.client.android.bbt.listener.TemperatureSelectedListener;
import cn.loveapple.client.android.database.TemperatureDao;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.database.impl.TemperatureDaoImpl;

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
	/**
	 * 
	 * {@inheritDoc}
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for(int i = 35; i < 43; i++){
        	adapter.add(String.valueOf(i));
        }
        Spinner temperature = (Spinner) findViewById(id.temperature);
        temperature.setAdapter(adapter);
        
        // デフォルトを設定
        temperature.setSelection(2);
        
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        temperature.setOnItemSelectedListener(new TemperatureSelectedListener(this));
        
        Button submit = (Button) findViewById(id.submit);
        submit.setOnClickListener(this);
        
        dao = new TemperatureDaoImpl(this);
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
		entity.setDate("testdate");
		try{
		dao.save(entity);
		TemperatureEntity result = dao.findByDate("testdate");
		
		Toast.makeText(this, "submit!!" 
				+ "date:" + result.getDate(), Toast.LENGTH_LONG).show();
		}catch (Exception e) {
			Toast.makeText(this, "Exception!!" 
					+ e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}
}
