/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
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

import java.util.List;

import cn.loveapple.client.android.shiba.database.entity.UrlHistoryEntity;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public interface UrlHistoryDao {
	/**
	 * URL、又は、タイトルから、最新のURL履歴を検索します。
	 * @param url
	 * @param title
	 * @return
	 */
	public UrlHistoryEntity getUrlHistory(String url, String title);
	/**
	 * URL、又は、タイトルから、最新のURL履歴を検索します。
	 * @param url
	 * @param title
	 * @return
	 */
	public List<UrlHistoryEntity> getUrlHistory(String url, String title, int limit);
	/**
	 * URL履歴を登録・保存します。
	 * @param entity
	 * @return
	 */
	public UrlHistoryEntity save(UrlHistoryEntity entity);
}
