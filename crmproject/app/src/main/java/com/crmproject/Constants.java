package com.crmproject;

import android.os.Environment;

import com.crmproject.util.UtilsLog;


public interface Constants {
    public static final String APP_NAME = "kfy_android";// 应用名称

    public static final String URL_QYRZ = UtilsLog.isTest ? "http://m.test.fang.com/house/ec/static/QiYeRenZheng" : "http://m.fang.com/house/ec/static/QiYeRenZheng";
    public static final String URL_SELECTCOMPANY = UtilsLog.isTest ? "http://b.nhe.test.fang.com/Wap/Buiness/selectcompany" : "http://channelsales.tao.fang.com/nhe/Wap/Buiness/selectcompany";
    public static final String URL_FEED_BACK = UtilsLog.isTest ? "http://channelsales.tao.test.fang.com/nhe/Wap/AdviseFeedback/index" : "http://channelsales.tao.fang.com/nhe/Wap/AdviseFeedback/index";
    public static final String URL_REGISTER = "http://esf.js.soufunimg.com/esf/agentcloud/agreement/net_service1.html";
    public static final String URL_TECH_SERVICE = "http://esf.js.soufunimg.com/esf/agentcloud/agreement/Product_service_open_platform1.html";
    public static final String URL_YINSIQUAN = UtilsLog.isTest ? "http://khy.test.fang.com/html/kaifaprivacy.html" : "http://kaifa.yun.fang.com/html/kaifaprivacy.html"; // 注册隐私权政策
    // 注册协议
    public static final String URL_PROTOCOL = UtilsLog.isTest ? "http://khy.test.fang.com/html/agreementreview.html" : "http://kaifa.yun.fang.com/html/agreementreview.html";
    public static final String URL_TECH_SERVICE_EB = "http://img1.soufunimg.com/message/broker/qd/ht/201706/jsxy.html";
    public static final String SLID_ACTION = "com.soufun.xinfang.xft.slid";// slidmenu
    public static final String SET_ACTION = "com.soufun.xinfang.xft.activity.setting"; // 滑动的广播
    public static final String PAY_PROTOCOL = "http://esf.js.soufunimg.com/esf/agentcloud/agreement/Product_service_open_platform1.html";// 房天下开放平台产品在线购买服务协议
    /**
     * 周边顾问wap页
     */
    public static final String AROUNDADVISER_URL = "http://a.wap.fang.com/zt/CircumAdviser/circumAdviserintro.html";
    /**
     * 订单列表页
     */
    public static final String URL_WAP_ORDER = UtilsLog.isTest ? "http://khy.test.fang.com/assets/html/wap/order/index.html/#/orderList" : "http://yunkaifa.fang.com/assets/html/wap/order/index.html/#/orderList";
    /**
     * 帮助与反馈
     */
    public static final String URL_WAP_SUGGESTIONHELP = UtilsLog.isTest ? "http://yun2test.fang.com/views/index.html/#/qaview?group=kaifa" : "https://yun2.fang.com/views/index.html/#/qaview?group=kaifa";
    /**
     * 隐私协议
     */
    public static final String URL_PRIVACY_POLICY = "http://kaifa.yun.fang.com/html/kaifaprivacy.html";
    /**
     * 服务协议
     */
    public static final String URL_SERVICE_POLICY = "http://esf.js.soufunimg.com/esf/agentcloud/agreement/net_service1.html";

    /**
     * 微信支付APPID
     */
    public static final String WX_APP_ID = "wxd28fbe85cbd4f7c4";//原id：wx5ee51b62b5c87ab7

    public static final String OS_VERSION = android.os.Build.VERSION.RELEASE;
    public static final String MODEL = android.os.Build.MODEL;

    /** 支持的图像 */
    /**
     * 小图
     */
    public static final int PIC_SIZE_SMALL = 1;
    /**
     * 中图
     */
    public static final int PIC_SIZE_MIDDLE = 2;
    /**
     * 大图
     */
    public static final int PIC_SIZE_LARGE = 3;

    /**
     * 根目录
     */
    public static final String ROOT_DIR_PATH = "/xfkfs/res";
    /**
     * 缓存目录
     */
    public static final String CACHE_DIR_PATH = ROOT_DIR_PATH + "/cache";
    /**
     * 图片缓存目录
     */
    public static final String PIC_CACHE_DIR_PATH = CACHE_DIR_PATH
            + "/pic_cache";
    /**
     * 收藏图片缓存目录
     */
    public static final String STORE_PIC_CACHE_DIR_PATH = CACHE_DIR_PATH
            + "/store_pic_cache";

    /**
     * 列表缓存目录
     */
    public static final String STORE_LIST_CACHE_DIR_PATH = CACHE_DIR_PATH
            + "/list_cache";

