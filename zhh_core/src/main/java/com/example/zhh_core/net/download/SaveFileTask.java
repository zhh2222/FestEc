package com.example.zhh_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.zhh_core.app.Zhh;
import com.example.zhh_core.net.callback.IRequest;
import com.example.zhh_core.net.callback.ISuccess;
import com.example.zhh_core.net.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author brett-zhu
 * created at 2019/3/3 10:14
 */
public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody body = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }


    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {

            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Zhh.getApplicationContext().startActivity(intent);
        }
    }
}
