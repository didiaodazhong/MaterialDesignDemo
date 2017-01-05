package com.peixing.materialdesigndemo;

import java.util.List;

/**
 * Created by peixing on 2017/1/1.
 */

public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermission);
}