    /**
     * 多媒体沟通，图片缓存目录
     */
    public static final String MM_PIC_CACHE_DIR_PATH = CACHE_DIR_PATH
            + "/mm_pic_cache";
    /**
     * 多媒体沟通，大图片缓存目录
     */
    public static final String MM_PIC_LARGE_CACHE_DIR_PATH = MM_PIC_CACHE_DIR_PATH
            + "/large";
    /**
     * 多媒体沟通，视频缓存目录
     */
    public static final String MM_VIDEO_CACHE_DIR_PATH = CACHE_DIR_PATH
            + "/mm_video_cache";
    /**
     * 多媒体沟通，音频缓存目录
     */
    public static final String MM_VOICE_CACHE_DIR_PATH = CACHE_DIR_PATH
            + "/mm_voice_cache";
    public static final String ROOT_VIDEO_PATH = ROOT_DIR_PATH + "/video_cache";
    public static final String ROOT_PIC_PATH = ROOT_DIR_PATH + "/pic_cache";

    /**
     * 多媒体沟通，非聊天视频缓存目录
     */
    public static final String MM_VIDEO_CACHE_PATH = ROOT_VIDEO_PATH
            + "/mm_voice_cache";
    public static final String MM_PIC_CACHE_PATH = ROOT_PIC_PATH
            + "/mm_pic_cache";
    public static final String ROOT_SAVE_DIR_PATH = ROOT_DIR_PATH + "/save";//手动保存的文件放到这里
    public static final String ROOT_PIC_SAVE_PATH = ROOT_SAVE_DIR_PATH + "/pic_save";//手动保存的图片放到这
    public static final String MM_PIC_SAVE_DIR_PATH = ROOT_PIC_SAVE_PATH;//既然是用户保存的图片，就不把聊天的目录单分出来了


    /**
     * 强制升级
     */
    public static final String APK_FORCE_UPDATE = "force_update";
    /**
     * 是否能升级
     */
    public static final String APK_HAS_UPDATE = "has_update";
    /**
     * 升级url
     */
    public static final String APK_UPDATE_URL = "update_url";
    /**
     * apk大小
     */
    public static final String APK_APP_SIZE = "app_size";
    /**
     * apk描述
     */
    public static final String APK_UPDATE_DESCRIBE = "update_describe";
    /**
     * apk名称
     */
    public static final String APK_APP_NAME = "xfxft";
    /**
     * apk版本
     */
    public static final String APK_APP_VERSION = "apk_app_version";
    /**
     * 本地apk版本
     */
    public static final String APK_APP_OLD_VERSION = "apk_app_old_version";
    /**
     * 手动更新标识
     */
    public static final String APK_MANUAL_UPDATE = "manual_update";
    public static final String RECORD_NAME = "updateProgress";

    public static final String KEY_FILE_SIZE = "FileSize";
    public static final String KEY_DOWNLOADED_SIZE = "Downloaded";
    public static final String UPDATE_APK_PATH = "update_apk_path";
    public static final String APK_NAME = "XFT_1.0.0.apk ";
    public static final String DOWNLOADING = "downloading";
    /**
     * 当前应用版本号
     */
    public static final String APP_VERSION = "appversion";
    /**
     * 请求的message name
     */
    public static final String MWSSAGE_NAME = "messagename";
    /**
     * share文件用户信息名key
     */
    public static final String USER_INFO = "xft_userinfo";
    /**
     * share文件当前默认项目KEY
     */
    public static final String APP_CURRENT_PROJECT = "xft_project";
    /**
     * 是否登录状态
     */
    public static final String AGENTLOGINSTATE_ON = "is_agentlogin_on";
    public static final String AGENTLOGINSTATE_SETTING_INFO = "agentloginstate_setting_info";
    /**
     * 是否自动选择项目
     */
    public static final String PROJECT_AUTO = "project_auto";
    /**
     * APK上次登录的版本，用于检查是否自动登录
     */
    public static final String APP_LAST_VERSION = "last_login_version";
    /**
     * 每页条数
     */
    public static final int PAGE_SIZE = 20;
    /**
     * 收藏最大的条数
     */
    public static final int STORE_NUM = 100;
    /**
     * 最近联系最大的条数
     */

    public static final int CONTACT_NUM = 50;
    /**
     * 浏览最大的条数
     */
    public static final int BROWSE_NUM = 20;
    /**
     * 动态最大的条数
     */
    public static final int DYNAMIC_NUM = 20;

