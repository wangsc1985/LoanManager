package com.wangsc.loanmanager.helper;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.wangsc.loanmanager.model.DataContext;
import com.wangsc.loanmanager.model.RunLog;

/**
 * Created by 阿弥陀佛 on 2016/10/18.
 */

public class _Helper {



    public static void printException(Context context, Exception e) {
        if (e.getStackTrace().length == 0)
            return;
        String msg = "";
        for (StackTraceElement ste : e.getStackTrace()) {
            if (ste.getClassName().contains(context.getPackageName())) {
                msg += "类名：\n" + ste.getClassName()
                        + "\n方法名：\n" + ste.getMethodName()
                        + "\n行号：" + ste.getLineNumber()
                        + "\n错误信息：\n" + e.getMessage() + "\n";
            }
        }
        try {
            new AlertDialog.Builder(context).setMessage(msg).setPositiveButton("知道了", null).show();
        } catch (Exception e1) {
        }
//        addRunLog(context, "运行错误", msg);
        e.printStackTrace();
    }

    public static void addRunLog(Context context, String item, String message) {
        new DataContext(context).addRunLog(new RunLog(item, message));
    }
}
