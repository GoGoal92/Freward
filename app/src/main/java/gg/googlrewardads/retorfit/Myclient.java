package gg.googlrewardads.retorfit;


import gg.googlrewardads.Constant;
import gg.googlrewardads.Main;
import gg.googlrewardads.Splash;
import gg.googlrewardads.phoneid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Myclient {

    public static void getuseridanduserticket() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getuseridanduserticket(Constant.apikey, phoneid.getid());
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Splash.Feedback(response.body().toString());
                } catch (Exception e) {
                    Splash.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Splash.Feedback_Error();
                t.printStackTrace();
            }
        });

    }


    public static void sent(String userid) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.sent(Constant.apikey, userid);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Main.Feedback(response.body().toString());
                } catch (Exception e) {
                    Main.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Main.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void faosdonoaphin(String userid) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.faosdonoaphin(Constant.apikey, userid);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Main.Feedbackfaosdonoaphin(response.body().toString());
                } catch (Exception e) {
                    Main.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Main.Feedback_Error();
                t.printStackTrace();
            }
        });

    }


    public static void getuseridanduserticket_main() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getuseridanduserticket(Constant.apikey, phoneid.getid());
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Main.Feedback_resume(response.body().toString());
                } catch (Exception e) {
                    Main.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Main.Feedback_Error();
                t.printStackTrace();
            }
        });

    }
}
