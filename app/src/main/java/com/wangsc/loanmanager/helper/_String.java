package com.wangsc.loanmanager.helper;

import android.content.Intent;
import android.widget.EditText;

/**
 * Created by 阿弥陀佛 on 2015/6/23.
 */
public class _String {


    public static String touzi_ed_values22 = "";

    public static String toCardNumberFormat(EditText edtext){

        String valueStr = edtext.getText().toString().replace(" ", "");

        if (valueStr.isEmpty())
            return "";

        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = 0;i<valueStr.length() ; i++) {
            result.append(valueStr.charAt(i));
            if (++count % 4 == 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * 在数字型字符串千分位加逗号
     *
     * @param edtext
     * @return sb.toString()
     */
    public static String toMoneyFormat(EditText edtext) {

        String valueStr = edtext.getText().toString().replace(",", "");

        if (valueStr.isEmpty())
            return "";
        try {
            Double.parseDouble(valueStr);
        } catch (Exception e) {
            return valueStr;
        }

        String valueStr1 = "";
        String valueStr2 = "";
        if (valueStr.contains(".")) {
            valueStr1 = valueStr.substring(0, valueStr.indexOf("."));
            valueStr2 = valueStr.substring(valueStr.indexOf("."), valueStr.length());
        } else {
            valueStr1 = valueStr;
        }

        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = valueStr1.length() - 1; i >= 0; i--) {
            result.append(valueStr1.charAt(i));
            if (++count % 3 == 0 && count < valueStr1.length()) {
                result.append(",");
            }
        }
        return result.reverse().toString() + valueStr2;
    }


    public static String concat(Object... strings) {
        StringBuilder sb = new StringBuilder();
        for (Object str : strings) {
            if (str != null)
                sb.append(str.toString());
        }
        return sb.toString();
    }

    public static boolean IsNullOrEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 时间字段，月、日、时、分、秒，小于10的，设置前缀‘0’。
     *
     * @param x
     * @return
     */
    public static String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }

    public static String formatToCardNumber(String number) {
        String num = "";
        int index = number.length() - 1;
        for (int i = 0; i < number.length(); i++) {
            index = number.length() - 1 - i;
            if (i % 4 == 0 && i != 0) {
                num = " " + num;
            }
            num = number.charAt(index) + num;
        }
        return num;
    }
}
