package com.crmproject.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

import androidx.core.app.ActivityCompat;

/**
 * 获取IMEI的工具类，能获取到手机真实IMEI的直接使用IMEI，不能的话获取sdk上的已有的uuid，手机文件系统上没有uuid，需要创建新的
 */
public class IMEIUtil {
    /**
     * 获取imei的方法
     * @param context
     * @return
     */
    public static String getImei(Context context){
        String imei = "";
        try {
            if ((ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                File file = new File(Environment.getExternalStorageDirectory(), ".fangsystem");
                if (!file.exists()){
                    file.createNewFile();
                }
                //获取IMEI
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    imei = tm.getDeviceId();
                    if (!StringUtils.isNullOrEmpty(imei)){
                        FileWriter fw = new FileWriter(file,false);//同时将真实的IMEI保存到文件中
                        fw.write(imei);
                        fw.close();
                    }
                }
                //如果获取到的手机IMEI为空，则从文件系统或取已经存储的uuid
                if (StringUtils.isNullOrEmpty(imei)){
                    if (file.exists()){//如果存储uuid的文件已存在，则读取文件获取uuid
                        FileReader fileReader = new FileReader(file);
                        BufferedReader r = new BufferedReader(fileReader);
                        String content = r.readLine();
                        if (!StringUtils.isNullOrEmpty(content)){//读取文件获取到的uuid不为空则使用
                            imei = content;
                        }else {//为空，则重新写入并赋值imei
                            FileWriter fw = new FileWriter(file,false);
                            String uuid = UUID.randomUUID().toString();
                            fw.write(uuid);
                            fw.close();
                            imei = uuid;
                        }
                    }else {//如果存储uuid的文件不存在，则创建新文件，并写入uuid
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file,false);
                        String uuid = UUID.randomUUID().toString();
                        fw.write(uuid);
                        fw.close();
                        imei = uuid;
                    }
                }
            }else {
                imei = UUID.randomUUID().toString();
            }
        }catch (Exception e){
            imei = UUID.randomUUID().toString();
        }

        return imei;
    }

}
