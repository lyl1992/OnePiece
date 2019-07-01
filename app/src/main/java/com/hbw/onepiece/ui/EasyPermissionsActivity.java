package com.hbw.onepiece.ui;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.hbw.onepiece.R;
import com.hebiwen.luffy.base.BaseActivity;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class EasyPermissionsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    public static final int CAMERA_AND_ACCESS_FINE_LOCATION = 1001;

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
            Toast.makeText(EasyPermissionsActivity.this, "用户授权成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (requestCode == CAMERA_AND_ACCESS_FINE_LOCATION) {
            Toast.makeText(EasyPermissionsActivity.this, "用户授权失败", Toast.LENGTH_SHORT).show();
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                new AppSettingsDialog.Builder(this).build().show();
            }
        }
    }

    @AfterPermissionGranted(CAMERA_AND_ACCESS_FINE_LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, null,
                    CAMERA_AND_ACCESS_FINE_LOCATION, perms);
        }
    }

}
