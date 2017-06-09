package gg.googlrewardads;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Go Goal on 3/4/2017.
 */

public class Constant {


    public  static  String host="http://52.14.88.50/fbvideo/api/";
    //public  static  String host="http://www.sandevelopment.net/fbvideo/api/";
    public static String apikey = "Cxdiurnd84067";
    static Activity ac;
    static SharedPreferences prefs;
    static Long usertime;


    public static void pushactivity(Activity ac1) {
        ac=ac1;
        try {

            PackageInfo info = ac.getPackageManager().getPackageInfo("gg.prodwreward", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                apikey=sign;

              /*  String path = Environment.getExternalStorageDirectory() + "/asd/";
                File root = new File(path);
                File gpxfile = new File(root, "samples.txt");
                FileWriter writer = new FileWriter(gpxfile);
                writer.append(sign);
                writer.flush();
                writer.close();*/


            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
    }


    public static void setcontext() {
        prefs = ac.getSharedPreferences("dlist",
                ac.MODE_PRIVATE);
        usertime=prefs.getLong("time",0);

        if (usertime==0){
            long fivemin=15*60*1000;
            long newuser=System.currentTimeMillis()-fivemin;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("time", newuser);
            editor.commit();
        }


    }

    public static void mytime() {
        usertime=prefs.getLong("time",0);
        Log.e("mytime",usertime+"");
    }


    public static long getremaintime() {
        //hh



        long mytime=prefs.getLong("time",0);
        long nowtime=System.currentTimeMillis();



        long remain=nowtime-mytime;


        Log.e("mytime","/"+mytime+"/"+nowtime+"/"+remain);

        long fivemin=15*1000;

        long total=fivemin-remain;

        if (total<1){
            total=0;
        }

        return total;
    }

    public static void committime() {

        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("time", System.currentTimeMillis());
        editor.commit();

        Log.e("comm",System.currentTimeMillis()+"");
    }
}
