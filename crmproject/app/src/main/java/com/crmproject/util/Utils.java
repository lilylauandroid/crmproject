package com.crmproject.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.crmproject.MyApplication;
import com.crmproject.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Utils {

	/**
	 * 弹出正在上传图片Dialog
	 * 
	 * @param content
	 *            显示内容
	 */
//	public static Dialog showProcessPicDialog(Context context, String content,
//			int height) {
//		Dialog customDialog = new Dialog(context, R.style.Theme_Light_Dialog);
//		customDialog.setContentView(R.layout.toast_view);
//		WindowManager.LayoutParams params = customDialog.getWindow()
//				.getAttributes();
//		params.gravity = Gravity.TOP | Gravity.CENTER;
//		params.y = height;
//		customDialog.findViewById(R.id.tv_name).setVisibility(View.GONE);
//		((TextView) customDialog.findViewById(R.id.tv_name_success))
//				.setText(content);
//		customDialog.show();
//		return customDialog;
//	}

	/**
	 * 弹出SoufunProcessDialog
	 * 
	 */
//	public static Dialog showProcessDialog(Context context) {
//		Dialog customDialog = new Dialog(context,
//				R.style.Theme_Light_ProcessDialog_Blue);
//		customDialog.setContentView(R.layout.process_dialog);
//		customDialog.findViewById(R.id.piv_loading_process).setVisibility(
//				View.VISIBLE);
//		customDialog.show();
//		return customDialog;
//	}

	/**
	 * 弹出SoufunProcessDialog
	 * 
	 * @param content
	 *            显示内容
	 */
//	public static Dialog showProcessDialog(Context context, String content) {
//
//		Dialog customDialog = new Dialog(context, R.style.Theme_Light_Dialog);
//		customDialog.setCanceledOnTouchOutside(false);
//		customDialog.setContentView(R.layout.process_dialog_new);
//		customDialog.findViewById(R.id.piv_loading_process).setVisibility(
//				View.VISIBLE);
//		((TextView) customDialog.findViewById(R.id.tv_process))
//				.setText(content);
//		customDialog.show();
//		return customDialog;
//
//	}

	/**
	 * 获得指定目录的剩余空间.
	 * 
	 * @param path
	 *            路径
	 * @return long
	 */
	public static long getFreeSpace(String path) {
		StatFs sf = new StatFs(path);
		long blockSize = sf.getBlockSize();
		long availCount = sf.getAvailableBlocks();

		return availCount * blockSize;
	}

	/**
	 * toast （默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 */
	public static void toast(Context c, String msg) {
		toast(c, msg, true);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, String msg, int duration) {
		toast(c, msg, true, duration);
	}

	/**
	 * toast 时间（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param show
	 *            是否显示
	 */
	public static void toast(Context c, String msg, boolean show) {
		toast(c, msg, show, Toast.LENGTH_SHORT);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param show
	 *            是否显示
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, String msg, boolean show, int duration) {
		if (!show)
			return;
		MyApplication.toastMgr.builder.display(msg, duration);
	}

	/**
	 * toast 时间（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 */
	public static void toast(Context c, int resid) {
		toast(c, resid, true);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, int resid, int duration) {
		toast(c, resid, true, duration);
	}

	/**
	 * toast 时间（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param show
	 *            是否显示
	 */
	public static void toast(Context c, int resid, boolean show) {
		toast(c, resid, show, Toast.LENGTH_LONG);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param show
	 *            是否显示
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, int resid, boolean show, int duration) {
		if (!show)
			return;
		MyApplication.toastMgr.builder.display(resid, duration);
	}

	/**
	 * 统一弹框信息 "网络连接失败,请稍后再试"
	 *
	 * 调接口后 接口返回信息为 null 时，统一报错信息为 “网络连接失败,请稍后再试”
	 */

	public static void toastFailNet(Context c) {
		toast(c, "网络连接失败,请稍后再试", true);
	}


	public static void showToast1(Context context,int imgage ,String text){
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER, 0, 0);
		View v = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
		TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
		tv_name.setText(text);
		ImageView iv_icon = (ImageView) v.findViewById(R.id.iv_icon);
		iv_icon.setBackgroundResource(imgage);
		toast.setView(v);
		toast.show();
	}

