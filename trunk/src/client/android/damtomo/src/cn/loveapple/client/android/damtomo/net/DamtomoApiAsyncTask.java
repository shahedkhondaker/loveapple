package cn.loveapple.client.android.damtomo.net;

import static cn.loveapple.client.android.Constant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import cn.loveapple.client.android.damtomo.R;
import cn.loveapple.client.android.damtomo.net.bean.Document;

public class DamtomoApiAsyncTask extends AsyncTask<Map<String, Object>, Integer, Document> {

	public static final String URL_KEY = "___url";
	private Activity activity;
	private ProgressDialog progressDialog;
	
	/**
	 * 
	 * @param activity
	 */
	public DamtomoApiAsyncTask(Activity activity){
		this.activity = activity;
		progressDialog = new ProgressDialog(this.activity);
		progressDialog.setIndeterminate(true);
	}
	
	/**
	 * 
	 * @param activity
	 * @param progressDialog
	 */
	public DamtomoApiAsyncTask(Activity activity, ProgressDialog progressDialog){
		this.activity = activity;
		this.progressDialog = progressDialog;
	}
	
	/**
	 * 
	 * @param params <code>0</code>番目はURL、<code>1</code>番目はパラメータ、<code>2</code>番目はヘッダー情報
	 */
	@Override
	protected Document doInBackground(Map<String, Object>... params) {
		try {
			
			// Set the Accept header for "application/xml"
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_XML);
			requestHeaders.setAccept(acceptableMediaTypes);

			// Populate the headers in an HttpEntity object to use for the
			// request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();

			// Perform the HTTP POST request
			ResponseEntity<Document> responseEntity = restTemplate.postForEntity(params[0].get(URL_KEY).toString(), requestEntity, Document.class, params[1]);
			//ResponseEntity<Document> responseEntity = restTemplate.exchange(params[0].toString(), HttpMethod.POST, requestEntity, Document.class, params[1]);

			Log.d(LOG_TAG, ReflectionToStringBuilder.toString(responseEntity.getBody()));
			
			// Return the list of states
			Document document = responseEntity.getBody();

			return document;
		} catch (Exception e) {
			Log.e(LOG_TAG, e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void onPreExecute() {
		progressDialog.setMessage(activity.getText(R.string.loading));
		progressDialog.show();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void onPostExecute(Document result) {
		progressDialog.dismiss();
		Log.d(LOG_TAG, "Document:" + ReflectionToStringBuilder.toString(result));
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
    protected void onProgressUpdate(Integer... progress) {
		progressDialog.incrementProgressBy(progress[0]);
    }

}
