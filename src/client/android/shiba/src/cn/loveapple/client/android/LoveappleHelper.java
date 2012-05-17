/*
 * $HeadURL:$
 * $Author:$
 * $Revision:$
 * $Date:$
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
package cn.loveapple.client.android;

import static cn.loveapple.client.android.Constant.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.http.HttpHost;

import cn.loveapple.client.android.util.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

/**
 * ヘルパークラス
 * 
 * @author $Author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 *
 */
public class LoveappleHelper {

	private final static String DEFAULT_HOST = "127.0.0.1";
	private final static int DEFAULT_PORT = 8118;
	private final static int DEFAULT_SOCKET_PORT = 9050;

	private final static int REQUEST_CODE = 0;

	public static void setProxy(Context ctx) {
		setProxy(ctx, DEFAULT_HOST, DEFAULT_PORT);
	}

	/**
	 * プロキシーを設定する<br>
	 * ホストが空文字、又は、ポート番号か<code>0</code>より小さい場合、何も設定しない。
	 * 
	 * @param ctx
	 * @param host
	 * @param port
	 */
	public static void setProxy(Context ctx, String host, int port) {
		if(StringUtils.isEmpty(host) || port < 0){
			return ;
		}
		setSystemProperties(host, port);
		setWebkitProxy(ctx, host, port);
	}

	/**
	 * システムのプロキシ属性を設定
	 * 
	 * @param host
	 * @param port
	 */
	private static void setSystemProperties(String host, int port) {

		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port + "");

		System.setProperty("https.proxyHost", host);
		System.setProperty("https.proxyPort", port + "");

		System.setProperty("socks.proxyHost", host);
		System.setProperty("socks.proxyPort", port + "");

	}

	/**
	 * Override WebKit Proxy settings
	 * 
	 * @param ctx
	 *            Android ApplicationContext
	 * @param host
	 * @param port
	 * @return true if Proxy was successfully set
	 */
	private static boolean setWebkitProxy(Context ctx, String host, int port) {
		boolean ret = false;
		try {
			Object requestQueueObject = getRequestQueue(ctx);
			if (requestQueueObject != null) {
				// Create Proxy config object and set it into request Q
				HttpHost httpHost = new HttpHost(host, port, "http");
				// HttpHost httpsHost = new HttpHost(host, port, "https");

				setDeclaredField(requestQueueObject, "mProxyHost", httpHost);
				ret = true;
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, "error setting up webkit proxying", e);
		}
		return ret;
	}

	public static void resetProxy(Context ctx) throws Exception {
		Object requestQueueObject = getRequestQueue(ctx);
		if (requestQueueObject != null) {
			setDeclaredField(requestQueueObject, "mProxyHost", null);
		}
	}

	public static Object getRequestQueue(Context ctx) throws Exception {
		Object ret = null;
		Class networkClass = Class.forName("android.webkit.Network");
		if (networkClass != null) {
			Object networkObj = invokeMethod(networkClass, "getInstance",
					new Object[] { ctx }, Context.class);
			if (networkObj != null) {
				ret = getDeclaredField(networkObj, "mRequestQueue");
			}
		}
		return ret;
	}

	private static Object getDeclaredField(Object obj, String name)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field f = obj.getClass().getDeclaredField(name);
		f.setAccessible(true);
		Object out = f.get(obj);
		Log.d(LOG_TAG, obj.getClass().getName() + "." + name + " = " + out);
		return out;
	}

	private static void setDeclaredField(Object obj, String name, Object value)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field f = obj.getClass().getDeclaredField(name);
		f.setAccessible(true);
		f.set(obj, value);
	}

	private static Object invokeMethod(Object object, String methodName,
			Object[] params, Class... types) throws Exception {
		Object out = null;
		Class c = object instanceof Class ? (Class) object : object.getClass();
		if (types != null) {
			Method method = c.getMethod(methodName, types);
			out = method.invoke(object, params);
		} else {
			Method method = c.getMethod(methodName);
			out = method.invoke(object);
		}
		Log.d(LOG_TAG, object.getClass().getName() + "." + methodName + "() = "
				+ out);
		return out;
	}

	public static Socket getSocket(Context context, String proxyHost,
			int proxyPort) throws IOException {
		Socket sock = new Socket();

		sock.connect(new InetSocketAddress(proxyHost, proxyPort), 10000);

		return sock;
	}

	public static Socket getSocket(Context context) throws IOException {
		return getSocket(context, DEFAULT_HOST, DEFAULT_SOCKET_PORT);

	}
/*
	public static AlertDialog initOrbot(Activity activity,
			CharSequence stringTitle, CharSequence stringMessage,
			CharSequence stringButtonYes, CharSequence stringButtonNo,
			CharSequence stringDesiredBarcodeFormats) {
		Intent intentScan = new Intent("org.torproject.android.START_TOR");
		intentScan.addCategory(Intent.CATEGORY_DEFAULT);

		try {
			activity.startActivityForResult(intentScan, REQUEST_CODE);
			return null;
		} catch (ActivityNotFoundException e) {
			return showDownloadDialog(activity, stringTitle, stringMessage,
					stringButtonYes, stringButtonNo);
		}
	}

	private static AlertDialog showDownloadDialog(final Activity activity,
			CharSequence stringTitle, CharSequence stringMessage,
			CharSequence stringButtonYes, CharSequence stringButtonNo) {
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
		downloadDialog.setTitle(stringTitle);
		downloadDialog.setMessage(stringMessage);
		downloadDialog.setPositiveButton(stringButtonYes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogInterface, int i) {
						Uri uri = Uri
								.parse("market://search?q=pname:org.torproject.android");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						activity.startActivity(intent);
					}
				});
		downloadDialog.setNegativeButton(stringButtonNo,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogInterface, int i) {
					}
				});
		return downloadDialog.show();
	}*/
}
