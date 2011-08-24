package cn.loveapple.client.android.shiba.listener;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import cn.loveapple.client.android.shiba.ShibaFacadeActivity;

public class RequestListener implements OnClickListener, OnKeyListener {
	private WebView webView;
	private AutoCompleteTextView address;
	
	public RequestListener(WebView webView, AutoCompleteTextView address){
		this.webView = webView;
		this.address = address;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClick(View v) {
		final String uriString = address.getText().toString();
		webView.loadUrl(uriString);
		webView.requestFocus();
	}
	
	/**
	 * Enterキーを押下する場合、WEBアクセスを行う
	 */
	@Override
	public boolean onKey(View view, int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_ENTER){
			final String uriString = address.getText().toString();
			webView.loadUrl(uriString);
			webView.requestFocus();
		}
		((ShibaFacadeActivity)view.getContext()).setButtonEnabled();
		return false;
	}
}
