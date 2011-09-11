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

import cn.loveapple.client.android.shiba.database.entity.CacheEntity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * URL
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class UrlAdapter extends BaseAdapter implements Filterable {

	private Context context;
	private ArrayList<CacheEntity> showUrlList;
	private ArrayList<CacheEntity> tempUrlList;
	
	public UrlAdapter(Context context, ArrayList<CacheEntity> urlList){
		super();
		this.context = context;
		this.showUrlList = urlList;
		this.tempUrlList = (ArrayList<CacheEntity>) urlList.clone();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount() {
		return showUrlList.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getItem(int position) {
		return showUrlList.get(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		CacheEntity entity = showUrlList.get(position);
		//TODO
		return null;
	}

}
