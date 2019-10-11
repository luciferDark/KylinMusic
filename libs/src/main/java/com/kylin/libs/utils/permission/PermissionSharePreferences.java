package com.kylin.libs.utils.permission;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 权限SP
 * @author  Kylin
 * @date 2017/12/12.
 */
public class PermissionSharePreferences {
	private static PermissionSharePreferences mInstance;
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor editor;
	private static String FILE_NAME = "ddianle_permission_sp";
	private static String PERMISSION_TYPE = "permission_type_";

	public static PermissionSharePreferences instance(Context c) {
		if (mInstance == null) {
			synchronized (PermissionSharePreferences.class) {
				if (mInstance == null) {
					mInstance = new PermissionSharePreferences(c);
				}
			}
		}
		return mInstance;
	}

	private PermissionSharePreferences(Context c) {
		this.mSharedPreferences = c.getSharedPreferences(FILE_NAME, 0);
		this.editor = this.mSharedPreferences.edit();
	}

	public int getPermission(int permissionType) {
		int result = this.mSharedPreferences.getInt(PERMISSION_TYPE
				+ permissionType, PackageManager.PERMISSION_DENIED);
		Log.d("Unity", "==getPermission permissionType:" + PERMISSION_TYPE
				+ permissionType + "---grantResult=" + result);
		return result;
	}

	public void setPermissonType(int permissionType, int grantResult) {
		Log.d("Unity", "==setPermissonType permissionType :"
				+ PERMISSION_TYPE + permissionType + "---grantResult="
				+ grantResult);
		this.editor.putInt(PERMISSION_TYPE + permissionType, grantResult);
		this.editor.commit();
	}
	
	public boolean isPermissioned(Context context, String permission){
		boolean result = PackageManager.PERMISSION_GRANTED == 
				instance(context). getPermission(PermissionGroups.getCheckType(Manifest.permission.CAMERA));
		return result;
	}
}