    public static final int CODE_AUTO_UPDATE = 1001;
    public static final int CODE_FORCE_UPDATE = 1002;
    public static final int CODE_MANUAL_UPDATE = 1003;
    public static final int CODE_CITY_SWITCH_DIALOG = 1004;
    public static final int GRAB_DIALOG_SHOW = 1005;
    public static final int BACK_HOUSE_INPUT_PROJNAME = 200;
    /**
     * 内测升级
     */
    public static final int CODE_CLOSE_TEST_UPDATE = 1007;
    /**
     * 用于记录内测上次提示的时间
     */
    public static final String KEY_TEST_TIME = "TestTime";

    public static final String FLOW = "flow";// 本地存储流量账号名
    public static final int BACK_REGISTER = 100;// forResult定义的code

    public static final int LOGIN = 101;// forResult定义的code

    public static final int PUBLISHLIST_DELETE = 102;// forResult定义的code

    public static final int CITY_SWITCH = 103;// forResult定义的code

    public static final int NEW_DETAIL = 104;// forResult定义的code

    public static final String COMMONT_BREAK = "break";// 服务器断开链接命令
    public static final String COMMONT_CHAT = "chat";// 聊天命令
    public static final String COMMONT_SEPARAT_MESSAGE = "separate";// 显示的信息和发送的消息不一致
    public static final String COMMONT_IMG = "img";// 聊天发送图片
    public static final String COMMONT_VIDEO = "video";// 视频发送
    public static final String COMMONT_VOICE = "voice";// 语音聊天
    public static final String COMMONT_FUNCTION_SWITCHER = "checkMultimedia";// 版本控制
    public static final String COMMONT_REQVIDEO = "reqvideo";// 收到的直播看房命令
    public static final String COMMONT_REPVIDEO = "repvideo";// 接受或者拒绝直播看房
    public static final String COMMONT_HANDUPVIDEO = "handupvideo";// 挂断直播看房
    public static final String COMMONT_TUIJIAN = "potential";// 潜客推荐
    public static final String CHAT_QIANKE = "qianke";// 潜客的标识

    public static final String CHAT_HANDUPVIDEO_STATIO_HOUSE_ACTION = "com.soufun.app.activity.handupvideo";// 结束直播看房
    // public static final String
    // CHAT_START_STATIO_HOUSE_ACTION="com.soufun.app.activity.startvideo";//启动直播看房
    public static final String CHAT_HOUSE_STATIO_BACK_DATA = "com.soufun.app.activity.statiobackdata";// 从直播看房返回的intentaction
    // public static final String CHAT_HOUSE_STATIO_BACK_DATA_FOR_ACTIVITY =
    // "com.soufun.app.activity.statiobackdata.chatactivity";

    public static final String CHAT_GET_OFFLINE_MESSAGES = "com.soufun.app.activity.getofflinemessage";// 免打扰模式时候的消息

    /**
     * 推送设置---震动
     */
    public static final String TUISONG_VIBRATE = "vibrate";
    /**
     * 推送设置---声音
     */
    public static final String TUISONG_VOICE = "voice";
    /**
     * 推送设置---开关
     */
    public static final String TUISONG_OPEN = "open";

    public static final String IM_USERTYPE = "consultant";//IM用户类型
    public static final String IM_USERNAME = "gw:";

    /**
     * 流量统计 all
     */
    public static final String FLOW_ALL = "all";

    /**
     * 流量统计 pc
     */
    public static final String FLOW_PC = "pc";

    /**
     * 流量统计 move
     */
    public static final String FLOW_MOVE = "app";

    /**
     * 流量统计 pv
     */
    public static final String FLOW_PV = "pv";

    /**
     * 登录信息有效时间
     */
    public static final long LOGIN_TIME_VALIDITY = 30 * 24 * 60 * 60 * 1000L;

    // 推送相关

    public static Object chatObj = new Object();

    public static final String COMP_MSG = "comp_msg";// 求购委托
    public static final String AREA_MSG = "area_msg";// 求购委托
    public static final String SHOP_MSG = "shop_msg";// 求购委托
    public static final String SOUFUN_MSG = "soufun_msg";// 求购委托
    public static final String SHORT_MSG = "short_msg";// 求购委托
    public static final String SOUFUN_ANNO = "xf_sendannounce";// 搜房公告

    /**
     * 新添加->抢客户推送使用
     */
    public static final String COMMONT_KFS_NOTICE = "notice";

    public static final int CHOOSE_COMERA = 101;// 拍照
    public static final int CHOOSE_ALBUM = 102;// 相册
    public static final int CHOOSE_CUT = 106;// 剪切

    /**
     * 楼盘相册
     */
    public static final int CHOOSE_FLOOR_ALBUM = 110;// 楼盘相册
    /**
     * 添加户型requestcode
     */
    public static final int CHOOSE_FLOOR_ADD_ROOM = 1000;// 添加户型

    /**
     * SD卡目录
     */
    public static final String SD_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath();

