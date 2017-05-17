package com.wangsc.loanmanager.helper;

import android.content.Intent;
import android.widget.EditText;

/**
 * Created by 阿弥陀佛 on 2015/6/23.
 */
public class _String {


    public static String touzi_ed_values22 = "";

    /**
     * 在数字型字符串千分位加逗号
     *
     * @param edtext
     * @return sb.toString()
     */
    public static String addComma(EditText edtext) {
        String valueStr =edtext.getText().toString().replace(",", "");
        if(valueStr.isEmpty())
            return "";

        String valueStr1 ="";
        String valueStr2 ="";
        if(valueStr.contains(".")){
            valueStr1 = valueStr.substring(0, valueStr.indexOf("."));
            valueStr2 = valueStr.substring(valueStr.indexOf("."), valueStr.length());
        }else{
            valueStr1 = valueStr;
        }

        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = valueStr1.length() - 1; i >= 0; i--) {
            result.append(valueStr1.charAt(i));
            if(++count%3==0&&count<valueStr1.length()){
                result.append(",");
            }
        }
        return result.reverse().toString()+valueStr2;
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


    /// <summary>
    /// 转换人民币大小金额
    /// </summary>
    /// <param name="num">金额</param>
    /// <returns>返回大写形式</returns>
    public static String CmycurD(double num) {
        String str1 = "零壹贰叁肆伍陆柒捌玖";            //0-9所对应的汉字
        String str2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; //数字位所对应的汉字
        String str3 = "";    //从原num值中取出的值
        String str4 = "";    //数字的字符串形式
        String str5 = "";  //人民币大写金额形式
        int i;    //循环变量
        int j;    //num的值乘以100的字符串长度
        String ch1 = "";    //数字的汉语读法
        String ch2 = "";    //数字位的汉字读法
        int nzero = 0;  //用来计算连续的零值是几个
        int temp;            //从原num值中取出的值

//        num = Math.round(Math.abs(num));    //将num取绝对值并四舍五入取2位小数
        num = Math.abs(num);    //将num取绝对值并四舍五入取2位小数
        str4 = ((long) (num * 100)) + "";        //将num乘100并转换成字符串形式
        j = str4.length();      //找出最高位
        if (j > 15) {
            return "溢出";
        }
        str2 = str2.substring(15 - j);   //取出对应位数的str2的值。如：200.55,j为5所以str2=佰拾元角分

        //循环取出每一位需要转换的值
        for (i = 0; i < j; i++) {
            str3 = str4.substring(i, i + 1);          //取出需转换的某一位的值
            temp = Integer.parseInt(str3);      //转换为数字
            if (i != (j - 3) && i != (j - 7) && i != (j - 11) && i != (j - 15)) {
                //当所取位数不为元、万、亿、万亿上的数字时
                if (str3 == "0") {
                    ch1 = "";
                    ch2 = "";
                    nzero = nzero + 1;
                } else {
                    if (str3 != "0" && nzero != 0) {
                        ch1 = "零" + str1.substring(temp * 1, temp * 1 + 1);
                        ch2 = str2.substring(i, i + 1);
                        nzero = 0;
                    } else {
                        ch1 = str1.substring(temp * 1, temp * 1 + 1);
                        ch2 = str2.substring(i, i + 1);
                        nzero = 0;
                    }
                }
            } else {
                //该位是万亿，亿，万，元位等关键位
                if (str3 != "0" && nzero != 0) {
                    ch1 = "零" + str1.substring(temp * 1, temp * 1 + 1);
                    ch2 = str2.substring(i, i + 1);
                    nzero = 0;
                } else {
                    if (str3 != "0" && nzero == 0) {
                        ch1 = str1.substring(temp * 1, temp * 1 + 1);
                        ch2 = str2.substring(i, i + 1);
                        nzero = 0;
                    } else {
                        if (str3 == "0" && nzero >= 3) {
                            ch1 = "";
                            ch2 = "";
                            nzero = nzero + 1;
                        } else {
                            if (j >= 11) {
                                ch1 = "";
                                nzero = nzero + 1;
                            } else {
                                ch1 = "";
                                ch2 = str2.substring(i, i + 1);
                                nzero = nzero + 1;
                            }
                        }
                    }
                }
            }
            if (i == (j - 11) || i == (j - 3)) {
                //如果该位是亿位或元位，则必须写上
                ch2 = str2.substring(i, i + 1);
            }
            str5 = str5 + ch1 + ch2;

            if (i == j - 1 && str3 == "0") {
                //最后一位（分）为0时，加上“整”
                str5 = str5 + '整';
            }
        }
        if (num == 0) {
            str5 = "零元整";
        }
        return str5;
    }

    /**/
    /// <summary>
    /// 一个重载，将字符串先转换成数字在调用CmycurD(double num)
    /// </summary>
    /// <param name="num">用户输入的金额，字符串形式未转成decimal</param>
    /// <returns></returns>
    public static String CmycurD(String numstr) {
        try {
            double num = Double.parseDouble(numstr);
            return CmycurD(num);
        } catch (Exception e) {
            String msg = "";
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains("loanmanager")) {
                    msg += "行号：" + ste.getLineNumber()
                            + "\n错误信息：\n" + e.getMessage() + "\n";
                    break;
                }
            }
            return msg;
        }
    }
}
