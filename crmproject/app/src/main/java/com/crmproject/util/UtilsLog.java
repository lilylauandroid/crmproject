package com.crmproject.util;

public class UtilsLog {

	//控制自动升级的请求地址 true 测试 必须修改 切换正式测试时 需要修改isTest、HTTP_HOST_XF，ChatConstants的default
	public static boolean isTest = false;
	public static boolean isCHJZ = false;//控制是否是超级账户 true是
	/**
	 * 请求接口
	 */
//	public static final String HTTP_HOST_XF = isTest?"dsapptest.3g.fang.com/":"open.3g.fang.com/"; // 正式接口
	public static final String HTTP_HOST_XF = isTest?"dsapptest.3g.fang.com/":"kfyun.3g.fang.com/"; // 正式接口
	/**开发云新域名*/
	public static final String HTTP_HOST_XF_NEW = isTest?"kfytransittest.3g.fang.com/":"kfytransit.3g.fang.com/";

	public static final String TAG="XFT_LOG";
	public static final String HTTP_HOST_CESHI = "111.207.187.155:9082/http/";// 测试
	public static final String HTTP_HOST = "client.3g.soufun.com/http/";// 正式地址

	public static final String HTTP_HOST_XFGZT = isTest?"xinfangbangjk.test.fang.com/":"xfbapi.3g.fang.com/xfdsapp/";

//	public static final String CHAT_URL = "http://chat.client.3g.fang.com/HttpConnection";// 聊天正式地址
//	public static final String CHAT_URL = "http://testchat.client.3g.fang.com/HttpConnection";// 聊天测式地址

	public static final String UPLOAD_URL = isTest?"http://xinfangbangjk.test.fang.com/267.aspx":"http://xinfangbangjk.wap.fang.com/267.aspx";// 视频音频上传地址正式地址(17.10 copy xfb暂时作为电商-跟进详情相关使用)
	public static final String RECHARGE_URL = isTest?"http://dsapptest.3g.fang.com/PayInApplyForApp_V1.api":"http://kfyun.3g.fang.com/PayInApplyForApp_V1.api";// 充值接口url

	/** 埋码服务器地址 */
	public static final String HTTP_ANALYZE_HOST = "jjy.3g.fang.com/httpclient/";
	/** 埋码服务器测试地址 */
	public static final String HTTP_ANALYZE_HOST_TEST = "jjytest.3g.fang.com/httpclient/";
	/** 埋码服务端方法名 */
	public static final String HTTP_ANALYZE_METHOD= "agentservice.jsp";
	public static final String HTTPS_ANALYZE_URL= "https://"+HTTP_ANALYZE_HOST + HTTP_ANALYZE_METHOD;
	public static final String HTTP_ANALYZE_URL_TEST= "http://"+HTTP_ANALYZE_HOST_TEST + HTTP_ANALYZE_METHOD;
	/** IM设置帮助地址 */
	public static final String HTTP_SET_HELP_HOST_TEST= "http://a.wap.test.fang.com/";
	public static final String HTTP_SET_HELP_HOST= "http://a.wap.fang.com/";
	public static final String SET_HELP_PATH= "index.html#/ImHelpList/open/";
	public static final String URL_CHAT_HOST = isTest ?"http://testchat.client.3g.fang.com"
			: "http://chat.client.3g.fang.com";
	public static final String URL_CHAT_AUTH_HOST = isTest ? "http://test.imgateway.3g.fang.com"
			: "http://imgateway.3g.fang.com/";
	public static final String NOTIFYURL = URL_CHAT_HOST+"/ClientInterface";
	public static final String URL_CHAT_REPORT = isTest ?"http://124.251.47.32:10089"
			:"http://api.im.fang.com";


	public static void d(String key, String value) {
		if (isTest) {
			android.util.Log.d(key, value);
		}
	}

	public static void i(String key, String value) {
		if (isTest) {
			android.util.Log.i(key, value);
		}
	}

	public static void e(String key, String value) {
		if (isTest) {
			android.util.Log.e(key, value);
		}
	}

	/**
	 * 获取IM设置帮助的地址
	 * @return url
	 */
	public static String getSetHelpUrl(){
		if (isTest){
			return HTTP_SET_HELP_HOST_TEST+SET_HELP_PATH+android.os.Build.BRAND;
		}else {
			return HTTP_SET_HELP_HOST+SET_HELP_PATH+android.os.Build.BRAND;
		}
	}
	/**
	 * 获取埋码、崩溃统计的地址
	 * @return url
	 */
	public static String getAnalysisUrl(){
		if (isTest){
			return HTTP_ANALYZE_URL_TEST;
		}else {
			return HTTPS_ANALYZE_URL;
		}
	}
}
