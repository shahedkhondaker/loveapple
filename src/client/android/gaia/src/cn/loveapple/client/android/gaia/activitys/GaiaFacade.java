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