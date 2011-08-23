package cn.loveapple.client.android.shiba.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;

public class RequestListener implements OnClickListener {
	private WebView webView;
	private AutoCompleteTextView address;
	
	public RequestListener(WebView webView, AutoCompleteTextView address){
		this.webView = webView;
		this.address = address;
	}
	@Override
	public void onClick(View v) {
		final String uriString = address.getText().toString();
		webView.loadUrl(uriString);
		webView.requestFocus();
	}

}
