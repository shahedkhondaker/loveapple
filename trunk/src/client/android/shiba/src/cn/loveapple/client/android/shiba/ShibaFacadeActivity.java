package cn.loveapple.client.android.shiba;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import cn.loveapple.client.android.bbt.view.LoveappleWebViewClient;

public class ShibaFacadeActivity extends BaseActivity {
	private WebView web_;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		web_ = (WebView) findViewById(R.id.web);
		final Button button = (Button) findViewById(R.id.GoButton);
		final OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText edit = (EditText) findViewById(R.id.UriText);
				final String uriString = edit.getText().toString();
				web_.loadUrl(uriString);
				web_.requestFocus();
			}
		};
		button.setOnClickListener(listener);

		web_.setWebViewClient(new LoveappleWebViewClient());
    }
}