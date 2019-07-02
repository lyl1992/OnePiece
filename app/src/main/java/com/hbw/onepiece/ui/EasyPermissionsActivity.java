package com.hbw.onepiece.ui;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.hbw.onepiece.R;
import com.hbw.onepiece.utils.ToastUtil;
import com.hebiwen.luffy.base.BaseActivity;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class EasyPermissionsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    public static final int CAMERA_AND_ACCESS_FINE_LOCATION = 1001;
    public String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_easy_permissions;
    }

    @Override
    public void initData() {
        super.initData();
        // 自定义权限弹出框
//        EasyPermissions.requestPermissions(
//                new PermissionRequest.Builder(this, RC_CAMERA_AND_LOCATION, perms)
//                        .setRationale(R.string.camera_and_location_rationale)
//                        .setPositiveButtonText(R.string.rationale_ask_ok)
//                        .setNegativeButtonText(R.string.rationale_ask_cancel)
//                        .setTheme(R.style.my_fancy_style)
//                        .build());
    }

    public void onClick(View view) {
        methodRequiresTwoPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == CAMERA_AND_ACCESS_FINE_LOCATION) {
            ToastUtil.getInstance().show("用户授权成功");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (requestCode == CAMERA_AND_ACCESS_FINE_LOCATION) {
            ToastUtil.getInstance().show("用户授权失败");
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                new AppSettingsDialog.Builder(this).build().show();
            }
        }
    }

    @AfterPermissionGranted(CAMERA_AND_ACCESS_FINE_LOCATION)
    private void methodRequiresTwoPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, null,
                    CAMERA_AND_ACCESS_FINE_LOCATION, perms);
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
        }
    }
}
