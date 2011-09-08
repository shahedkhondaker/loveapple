/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.loveapple.client.android.damtomo.net.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cn.loveapple.client.android.Constant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Roy Clarkson
 * @author Helena Edelson
 * @author Pierre-Yves Ricau
 */
public class HttpGetXmlActivity extends AbstractAsyncListActivity {
	
	protected static final String TAG = HttpGetXmlActivity.class.getSimpleName();
	
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		// when this activity starts, initiate an asynchronous HTTP GET request
		new DownloadStatesTask().execute();
	}
	
	
	//***************************************
    // Private methods
    //*************************************** 
	private void refreshStates(List<Document> states) {	
		if (states == null) {
			return;
		}
		
		StatesListAdapter adapter = new StatesListAdapter(this, states);
		setListAdapter(adapter);
	}
	
	
	//***************************************
    // Private classes
    //***************************************
	private class DownloadStatesTask extends AsyncTask<Void, Void, List<Document>> {
		
		@Override
		protected void onPreExecute() {
			// before the network request begins, show a progress indicator
			showLoadingProgressDialog();
		}
		
		@Override
		protected List<Document> doInBackground(Void... params) {
			try {
				// The URL for making the GET request
				final String url = "http://www.clubdam.com/app/damtomo/auth/LoginXML.do?loginId={loginId}&password={password}&procKbn={procKbn}";

				Map<String, String> reqParams = new HashMap<String, String>();
				reqParams.put("loginId", "loveapple");
				reqParams.put("password", "03232046");
				reqParams.put("procKbn", "1");
				
				
				// Set the Accept header for "application/xml"
				HttpHeaders requestHeaders = new HttpHeaders();
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				requestHeaders.setAccept(acceptableMediaTypes);

				// Populate the headers in an HttpEntity object to use for the
				// request
//				HttpEntity<?> requestEntity = new HttpEntity<Object>("loginId=loveapple&password=03232046&procKbn=1", requestHeaders);
				HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

				// Create a new RestTemplate instance
				RestTemplate restTemplate = new RestTemplate();
				// Perform the HTTP POST request
				ResponseEntity<Document> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Document.class, reqParams);

				Log.d(Constant.LOG_TAG, ToStringBuilder.reflectionToString(responseEntity.getBody()));
				
				// Return the list of states
				Document document = responseEntity.getBody();

				List<Document> list = new ArrayList<Document>();
				list.add(document);
				return list;
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}

			return null;
		}
		
		@Override
		protected void onPostExecute(List<Document> result) {
			// hide the progress indicator when the network request is complete
			dismissProgressDialog();

			// return the list of states
			refreshStates(result);
		}
		
	}
	
}
