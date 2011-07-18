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
package cn.loveapple.client.android.bbt.listener;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 
 * SeekBarの状態をあらわすメッセージを設定するリスナー
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class MessagerOnSeekBarChangeListener implements OnSeekBarChangeListener {
	/**
	 * SeekBar状態を表示するテキスト
	 */
	private TextView text;
	/**
	 * SeekBar状態を表示するテキストの配列
	 */
	private String[] strs;
	
	public MessagerOnSeekBarChangeListener(TextView text, String[] strs){
		this.text = text;
		this.strs = strs;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if(strs.length <= seekBar.getProgress()){
			return;
		}
		text.setText(strs[seekBar.getProgress()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if(strs.length <= seekBar.getProgress()){
			return;
		}
		text.setText(strs[seekBar.getProgress()]);
	}

}