//	public static void showToast2(Context context,String title ,String name){
//		Toast toast = new Toast(context);
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		View v = LayoutInflater.from(context).inflate(R.layout.xfb_toast_2, null);
//		TextView tv_title = (TextView) v.findViewById(R.id.tv_title_toast_2);
//		TextView tv_name = (TextView) v.findViewById(R.id.tv_name_toast_2);
//		if (StringUtils.isNullOrEmpty(title)){
//			tv_title.setVisibility(View.GONE);
//		}else{
//			tv_title.setText(title);
//		}
//		tv_name.setText(name);
//		toast.setView(v);
//		toast.setDuration(Toast.LENGTH_LONG);
//		toast.show();
//	}
//
//	/**自定义toast 举报相关*/
//	public static void showToast3(Context context,int imgage ,String text){
//		Toast toast = new Toast(context);
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		View v = LayoutInflater.from(context).inflate(R.layout.layout_toast_report, null);
//		TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
//		tv_name.setText(text);
//		ImageView iv_icon = (ImageView) v.findViewById(R.id.iv_icon);
//		iv_icon.setImageResource(imgage);
//		toast.setView(v);
//		toast.show();
//	}

	/**
	 * 检查SDCard是否存在
	 * 
	 * @return
	 */
	public static boolean checkSDCardPresent() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;

	}

	/**
	 * 检查SDCard是否可读
	 * 
	 * @return
	 */
	public static boolean checkSDCardRead() {
		if (checkSDCardPresent())
			return Environment.getExternalStorageDirectory().canRead();
		else
			return false;
	}

	/**
	 * 检查SDCard是否可写
	 * 
	 * @return
	 */
	public static boolean checkSDCardWriter() {
		if (checkSDCardPresent())
			return Environment.getExternalStorageDirectory().canWrite();
		else
			return false;
	}

	/**
	 * 检查sdcard的剩余容量是否超过size
	 * 
	 * @param size
	 *            单位是KB
	 * @return 超过true 否则false
	 */
	public static boolean checkSDCardCapacity(int size) {
		// 取得sdcard文件路径
		File pathFile = Environment.getExternalStorageDirectory();
		StatFs statfs = new StatFs(pathFile.getPath());
		// 获取SDCard上每个block的SIZE
		long blocSize = statfs.getBlockSize();
		// 获取可供程序使用的Block的数量
		long availaBlock = statfs.getAvailableBlocks();
		if ((availaBlock * blocSize / 1024) > size)
			return true;
		else
			return false;
	}

	/**
	 * 检查sdcard中是否存在制定路径的文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkSDCardFile(String path) {
		if (path == null || "".equals(path.trim()))
			return false;
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 解压数据库文件并保存(注意这个方法比较耗时请在线程里使用)
	 * 
	 * @param fileName
	 *            数据库文件名称
	 * @param context
	 * @throws Exception
	 */
	public static void UnZipFolder(String fileName, Context context)
			throws Exception {
		// 获取指定数据库绝对路径
		String outPathString = context.getDatabasePath("db").getAbsolutePath()
				+ "/";
		outPathString = outPathString.substring(0, outPathString.length() - 3);
		UnZipFolder(fileName, outPathString, context);
	}

	public static void UnZipFolder(String fileName, String outPathString,
			Context context) throws Exception {
		InputStream in = context.getAssets().open(fileName);
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(
				in);
		java.util.zip.ZipEntry zipEntry;
		String szName = "";
		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();
			if (zipEntry.isDirectory()) {
				szName = szName.substring(0, szName.length() - 1);
				File folder = new File(outPathString
						+ File.separator + szName);
				folder.mkdirs();
			} else {
				File file = new File(outPathString
						+ File.separator + szName);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
				FileOutputStream out = new FileOutputStream(
						file);
				int len;
				byte[] buffer = new byte[1024];
				while ((len = inZip.read(buffer)) != -1) {
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}
		inZip.close();
	}

	/**
	 * 保存数据库文件(注意这个方法比较耗时请在线程里使用)
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static boolean retrieveApkFromAssets(String fileName, Context context) {
		String outPathString = context.getDatabasePath("db").getAbsolutePath()
				+ "/";
		outPathString = outPathString.substring(0, outPathString.length() - 3);
		return retrieveApkFromAssets(fileName, outPathString, context);
	}

	public static boolean retrieveApkFromAssets(String fileName,
			String outPathString, Context context) {
		boolean bRet = false;

		try {
			InputStream is = context.getAssets().open(fileName);

			File file = new File(outPathString + "/" + fileName);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);

			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}

			fos.close();
			is.close();

			bRet = true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bRet;
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param activity
	 *            要隐藏软键盘的activity
	 */
	public static void hideSoftKeyBoard(Activity activity) {
		final View v = activity.getWindow().peekDecorView();
		if (v != null && v.getWindowToken() != null) {
			try {
				((InputMethodManager) activity
						.getSystemService(Context.INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(activity.getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showSoftKeyBroad(Context context, EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// only will trigger it if no physical keyboard is open
		mgr.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * @deprecated 判断网络是否可用
	 *
	 * @return 可用返回true 否则返回false
	 */
	public static boolean isNetworkAvailable(Activity activity) {
		ConnectivityManager connectivity = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		UtilsLog.e("isNetworkAvailable", "isNetworkAvailable");
		if (connectivity == null) {
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
				toast(activity, "网络异常，请检查网络状态");
			}
		}
		return false;
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void hideSoftKeyBroad(Context context, EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	/**
	 * 显示软键盘，和上面的showSoftKeyBroad方法的区别在于，如果从其他activity返回的时候需要延迟一点才能显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showKeyBoardLater(final Context context,
			final EditText editText) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				showSoftKeyBroad(context, editText);
			}
		}, 500);
	}

	/**
	 * 显示软键盘，和上面的showSoftKeyBroad方法的区别在于，如果从其他activity返回的时候需要延迟一点才能显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showKeyBoardLater(final Context context,
			final EditText editText, long laterTime) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				showSoftKeyBroad(context, editText);
			}
		}, laterTime);
	}

	/**
	 * 获取网络连接类型
	 * 
	 * @return -1表示没有网络
	 */
	public static final int TYPE_WIFI = 0;
	public static final int TYPE_3G = 1;
	public static final int TYPE_GPRS = 2;

//	public static final int getNetWorkType(Context c) {
//		ConnectivityManager conn = (ConnectivityManager) c
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		if (conn == null) {
//			return -1;
//		}
//		NetworkInfo info = conn.getActiveNetworkInfo();
//		if (info == null || !info.isAvailable()) {
//			return -1;
//		}
//
//		int type = info.getType(); // MOBILE（GPRS）;WIFI
//		if (type == ConnectivityManager.TYPE_WIFI) {
//			return TYPE_WIFI;
//		} else {
//			TelephonyManager tm = (TelephonyManager) c
//					.getSystemService(Context.TELEPHONY_SERVICE);
//			switch (tm.getNetworkType()) {
//			case TelephonyManager.NETWORK_TYPE_CDMA:
//				return TYPE_GPRS;
//			case TelephonyManager.NETWORK_TYPE_EDGE:
//				return TYPE_GPRS;
//			case TelephonyManager.NETWORK_TYPE_GPRS:
//				return TYPE_GPRS;
//			default:
//				return TYPE_3G;
//			}
//		}
//	}

//	public static final String getConnMode(Context context) {
//		ConnectivityManager conn = (ConnectivityManager) context
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		if (conn == null) {
//			return null;
//		}
//		NetworkInfo info = conn.getActiveNetworkInfo();
//		if (info == null || !info.isAvailable()) {
//			return null;
//		}
//
//		int type = info.getType(); // MOBILE（GPRS）;WIFI
//		if (type == ConnectivityManager.TYPE_WIFI) {
//			return APN_TYPE_WIFI;
//		} else {
//			return getApnType(context);
//		}
//	}

	private static Uri PREFERRED_APN_URI = Uri
			.parse("content://telephony/carriers/preferapn");
	public static String APN_TYPE_WIFI = "wifi";
	public static String APN_TYPE_CTNET = "ctnet";
	public static String APN_TYPE_CTWAP = "ctwap";
	public static String APN_TYPE_CMNET = "cmnet";
	public static String APN_TYPE_CMWAP = "cmwap";
	public static String APN_TYPE_UNINET = "uninet";
	public static String APN_TYPE_UNIWAP = "uniwap";

	public static String getApnType(Context context) {
		String apntype = "nomatch";
		try {
			Cursor c = context.getContentResolver().query(PREFERRED_APN_URI,
					null, null, null, null);
			c.moveToFirst();
			String user = c.getString(c.getColumnIndex("user")).toLowerCase();
			c.close();
			if (user.startsWith(APN_TYPE_CTNET)) {
				apntype = APN_TYPE_CTNET;
			} else if (user.startsWith(APN_TYPE_CTWAP)) {
				apntype = APN_TYPE_CTWAP;
			} else if (user.startsWith(APN_TYPE_CMNET)) {
				apntype = APN_TYPE_CMNET;
			} else if (user.startsWith(APN_TYPE_CMWAP)) {
				apntype = APN_TYPE_CMWAP;
			} else if (user.startsWith(APN_TYPE_UNINET)) {
				apntype = APN_TYPE_UNINET;
			} else if (user.startsWith(APN_TYPE_UNIWAP)) {
				apntype = APN_TYPE_UNIWAP;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apntype;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int dip2px(float dpValue) {
		final float scale = MyApplication.getSelf().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据日期获得星期
	 * 
	 * @param strDate
	 *            2014-06-10
	 * @return
	 */
	public static String getWeekOfDate(String strDate) {
		String[] dateDetail = strDate.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateDetail[0]),
				Integer.parseInt(dateDetail[1]) - 1,
				Integer.parseInt(dateDetail[2]));// 2002-03-28 星期四
		return "周" + getBigWeek(cal.get(Calendar.DAY_OF_WEEK));
	}

	public static String getBigWeek(int dayofweek) {
		// Calendar中1-星期天，2-星期一，3-星期二，4-星期三，5-星期四，6-星期五，7-星期六
		String[] wee = { "", "天", "一", "二", "三", "四", "五", "六" };
		return wee[dayofweek];
	}

	public static String getDate(String a) {
		String b = a.substring(5, a.length());
		String c = b.substring(0, 1);
		if ("0".equals(c)) {
			b = a.substring(6, a.length()).replace("-", "/");
		}

		return b;
	}

	public static String getDateWithOutTime(String a) {
		// 2014/5/8 0:00:00
		// 2014-01-27 10-04
		String b = a.substring(5, a.length());
		String c = b.substring(0, 1);
		// int d = b.indexOf(" ");
		if ("0".equals(c)) {
			b = a.substring(6, a.length()).replace("-", "/");
		} else {
			b = b.substring(0, b.length());
		}
		return b;
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");// yyyy年MM月dd日
																	// HH:mm:ss
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);

		if (StringUtils.isNullOrEmpty(str)) {
			return "";
		}
		return str;
	}

	public static String comitReport(String str) {
		if (StringUtils.isNullOrEmpty(str)) {
			str = "0";
		}

		return str;
	}

	public static void showAnim(final ImageView view) {
		final Bitmap originalBitmap = ((BitmapDrawable) view.getDrawable())
				.getBitmap();
		final Bitmap dstBitmap = Bitmap.createBitmap(originalBitmap.getWidth(),
				originalBitmap.getHeight(), Config.ARGB_8888);
		final Canvas canvas = new Canvas(dstBitmap);
		view.setImageBitmap(dstBitmap);
		view.setTag(0);
		view.postDelayed(new Runnable() {

			@Override
			public void run() {
				int count = (Integer) view.getTag();
				if (count >= dstBitmap.getWidth()) {
					count = 0;
					return;
				}
				canvas.drawBitmap(originalBitmap, 0, 0, null);
				Paint paint = new Paint();
				paint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
				canvas.drawRect(count, 0, dstBitmap.getWidth(),
						dstBitmap.getHeight(), paint);
				view.postInvalidate();
				count += 30;
				view.setTag(count);
				view.postDelayed(this, 50);
			}
		}, 10);

	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dpValue * scale + 0.5f);
	}

	/**
	 * 判断当前月份是否在项目执行期内
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static boolean isRuntime(String date) {
//		String discountEndTime = SouFunApplication.getSelf().getUserInfo().DiscountEtime;
		String discountEndTime = "";
		if (StringUtils.isNullOrEmpty(discountEndTime)) {
			return false;
		}
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currureDate = sDateFormat.format(new Date(System
				.currentTimeMillis()));
		int cur = Integer.parseInt(currureDate.replace("-", ""));

		int pos = discountEndTime.indexOf(" ");
		String time = discountEndTime.substring(0, pos).replace("-", "");
		int da = Integer.parseInt(time);

		if (cur <= da) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断当前程序是否处于活动状态
	 * 
	 * @return
	 */
	public static boolean isAppOnForeGround(Context c) {
		if (c == null) {
			return false;
		}
		ActivityManager activityManager = (ActivityManager) c
				.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = c.getPackageName();
		List<RunningAppProcessInfo> appProcessesList = activityManager
				.getRunningAppProcesses();
		if (appProcessesList == null) {
			return false;
		}
		for (RunningAppProcessInfo runningAppProcessInfo : appProcessesList) {
			if (runningAppProcessInfo.processName.equals(packageName)
					&& runningAppProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 用来判断服务是否运行
	 * 
	 * @param mContext
	 * @param className
	 *            判断的服务名字
	 * @return true在运行 false 不在运行
	 */
	public static boolean isServiceRunning(Context mContext, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}




	/**
	 * 判断是否联网
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetConn(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectivityManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				String name = info.getTypeName();
				UtilsLog.e("chat", "联网方式" + name);
				return true;
			} else {
				UtilsLog.e("chat", "断网");
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static void showPushDialog(Context context){
//		SoufunDialog dialog = new SoufunDialog.Builder(context)
//				.setTitle(R.string.dialog_title)
//				.setIcon(R.drawable.dialog_alert_icon)
//				.setMessage("你确定要退出吗？")
//				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						SouFunApplication.getSelf().setLogined(false);
//						/**
//						 * 退出登录的时候 结束掉服务
//						 */
//						SouFunApplication.getSelf().ActivitysDestroy();
//						dialog.dismiss();
//					}
//				})
//				.setNegativeButton(R.string.dialog_cancel,
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//												int which) {
//								dialog.dismiss();
//							}
//						}).create();
//		dialog.show();
	}

	/**
	 * DES加密功能
	 * @param data
	 * @param sKey
	 * @param charst
	 * @return
	 * @throws Exception
	 */
	public static String des3EncodeECB(String data, String sKey, String charst)
			throws Exception {
		SecretKey deskey = null;
		DESKeySpec spec = new DESKeySpec(sKey.getBytes(charst));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(sKey.getBytes(charst));
		cipher.init(Cipher.ENCRYPT_MODE, deskey, iv);
		byte[] bOut = cipher.doFinal(data.getBytes(charst));
		return bytesToHexString(bOut);
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * DES解密
	 * @param plaintext
	 * @param AuthKey
	 * @param AuthIv
	 * @param encoding
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeDES(String plaintext, String AuthKey,String AuthIv,String encoding) throws NoSuchAlgorithmException,
			NoSuchPaddingException, Exception, UnsupportedEncodingException {
		byte[] bytesrc = StringToHex(plaintext);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(AuthKey.getBytes(encoding));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(AuthIv.getBytes(encoding));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte,encoding);
	}

	public static byte[] StringToHex(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}

	public static Date StringToDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date time = df.parse(date);//String转Date
			return time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断time是否在from，to之内
	 *
	 * @param time 指定日期
	 * @param from 开始日期
	 * @param to   结束日期
	 * @return
	 */
	public static boolean belongCalendar(Date time, Date from, Date to) {
		Calendar date = Calendar.getInstance();
		date.setTime(time);

		Calendar after = Calendar.getInstance();
		after.setTime(from);

		Calendar before = Calendar.getInstance();
		before.setTime(to);

		if (date.after(after) && date.before(before)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断某日期跟跟现在相隔多少天
	 * 正数表示在当前时间之后，负数表示在当前时间之前
	 * @param date
	 * @return
	 */
	public static long getDistanceDays(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		long days = 0;
		try {
			Date time = df.parse(date);//String转Date
			Date now = new Date();//获取当前时间
			long time1 = time.getTime();
			long time2 = now.getTime();
			long diff = time1 - time2;
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}





	/**
	 * 获得mac地址
	 *
	 * @return
	 */
	public static String getMac() {
		String macSerial = null;
		String str = "";
		try {
			Process pp = Runtime.getRuntime().exec(
					"cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (; null != str;) {
				str = input.readLine();
				if (str != null) {
					macSerial = str.trim();// 去空格
					break;
				}
			}
		} catch (IOException ex) {
			// 赋予默认值
			ex.printStackTrace();
		}
		if (!TextUtils.isEmpty(macSerial)) {
			macSerial = macSerial.replace(":", "-");
		}
		return macSerial;
	}

	/**
	 * 相机是否可用
	 * @return 返回true 表示可以使用  返回false表示不可以使用
	 */
	public static boolean isCameraCanUse() {
		boolean canUse = true;
		Camera mCamera = null;
		try {
			mCamera = Camera.open();
			mCamera.getParameters();
			UtilsLog.e("camera", "can open");
		} catch (Exception e) {
			UtilsLog.e("camera", "can't open");
			canUse = false;
		}
		if (canUse) {
			if (null != mCamera) {
				mCamera.release();
				mCamera = null;
			}
		}
		return canUse;
	}

	/**
	 * 验证麦克风权限（涉及读写SD卡权限）
	 * by google:
	 *     44100Hz is currently the only rate that is guaranteed to work on all devices
	 *     AudioFormat#CHANNEL_IN_MONO is guaranteed to work on all devices.
	 *     PCM 16 bit per sample. Guaranteed to be supported by devices.
	 */
	public static boolean isMediaRecorderCanUse(){
		long startTime = 0;
		AudioRecord recorder = null;
		try {
			recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
					44100,
					AudioFormat.CHANNEL_IN_MONO,
					AudioFormat.ENCODING_PCM_16BIT,
					AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT));
			recorder.startRecording();
			startTime = System.currentTimeMillis();
		}catch (Exception e){
			UtilsLog.e("mediaRecorder", "can't use");
		}finally {
			if (recorder != null){
				recorder.release();
				recorder = null;
			}
		}
		return startTime != 0;
	}

	/**判断是否有麦克风和相机、存储权限*/
	public static boolean hasDFPermission(Context context){
		StringBuilder text = new StringBuilder();
		if(!Utils.isCameraCanUse()){
			text.append("相机");
		}
		if(!Utils.isMediaRecorderCanUse()){
			text.append(text.length() > 0 ? "、麦克风" : "麦克风");
		}

		if(!Utils.checkSDCardPresent()){
			text.append(text.length() > 0 ? "、存储" : "存储");
		}
		if(!StringUtils.isNullOrEmpty(text.toString())){
			Utils.toast(context, "需启用" + text.toString() + "权限，请先打开"  + text.toString() + "权限");
			return false;
		}
		return true;
	}

	public static String getDateTime(String time) {

		if(StringUtils.isNullOrEmpty(time)){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = formatter.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f.format(d);
	}



	// 获取屏幕的大小
	public static Screen getScreenPix(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return new Screen(dm.widthPixels, dm.heightPixels);
	}

	public static class Screen {

		public int widthPixels;
		public int heightPixels;


		public Screen(int widthPixels, int heightPixels) {
			this.widthPixels = widthPixels;
			this.heightPixels = heightPixels;
		}

		@Override
		public String toString() {
			return "(" + widthPixels + "," + heightPixels + ")";
		}

	}
}