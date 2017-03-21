package com.example.sunzh.gradleapplication;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by sunzh on 2017/3/17.
 */

public class HomeService extends Service implements OnHomePressListener {

    private HomeWatcherReceiver mHomeKeyReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(HomeService.this, "开启服务", Toast.LENGTH_SHORT).show();
        mHomeKeyReceiver = new HomeWatcherReceiver();
        mHomeKeyReceiver.setmListener(this);
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mHomeKeyReceiver, homeFilter);
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(HomeService.this, "杀死服务", Toast.LENGTH_SHORT).show();
        unregisterReceiver(mHomeKeyReceiver);
    }

    @Override
    public void onHomePressed() {
        Toast.makeText(HomeService.this, "按下home", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHomeLongPressed() {

    }
}
