package com.ntpd.inventorymanagement_qlk.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.AGConnectUser;
import com.ntpd.inventorymanagement_qlk.Login.LoginActivity;
import com.ntpd.inventorymanagement_qlk.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    TextView txtNameapp;
    ImageView logostart;
    Animation topAnim, bottomAnim;

    private TimerTask timerTask;

    private static int SPLASH_TIME_OUT = 3000;
    private static final String TAG = "StartAppActivity";
    public String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getView();

        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        logostart.setAnimation(topAnim);
        txtNameapp.setAnimation(bottomAnim);

        AGConnectUser user = AGConnectAuth.getInstance().getCurrentUser();

        if (user != null)
            userEmail = user.getEmail();
        Timer RunSplash = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (userEmail != null) {
                            Intent intent = new Intent(StartActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(StartActivity.this, "Xin chào !",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(StartActivity.this, "Đăng nhập tài khoản của bạn để tiếp tục !", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        };
        RunSplash.schedule(timerTask, SPLASH_TIME_OUT);
    }

    private void getView() {
        txtNameapp=findViewById(R.id.txtNameapp);
        logostart=findViewById(R.id.logo_start);
    }
}