    //一下为常用工具全局常量
    /**
     * 抖房
     */
    public static final String TOOL_DOUFANG = "1";
    /**
     * 客户管理
     */
    public static final String TOOL_CUSTOMER_MANAGEMENT = "4";
    /**
     * 销售管理
     */
    public static final String TOOL_SALES_MANAGEMENT = "17";
    /**
     * 钱包
     */
    public static final String TOOL_WALLET = "20";
    /**
     * 合同管理
     */
    public static final String TOOL_CONTRACT_MANAGEMENT = "12";
    /**
     * 服务团队
     */
    public static final String TOOL_SERVICE_TEAM = "11";
    /**
     * 抢客户
     */
    public static final String TOOL_ROB_CUSTOMERS = "2";
    /**
     * 数据中心
     */
    public static final String TOOL_DATA_CENTER = "8";
    /**
     * 项目卖点
     */
    public static final String TOOL_SELLING_POINT = "18";
    /**
     * 楼盘海报
     */
    public static final String TOOL_PROJ_POSTER = "19";
    /**
     * 推荐好友
     */
    public static final String TOOL_RECOMMAND_FRIENDS = "22";
    /**
     * 直播
     */
    public static final String TOOL_ZHIBO = "33";
    /**
     * 潜客数据
     */
    public static final String TOOL_QIANKE_DATA = "52";
    /**
     * 问答广场
     */
    public static final String TOOL_WENDA_SQUARE = "53";
    /**
     * 楼盘点评
     */
    public static final String TOOL_PROPERTY_REVIEWS = "54";
    /**
     * 抢C位
     */
    public static final String TOOL_ROB_C = "55";
    /**
     * 微案场
     */
    public static final String TOOL_MICRO_SHOP = "56";
    /**
     * 新手教学
     */
    public static final String TOOL_NEWUSER_GUIDE = "57";
    /**
     * VR管理
     */
    public static final String TOOL_VR_MANAGE = "58";
    /**
     * 楼盘点评
     */
    public static final String TOOL_LOUPAN_DIANPING = "59";
    /**
     * 获客权限
     */
    public static final String TOOL_HUOKE_QUANXIAN = "60";
    /**房产圈*/
    public static final String TOOL_FANGCHAN_QUAN = "66";

    /**本地配置周边顾问id*/
    public static final String TOOL_ZHOUBIANGUWEN = "1000";

    /**本地配置直购确客id*/
    public static final String TOOL_ZHIGOUQUEKE = "1001";

    //产品变量
    /**
     * 天下潜客
     */
    public static final String PRODUCT_TXQK = "2003";
    /**
     * 天下潜客k+
     */
    public static final String PRODUCT_TXQK_K_PLUS = "2020";
    /**
     * 广告
     */
    public static final String PRODUCT_AD = "2014";
    /**
     * 活动营销工具
     */
    public static final String PRODUCT_HDYXGJ = "2011";
    /**
     * 新房通
     */
    public static final String PRODUCT_XFT = "0003";

    /**
     * 工具广播接收者
     */
    public static final String BROADCAST_FUNCTION = "com.fang.KFSCloud.function";
    /**
     * 人脸识别弹框
     */
    public static final String BROADCAST_FACE = "com.fang.KFSCloud.face";


    //跳转变量 用处比较多的放这里
    public static final String URL_LIVE = UtilsLog.isTest ? "https://livetest.fang.com/liveshow/index/liveforapp" : "https://live.fang.com/liveshow/index/liveforapp";
    /**
     * 跳转到我的直播
     */
    public static final int MY_LIVE = 2000;
    /**
     * 跳转到开播
     */
    public static final int START_LIVE = 2001;
    /**
     * K+产品id（统一配置以防修改）
     */
    public static final String K_PLUS_PRODUCTID = "107";
    /**
     * K+产品type统一配置以防修改）
     */
    public static final String K_PLUS_PRODUCTTYPE = "107";
    /**一键登录appid*/
    public static final String CMCC_APPID = "300011963812";
    /**一键登录token*/
    public static final String CMCC_TOKEN = "1756BE28F6DA08A39F9CD6CD7AD57054";
    /**个人实名认证提示已有个人认证*/
    public static final String URL_HAS_PERSONAL_AUTH = "https://news.fang.com/zt/wap/202003/help3.html";
    /**企业资质认证无法通过*/
    public static final String URL_COMPANY_AUTH_NO_PASS = "https://news.fang.com/zt/wap/202003/help6.html";
    /**收不到验证码*/
    public static final String URL_NO_SMS_CODE = "https://news.fang.com/zt/wap/202003/help2.html";
    /**讲房隐私协议*/
    public static final String JF_URL_PRIVACY_POLICY = "https://m.fang.com/zt/wap/202007/spjfkfyyszc.html?city=bj&m=xfdg";

}
