package com.kylin.libs.utils.permission;

/**
 * 权限请求接口
 * @author  kylin
 * @date  2017/12/12
 */
public interface PermissionInterface {
    /**
     * 设置请求权限请求码
     *
     * @return 返回请求码
     */
    int getPermissionsRequestCode();

    /**
     * 设置需要请求的权限
     *
     * @return 返回权限数组
     */
    String[] getPermissions();

    /**
     * 请求权限成功回调
     *
     * @param permission
     * @param grantResult
     */
    void requestPermissionsSuccess(String permission, int grantResult);

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFail();

    /**
     * 请求权限完成回调
     */
    void requestOver();
}
