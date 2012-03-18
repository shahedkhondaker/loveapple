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
package cn.loveapple.client.android.gaia.logic;

import java.util.ArrayList;
import java.util.List;

import cn.loveapple.client.android.gaia.entity.EventEntity;

/**
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class EventLogic {
	public List<EventEntity> findEventList(){
		List<EventEntity> result = new ArrayList<EventEntity>();
		EventEntity entity1 = new EventEntity();
		entity1.setLatitude(1.0);
		entity1.setLongitude(2.0);
		entity1.setMessage("first event");
		entity1.setTag("#x#");
		result.add(entity1);
		
		return result;
	}
}
