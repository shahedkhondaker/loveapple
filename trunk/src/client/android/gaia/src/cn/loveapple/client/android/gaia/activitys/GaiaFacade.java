/*
 * $HeadURL: https://loveapple.googlecode.com/svn/trunk/src/client/android/gaia/src/cn/loveapple/client/android/gaia/activitys/GaiaEventList.java $
 * $Author: hao0323@gmail.com $
 * $Revision: 397 $
 * $Date: 2012-03-18 16:51:39 +0900 (日, 18 3 2012) $
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
package cn.loveapple.client.android.gaia.activitys;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.loveapple.client.android.BaseActivity;
import cn.loveapple.client.android.gaia.R.id;
import cn.loveapple.client.android.gaia.entity.EventEntity;

/**
 * @author loveapple
 * @version $Revision: 397 $
 * @date $Date: 2012-03-18 16:51:39 +0900 (日, 18 3 2012) $
 * @id $Id: GaiaEventList.java 397 2012-03-18 07:51:39Z hao0323@gmail.com $
 *
 */
public class GaiaFacade extends BaseActivity {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void init(){
		super.init();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initView(){
		
		// init event list
		List<EventEntity> eventList = eventLogic.findEventList();
		ArrayAdapter<EventEntity> eventListAdapter = new ArrayAdapter<EventEntity>(this, android.R.layout.simple_list_item_1);
		for(EventEntity event : eventList){
			eventListAdapter.add(event);
		}
		
		ListView eventListView = (ListView) findViewById(id.event_list);
		eventListView.setAdapter(eventListAdapter);
		
		// リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
		eventListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                EventEntity item = (EventEntity) listView.getItemAtPosition(position);
                Toast.makeText(GaiaFacade.this, item.getMessage(), Toast.LENGTH_LONG).show();
            }
		});
		
		// リストビューのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
		eventListView.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                ListView listView = (ListView) parent;
                // 選択されたアイテムを取得します
                EventEntity item = (EventEntity) listView.getSelectedItem();
                Toast.makeText(GaiaFacade.this, item.getLatitude() + ":" + item.getLongitude(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
		});
    }
	
}