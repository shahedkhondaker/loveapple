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
package cn.loveapple.client.android.damtomo;

import static cn.loveapple.client.android.Constant.*;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import cn.loveapple.client.android.ActivityLabel;
import cn.loveapple.client.android.damtomo.listener.OpenLoginListener;
import cn.loveapple.client.android.util.StringUtils;

/**
 * アクティビティの基底クラス。
 * 共通な初期化処理などを行う。
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BaseActivity extends Activity implements AsyncActivity, ActivityLabel {

	/**
	 * 処理中プロセス
	 */
	private ProgressDialog progressDialog;
	
	/**
	 * 処理が破棄されたかどうかを表すフラグ
	 */
	private boolean destroyed = false;
	
	/**
	 * システムパッケージ情報
	 */
	protected PackageInfo packageInfo;
	/**
	 * ヘルプメニューフラグ値
	 */
	protected static final int MENU_HELP = 0;
	/**
	 * 設定メニューフラグ値
	 */
	protected static final int MENU_OPT = 1;
	/**
	 * 画面ディスプレ
	 */
	protected Display display;
	/**
	 * 初期ダウン座標：X
	 */
	protected float downX = 0;
	/**
	 * 初期ダウン座標：Y
	 */
	protected float downY = 0;
	
	/**
	 * 初期化を行う
	 */
	protected void init(){

    	WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    	display = wm.getDefaultDisplay();
    	
		try{
			packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
		}catch (NameNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
	protected void onResume(){
		super.onResume();
        
        initView();
        initVisibility();
	}
	
	/**
	 * 表示しない場合の共通処理：<br>
	 * 1.リソース開放
	 */
	@Override
	protected void onPause(){
		super.onPause();
	}
	
	/**
	 * 表示/非表示初期化
	 */
	protected void initVisibility(){
		
	}
    /**
     * {@linkplain #onResume()}が実行される際、画面に表示するコンポーネントの初期化を行う
     */
    protected void initView(){
    	Button openLogin = (Button) findViewById(R.id.openLogin);
    	if(openLogin != null){
    		openLogin.setOnClickListener(new OpenLoginListener());
    	}
    }
    

	/**
	 * 画面を閉じるボタンを原則的に用意しないので、
	 * 横にドロップしたら、アプリを終了する。
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			downX = event.getX();
			downY = event.getY();
		}
		if(event.getAction() == MotionEvent.ACTION_UP && 100 <= Math.abs(downX - event.getX())){
			finish();
			return super.onTouchEvent(event);
			
		}
		return super.onTouchEvent(event);

	}

	/**
	 * 全画面共通のオプションメニュー
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		menu.add(0, MENU_OPT, 0, getText(R.string.setting)).setIcon(
//				android.R.drawable.ic_menu_preferences);
//		menu.add(0, MENU_HELP, 0, getText(R.string.help)).setIcon(
//				android.R.drawable.ic_menu_help);

		return true;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		Intent intent = new Intent();
//		switch (item.getItemId()) {
//		case MENU_HELP:
//			intent.setClassName(this, HelpInfoActivity.class.getName());
//			startActivity(intent);
//			return true;
//		case MENU_OPT:
//			intent.setClassName(this, BbtSetting.class.getName());
//			startActivity(intent);
//			return true;
//		}

		return true;
	}
	
	/**
	 * ログイン中かどうかを確認
	 * @return
	 */
	protected boolean isLogin(){
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MainApplication getApplicationContext() {
		return (MainApplication) super.getApplicationContext();
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		destroyed = true;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void showLoadingProgressDialog() {
		this.showProgressDialog(getText(R.string.loading));
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void showProgressDialog(CharSequence message) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setIndeterminate(true);
		}
		
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void dismissProgressDialog() {
		if (progressDialog != null && !destroyed) {
			progressDialog.dismiss();
		}
	}
}
