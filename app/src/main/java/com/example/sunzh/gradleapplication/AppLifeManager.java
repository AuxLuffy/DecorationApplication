package com.example.sunzh.gradleapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by sunzh on 2017/3/17.
 */

public class AppLifeManager {
    private static final String TAG = "AppLifeManager";
    private static Application app;

    public static void init(Application application) {
        app = application;
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Toast.makeText(app, "应用进入前台", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Toast.makeText(app, "应用进入后台", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

}
