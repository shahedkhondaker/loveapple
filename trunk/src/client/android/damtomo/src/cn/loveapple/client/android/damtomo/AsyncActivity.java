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
package cn.loveapple.client.android.damtomo;



/**
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public interface AsyncActivity {
	
	/**
	 * アプリケーションを取得する
	 * @return
	 */
	public MainApplication getApplicationContext();
	
	/**
	 * プロセス処理中のダイアログを取得
	 */
	public void showLoadingProgressDialog();
	
	/**
	 * プロセス処理中のダイアログを表示
	 * 
	 * @param message 表示する文言
	 */
	public void showProgressDialog(CharSequence message);
		
	/**
	 * プロセス処理中のダイアログを非表示
	 */
	public void dismissProgressDialog();
	
}