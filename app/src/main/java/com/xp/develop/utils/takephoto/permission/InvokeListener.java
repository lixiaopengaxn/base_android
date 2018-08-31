package com.xp.develop.utils.takephoto.permission;


import com.xp.develop.utils.takephoto.model.InvokeParam;

/**
 * 授权管理回调
 */
public interface InvokeListener {
    PermissionManager.TPermissionType invoke(InvokeParam invokeParam);
}
