package cn.loveapple.service.util.web;

/**
 * コントローラ、ビューのUTIL。
 * 
 * @author hao_shunri
 *
 */
public class FrontUtil {
	/**
	 * URL文字列を<code>a/b/c</code>の形式に生成する。
	 * @param seqs URL文字列
	 * @return URLを返す
	 */
	public static String createUrlStr(CharSequence...seqs){
		if(seqs == null || seqs.length < 1){
			return "";
		}
		StringBuilder url = new StringBuilder(1024);
		for(int i = 0; i < seqs.length; i++){
			if(seqs[i].length() < 1){
				continue;
			}
			if(i != 0 && seqs[i].charAt(0) != '/'){
				url.append('/');
			}
			url.append(seqs[i]);
		}
		return url.toString();
	}
	/**
	 * URL文字列を<code>redirect:/a/b/c</code>の形式に生成する。
	 * 
	 * @see #createUrlStr(CharSequence...) URLの生成処理を行う。
	 * @param seqs URL文字列
	 * @return リダイレクト用のURLを返す
	 */
	public static String createRedirectUrlStr(CharSequence...seqs){
		String url = createUrlStr(seqs);
		if(url.startsWith("/")){
			return "redirect:" + url;
		}
		return "redirect:/" + url;
	}
}
