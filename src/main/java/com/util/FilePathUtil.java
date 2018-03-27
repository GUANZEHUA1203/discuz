package com.util;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 2015年5月21日
 * 
 * @author R.ZENG
 *
 *         文件名生成规则fileType /[yyyymm]/[d]/[fileid]_[rand].[fileExt]
 *         如果文件是图片格式，会生成缩略图，文件名会直接添加.thumb后缀 规则参数 yyyymm：
 *         时间的年月，固定6位字符。如200505 d： 时间的日期，值范围1~31。如5 fileid: 上传文件的ID，hex(int64)
 *         rand： 防盗链随机数，hex(int32)。 fileExt： 文件扩展名。
 *         
 *         
 *         主要通过文件Id(Id唯一性) 计算文件路径
 *         通过文件路径计算文件Id
 */
public class FilePathUtil {

	private volatile long startTime;
	private volatile long endTime;
	private volatile int year;
	private volatile int month;

	public static void createFolder(String path) {
		File file = new File(path);
		if (!file.isFile() && !file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 返回日期路径字符串
	 * 
	 * @return
	 */
	public String getDatePathString() {
		long now = System.currentTimeMillis();
		// 在此区间范围之内
		if (now >= startTime && now < endTime) {
			return pieceDatePathString();
		} else {// 如果不在就需要重新设置
			setDatePathProperties(now);
			return pieceDatePathString();
		}
	}

	/**
	 * 两种情况会调用该方法，一种是初始化的时候，一种是不在该时间范围的时候
	 */
	private void setDatePathProperties(long now) {
		synchronized (this) {
			if (now >= startTime && now < endTime) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(now);

				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), 0);
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH) + 1;
				// day = calendar.get(Calendar.DAY_OF_MONTH);
				// hour = calendar.get(Calendar.HOUR_OF_DAY);

				startTime = calendar.getTimeInMillis();
				endTime = startTime + 60 * 60 * 1000;
			}
		}
	}

	// 路径 /年月/月/
	private String pieceDatePathString() {
		String monthStr = "";
		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = month + "";
		}
		return String.valueOf(year + monthStr + "/" + monthStr + "/");
	}

	// 根据fileId得到路径，fileType 可以创建不同文件夹
	public static String getFilePath(int fileType, long fileId) {
		if (fileId == 0) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		//calendar.setTimeInMillis(fileId);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		// 路径 /年月/月/
		String monthStr = null;
		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = month + "";
		}
		return String
				.valueOf("/" + fileType + "/" + year + monthStr + "/" + monthStr + "/" + toHex(fileId, 16) + ".png");
	}

	// 通过路径获取文件ID
	public static long findFileId(String filePath) {
		if (StringUtils.isNotBlank(filePath) && filePath.contains(".")) {
			filePath = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
			if (StringUtils.isNotBlank(filePath)) {
				return Long.parseLong(filePath, 16);
			}
		}
		return 0;
	}

	/**
	 * 
	 *
	 * @Title: find @Description:获取某个文件夹里面的某些类型的文件 @param @param
	 *         path @param @param reg @param @return 设定文件 @return Set<String>
	 *         返回类型 @throws
	 */
	public static synchronized Set<String> scanDir(String path, String reg) {
		Pattern pat = Pattern.compile(reg);
		File file = new File(path);
		Set<String> set = new HashSet<String>();
		if (file.isDirectory()) {
			File[] arr = file.listFiles();
			for (int i = 0; i < arr.length; i++) {
				// 判断是否是文件夹，如果是的话，再调用一下find方法
				if (arr[i].isDirectory()) {
					scanDir(arr[i].getAbsolutePath(), reg);
					// break;
				}
				Matcher mat = pat.matcher(arr[i].getAbsolutePath());
				// 根据正则表达式，寻找匹配的文件
				if (mat.matches()) {
					// 这个getAbsolutePath()方法返回一个String的文件绝对路径
					set.add(arr[i].getAbsolutePath());
				}

			}
		}
		return set;
	}

	// 转换成16进制
	public static String toHex(long value, int hex) {
		String result = Long.toString(value, hex);
		if (value < 16) {
			result = "0" + result;
		}
		return result;
	}

	public static void main(String args[]) {
		// System.out.println(findFileId("/14f82ecd757.jpg"));
		// System.out.println(getAvatarFilePath(1, 1441013289105L));
		System.out.println(getFilePath(1, 1440001L));

		// System.out.println(findFileId("/1/201508/08/14f8315d091.jpg"));
		System.out.println(findFileId("/1/201801/01/15f901.jpg"));

	}

}