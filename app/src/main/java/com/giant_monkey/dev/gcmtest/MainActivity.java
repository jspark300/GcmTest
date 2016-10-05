package com.giant_monkey.dev.gcmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.os.AsyncTask;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    GoogleCloudMessaging gcm;
    static String SENDER_ID = "393901362333";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            registerInBackground();
        }
    }


    private boolean checkPlayServices(){
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(resultCode != ConnectionResult.SUCCESS){
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 69).show();
            }else{
                finish();
            }
            return false;
        }
        return true;
    }

    private void registerInBackground(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params){
                String msg;
                try{
                    if(gcm == null){
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    msg = gcm.register(SENDER_ID);
                }catch(IOException e){
                    msg = "";
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg){
                if(!msg.equals("")){
                }
            }
        }.execute(null, null, null);
    }
}
