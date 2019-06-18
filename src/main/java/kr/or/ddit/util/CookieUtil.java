package kr.or.ddit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CookieUtil.class);
	
	public static String cookieString;	// 분석할 쿠키 문자열
	
	/**
	 * 
	* Method : setCookieString
	* 작성자 : PC14
	* 변경이력 :
	* @param cookieString
	* Method 설명 : 분석할 쿠키 문자열을 받는다.
	 */
	public static void setCookieString(String cookieString) {
		CookieUtil.cookieString = cookieString;
	}

	
	/**
	 * 
	* Method : getCookie
	* 작성자 : PC14
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다.
	 */
	public static String getCookie(String cookie) {
		// "userId=brown; rememberme=true; test=testValue";
		// cookie = "userId"
		String[] cookieArray = CookieUtil.cookieString.split("; ");
		
		String cookieValue = "";
		
		for(String str : cookieArray){
			logger.debug("str : {} ", str);
			
			if(str.startsWith(cookie)){
				String[] cookieStr = str.split("=");
				
				if(cookie.equals(cookieStr[0])){
					cookieValue = cookieStr[1];
					break;
				}
				
			}
		}
		
		return cookieValue;
	}
	
	/**
	 * 
	 * Method : getCookie
	 * 작성자 : PC14
	 * 변경이력 :
	 * @param cookie
	 * @return
	 * Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다.
	 */
	public static String getCookie2(String cookie) {

		String[] cookies = cookieString.split("; ");
		String cookieValue = "";
		
		for(int i=0; i < cookies.length; i++){
			
			if( cookies[i].contains(cookie) ){
				int idx = cookies[i].indexOf("=");

				if( cookie.equals(cookies[i].substring(0,idx)) ){
					cookieValue = cookies[i].substring(idx+1);
					break;
				}
			}
		}
		return cookieValue;
	}


}
