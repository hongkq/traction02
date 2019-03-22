package com .example.traction02.util.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.traction02.util.UrlBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static android.content.Context.MODE_PRIVATE;

/**
 * 项目名称：
 * 类描述：
 * 创建人：zhaowei
 * 创建时间：2017/4/19 9:20
 * 修改人：Administrator
 * 修改时间：2017/4/19 9:20
 * 修改备注：
 */
public class Util {

    public static String urlHttp;
    public static String urlPort ;

    /**
     * 描述：保存数据到SharedPreferences对象中
     * @param ipUrl
     * @param ipPort
     */

    public static void saveSetting(String ipUrl, String ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences("setting", MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putString("ipUrl", ipUrl);
        editor.putString("ipPort", ipPort);
        editor.commit();
    }

    /**
     * 描述：获取数据到SharedPreferences对象中
     * @return
     */
    public static UrlBean loadSetting(Context context) {
        UrlBean urlBean=new UrlBean();

        SharedPreferences loadSettingLoad = context.getSharedPreferences("setting", MODE_PRIVATE);
        //这里是将setting.xml 中的数据读出来
        urlBean.setUrl( loadSettingLoad.getString("ipUrl", "") );
        urlBean.setPort( loadSettingLoad.getString("ipPort", "") );

        String urlSetting = "http://" + urlHttp+ ":" + urlPort + "/";
        return urlBean;
    }
    /*
     *   手机号正则判断
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    public static  void  clear(Context context){


    }
}
