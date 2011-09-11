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
package cn.loveapple.client.android.damtomo.net.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cn.loveapple.client.android.Constant;
import cn.loveapple.client.android.damtomo.net.bean.Document;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/**
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
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

				Log.d(Constant.LOG_TAG, ReflectionToStringBuilder.toString(responseEntity.getBody()));
				
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
