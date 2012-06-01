/*
 * $HeadURL: https://loveapple.googlecode.com/svn/trunk/src/client/android/shiba/src/cn/loveapple/client/android/shiba/UrlAdapter.java $
 * $Author: hao0323@gmail.com $
 * $Revision: 403 $
 * $Date: 2012-05-15 10:06:53 +0900 (火, 15 5 2012) $
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
package cn.loveapple.client.android.shiba;

import static cn.loveapple.client.android.Constant.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import cn.loveapple.client.android.ApiHelper;
import cn.loveapple.client.android.ApiHelper.ProxyServerList;
import cn.loveapple.client.android.shiba.database.entity.ProxyServer;

/**
 * proxy server
 * 
 * @author loveapple
 * @version $Revision: 403 $
 * @date $Date: 2012-05-15 10:06:53 +0900 (火, 15 5 2012) $
 * @id $Id: UrlAdapter.java 403 2012-05-15 01:06:53Z hao0323@gmail.com $
 * 
 */
public class ProxyServerAdapter extends ArrayAdapter<ProxyServer> implements
		Filterable {

	private ProxyServerList proxyServerList;
	private List<ProxyServer> viewResult;
	private LayoutInflater layoutInflater_;

	public ProxyServerAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		proxyServerList = ApiHelper.getProxyServerListStorage();
		layoutInflater_ = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount() {
		return proxyServerList.getProxyServerList().size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProxyServer getItem(int position) {
		return proxyServerList.getProxyServerList().get(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Filter getFilter() {
		Filter proxyServerFilter = new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				if (constraint != null) {
					viewResult = new ArrayList<ProxyServer>();
					// A class that queries a web API, parses the data and
					// returns an ArrayList<Style>
					for (ProxyServer proxyServer : proxyServerList
							.getProxyServerList()) {
						if (proxyServer.getHost() != null
								&& proxyServer.getHost().matches(
										"*" + constraint + "*")) {
							viewResult.add(proxyServer);
						}
					}

					// Now assign the values and count to the FilterResults
					// object
					filterResults.values = viewResult;
					filterResults.count = viewResult.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				if (results != null && results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}
		};

		return proxyServerFilter;
	}

	/**
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 特定の行(position)のデータを得る
		ProxyServer item = getItem(position);

		// convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		if (null == convertView) {
			convertView = layoutInflater_.inflate(R.layout.list_proxy_server,
					null);
		}

		// CustomDataのデータをViewの各Widgetにセットする
		ImageView imageView;
		imageView = (ImageView) convertView
				.findViewById(R.id.list_proxy_server_image);
		
		imageView.setImageBitmap(loadIcon(item));

		TextView textView;
		textView = (TextView) convertView
				.findViewById(R.id.list_proxy_server_text);
		textView.setText(item.getHost());

		return convertView;
	}
	
	/**
	 * 国旗のアイコンをロードする
	 * @param proxyServer
	 * @return
	 */
	private Bitmap loadIcon(ProxyServer proxyServer){
		InputStream is = null;
		StringBuilder imageName = new StringBuilder(16);
		Bitmap icon = null;
		try {
			imageName.append("countrys");
			imageName.append(File.separatorChar);
			imageName.append(proxyServer.getLocal().toUpperCase());
			imageName.append(".png");
			is = getContext().getResources().getAssets().open(imageName.toString());
			icon = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e(LOG_TAG, "not found image the path of " + imageName, e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {}
		}
		return icon;
	}
}
