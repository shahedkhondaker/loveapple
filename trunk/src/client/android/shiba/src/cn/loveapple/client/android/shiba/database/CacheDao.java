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
package cn.loveapple.client.android.shiba.database;

import static cn.loveapple.client.android.shiba.database.entity.CacheEntity.*;

import java.util.List;

import cn.loveapple.client.android.shiba.database.entity.CacheEntity;

/**
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public interface CacheDao {

	public static final String[] BIND_COLUMN = new String[]{COLUMN_ID, COLUMN_URL, COLUMN_LAST_MODIFY,COLUMN_ETAG,COLUMN_EXPIRES,
				COLUMN_EXPIRES_STRING, COLUMN_MIMETYPE,COLUMN_ENCODING,COLUMN_HTTP_STATUS,COLUMN_LOCATION,
				COLUMN_CONTENT_LENGTH,COLUMN_CONTENT_DISPOSITION,COLUMN_CROSSD_OMAIN};
	/**
	 * 
	 * @param url
	 * @param limit
	 * @return
	 */
	public List<CacheEntity> findCache(String url, int limit);
	
	/**
	 * 
	 * @param url
	 * @param limit
	 * @return
	 */
	public List<String> findCacheHttpUrl(String url, int limit);
}
