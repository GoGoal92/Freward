package gg.googlrewardads;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import gg.googlrewardads.retorfit.Myclient;

/**
 * Created by Go Goal on 3/4/2017.
 */

public class Splash extends AppCompatActivity {

    static ProgressBar pb;
    static TextView load;
    static int counter = 0;
    static Activity ac;

    static Handler handler = new Handler();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ac = this;
        Constant.pushactivity(ac);
        Constant.setcontext();
      //  Constant.mytime();


        pb = (ProgressBar) findViewById(R.id.upload_progress);
        load = (TextView) findViewById(R.id.loading);
        phoneid pid = new phoneid(getApplicationContext());

        Myclient.getuseridanduserticket();

        load.setText("Checking your information ..");


    }

    public static void Feedback(String s) {



        Log.e("sssssssssssss",s);

        final String status=Jsonparser.getonestring(s,"status");


        if (status.equals("2")){

            final String upurl=Jsonparser.getonestring(s,"url");
            final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ac);


            dialogBuilder
                    .withTitle("Need to Update")
                    //.withTitle(null)  no title.set
                    .withTitleColor("#FFFFFF")                                  //def
                    .withDividerColor("#11000000")                              //def
                    .withMessage("Application Need to update")                     //.withMessage(null)  no Msg
                    .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                    .withDialogColor("#021626")                               //def  | withDialogColor(int resid)
                    // .withIcon(getResources().getDrawable(R.drawable.icon))
                    .withDuration(700)                                          //def
                    .withEffect(Effectstype.Shake)                                         //def Effectstype.Slidetop
                    .withButton1Text("Update")                                      //def gone
                    .withButton2Text("Cancel")                                  //def gone
                    .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                    .setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                                    .parse(upurl));
                            ac.startActivity(intent);

                            dialogBuilder.dismiss();
                        }
                    })
                    .setButton2Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogBuilder.dismiss();
                            ac.finish();
                        }
                    })
                    .show();

            dialogBuilder.setCancelable(false);
            dialogBuilder.setCanceledOnTouchOutside(false);


        }else if(status.equals("1")) {
            final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ac);
            final String msg=Jsonparser.getonestring(s,"msg");


            dialogBuilder
                    .withTitle("Server Maintainance")
                    //.withTitle(null)  no title.set
                    .withTitleColor("#FFFFFF")                                  //def
                    .withDividerColor("#11000000")                              //def
                    .withMessage(msg)                     //.withMessage(null)  no Msg
                    .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                    .withDialogColor("#021626")                               //def  | withDialogColor(int resid)
                    // .withIcon(getResources().getDrawable(R.drawable.icon))
                    .withDuration(700)                                          //def
                    .withEffect(Effectstype.Shake)                                         //def Effectstype.Slidetop
                    .withButton1Text("Okay")                                      //def gone
                    .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                    .setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                           ac.finish();
                            dialogBuilder.dismiss();
                        }
                    }).show();

            dialogBuilder.setCancelable(false);
            dialogBuilder.setCanceledOnTouchOutside(false);
        }else{

            final String userid=Jsonparser.getonestring(s,"userid");
            final String ticket=Jsonparser.getonestring(s,"ticket");
            final String userstatic=Jsonparser.getonestring(s,"remain");

            load.setText("Game Loading ..");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (++counter < 101) {
                        pb.setProgress(counter);
                        handler.postDelayed(this, 20L);
                        return;
                    } else {
                        Intent it = new Intent(ac, Main.class);
                        it.putExtra("a",userid);
                        it.putExtra("b",ticket);
                        it.putExtra("c",userstatic);
                        ac.startActivity(it);
                        ac.finish();
                        counter = 0;
                    }

                    handler.removeCallbacks(this);
                }
            }, 20L);
        }



    }

    public static void Feedback_Error() {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ac);


        dialogBuilder
                .withTitle("Connection Problem")
                //.withTitle(null)  no title.set
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("Network Fail. Please Check Your Network")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#021626")                               //def  | withDialogColor(int resid)
                // .withIcon(getResources().getDrawable(R.drawable.icon))
                .withDuration(700)                                          //def
                .withEffect(Effectstype.Shake)                                         //def Effectstype.Slidetop
                .withButton1Text("Retry")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Myclient.getuseridanduserticket();
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                        ac.finish();
                    }
                })
                .show();

        dialogBuilder.setCancelable(false);
        dialogBuilder.setCanceledOnTouchOutside(false);

    }
}
