package com.kylin.libs.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * @author  Kylin
 * @date 2018/3/30.
 */
public class PermissionHelper {
	private Activity mActivity;  
    private PermissionInterface mPermissionInterface;  
  
    public PermissionHelper(Activity activity, PermissionInterface permissionInterface) {  
        mActivity = activity;  
        mPermissionInterface = permissionInterface;  
    }  
  
    /**  
     * 开始请求权限。  
     * 方法内部已经对Android M 或以上版本进行了判断，外部使用不再需要重复判断。  
     * 如果设备还不是M或以上版本，则也会回调到requestPermissionsSuccess方法。  
     */  
    public void requestPermissions(){  
        String[] deniedPermissions = PermissionUtil.getDeniedPermissions(mActivity, mPermissionInterface.getPermissions());  
        if(deniedPermissions != null && deniedPermissions.length > 0){  
            PermissionUtil.requestPermissions(mActivity, deniedPermissions, mPermissionInterface.getPermissionsRequestCode());  
        }else{  
            mPermissionInterface.requestPermissionsSuccess(null,-1);  
			Log.d("Unity", "requestPermissionsSuccess(null,-1)");
            mPermissionInterface.requestOver();
        }  
    }  
  
    /**  
     * 在Activity中的onRequestPermissionsResult中调用  
     * @param requestCode  
     * @param permissions  
     * @param grantResults  
     * @return true 代表对该requestCode感兴趣，并已经处理掉了。false 对该requestCode不感兴趣，不处理。  
     */  
    public boolean requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){  
        if(requestCode == mPermissionInterface.getPermissionsRequestCode()){  
			Log.d("Unity", "requestPermissionsResult grantResults.length:" + grantResults.length);
            //是否全部权限已授权
            boolean isAllGranted = true;
            for(int result : grantResults){  
                if(result == PackageManager.PERMISSION_DENIED){  
                    isAllGranted = false;  
                    break;  
                }  
            }  
            PermissionSharePreferences ddlSP = PermissionSharePreferences.instance(mActivity);
            for (int i = 0; i < permissions.length; i++) {
				Log.d("Unity", "requestPermissionsResult:" + permissions[i]);
				int type = PermissionGroups.getCheckType(permissions[i]);
				if (i < grantResults.length) {
					ddlSP.setPermissonType(type, grantResults[i]);
					mPermissionInterface.requestPermissionsSuccess(permissions[i],grantResults[i]);  
				}
			}
            
            if(isAllGranted){  
                //已全部授权  
            }else{  
                //权限有缺失  
                mPermissionInterface.requestPermissionsFail();  
            }  
            
            mPermissionInterface.requestOver();
            return true;  
        }  
        return false;  
    }  
}
