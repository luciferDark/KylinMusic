package com.kylin.libs.utils.permission;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 *
 * @author  Kylin
 * @date 2018/3/30.
 */
@SuppressLint("NewApi")
public class PermissionUtil {
	public static int SDKVERSION = 23;

	/**
	 * 判断是否有某个权限
	 * 
	 * @param context
	 * @param permission
	 * @return
	 */
	public static boolean hasPermission(Context context, String permission) {
		if (Build.VERSION.SDK_INT >= SDKVERSION) {
			if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
				Log.d("Unity", "hasPermission?permission " + permission + "--没有权限");
				return false;
			}
		}
		Log.d("Unity", "hasPermission?permission " + permission + "--有权限");
		return true;
	}

	/**
	 * 弹出对话框请求权限
	 * 
	 * @param activity
	 * @param permissions
	 * @param requestCode
	 */
	public static void requestPermissions(Activity activity,
			String[] permissions, int requestCode) {
		if (Build.VERSION.SDK_INT >= SDKVERSION) {
			for (String permission : permissions) {
				Log.d("Unity", "requestPermissions:" + permission);
			}
			ActivityCompat.requestPermissions(activity, permissions,requestCode);
		}
	}

	/**
	 * 返回缺失的权限
	 * 
	 * @param context
	 * @param permissions
	 * @return 返回缺少的权限，null 意味着没有缺少权限
	 */
	public static String[] getDeniedPermissions(Context context,
			String[] permissions) {
		if (Build.VERSION.SDK_INT >= SDKVERSION) {
			ArrayList<String> deniedPermissionList = new ArrayList<String>();
			for (String permission : permissions) {
				Log.d("Unity", "permission:" + permission);
				if (!hasPermission(context, permission)) {
					//没有权限，添加到拒绝权限组集合
					Log.d("Unity", "permission:" + permission + " is not granted, add to deniedList");
					deniedPermissionList.add(permission);
				} else {
					//有权限
					Log.d("Unity", "permission:" + permission + " is  granted.");
				}
			}
			int size = deniedPermissionList.size();
			if (size > 0) {
				return deniedPermissionList
						.toArray(new String[deniedPermissionList.size()]);
			}
		}
		return null;
	}

}
