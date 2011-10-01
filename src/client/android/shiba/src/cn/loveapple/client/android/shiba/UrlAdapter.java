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
package cn.loveapple.client.android.shiba;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import cn.loveapple.client.android.shiba.database.CacheDao;

/**
 * URL
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 * 
 */
public class UrlAdapter extends ArrayAdapter<String> implements Filterable {

	private List<String> urlList;
	private CacheDao cacheDao;

	public UrlAdapter(Context context, int textViewResourceId, CacheDao cacheDao) {
		super(context, textViewResourceId);
		urlList = new ArrayList<String>();
		this.cacheDao = cacheDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount() {
		return urlList.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getItem(int position) {
		return urlList.get(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Filter getFilter() {
		Filter urlFilter = new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				if (constraint != null) {
					// A class that queries a web API, parses the data and
					// returns an ArrayList<Style>
					try {
						urlList = cacheDao.findCacheHttpUrl(constraint.toString(), ShibaFacadeActivity.VIEW_URL_LIMIT);
					} catch (Exception e) {
					}
					// Now assign the values and count to the FilterResults
					// object
					filterResults.values = urlList;
					filterResults.count = urlList.size();
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

		return urlFilter;
	}

}
