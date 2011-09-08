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

import org.springframework.security.crypto.encrypt.AndroidEncryptors;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.sqlite.SQLiteConnectionRepository;
import org.springframework.social.connect.sqlite.support.SQLiteConnectionRepositoryHelper;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import cn.loveapple.client.android.damtomo.R;

/**
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class MainApplication extends Application 
{
	private ConnectionFactoryRegistry connectionFactoryRegistry;
	private SQLiteOpenHelper repositoryHelper;
	private ConnectionRepository connectionRepository;	
	
	
	//***************************************
    // Application Methods
    //***************************************
	@Override
	public void onCreate() {
		// create a new ConnectionFactoryLocator and populate it with Facebook and Twitter ConnectionFactories
		connectionFactoryRegistry = new ConnectionFactoryRegistry();
		connectionFactoryRegistry.addConnectionFactory(new FacebookConnectionFactory(getFacebookAppId(), getFacebookAppSecret()));
		connectionFactoryRegistry.addConnectionFactory(new TwitterConnectionFactory(getTwitterConsumerToken(), getTwitterConsumerTokenSecret()));

		// set up the database and encryption
		repositoryHelper = new SQLiteConnectionRepositoryHelper(this);
		connectionRepository = new SQLiteConnectionRepository(repositoryHelper, connectionFactoryRegistry, AndroidEncryptors.text("password", "5c0744940b5c369b"));
	}
	
	
	//***************************************
    // Private methods
    //***************************************	
	private String getFacebookAppId() {
		return getString(R.string.facebook_app_id);
	}
	
	private String getFacebookAppSecret() {
		return getString(R.string.facebook_app_secret);
	}
	
	private String getTwitterConsumerToken() {
		return getString(R.string.twitter_consumer_key);
	}
	
	private String getTwitterConsumerTokenSecret() {
		return getString(R.string.twitter_consumer_key_secret);
	}
	
	
	//***************************************
    // Public methods
    //***************************************
	public ConnectionRepository getConnectionRepository() {
		return connectionRepository;
	}
	
	public FacebookConnectionFactory getFacebookConnectionFactory() {
		return (FacebookConnectionFactory) connectionFactoryRegistry.getConnectionFactory(Facebook.class);
	}
	
	public TwitterConnectionFactory getTwitterConnectionFactory() {
		return (TwitterConnectionFactory) connectionFactoryRegistry.getConnectionFactory(Twitter.class);
	}
	
}
