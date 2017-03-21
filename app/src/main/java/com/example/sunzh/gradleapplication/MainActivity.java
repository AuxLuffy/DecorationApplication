package com.example.sunzh.gradleapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/**
 * 测试系统decoration ui及弱引用
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 单个数据大小为1
     */
    private static final int _1M = 1024 * 1024;
    /**
     * 隐藏系统ui(decoration)消息标志
     */
    private static final int HIDE_SYSTEM_UI = 1;
    /**
     * 发送隐藏系统ui(decoration)消息的时间
     */
    public static final int DELAY_MILLIS = 3000;
    private TextView test;
    private ReferenceQueue<? super byte[]> referenceQueue;
    private TextView cancel;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HIDE_SYSTEM_UI:
                    translucentStatusBar();
                    break;
            }
        }
    };

    /**
     * 状态栏沉浸
     */
    private void translucentStatusBar() {
        handler.removeMessages(HIDE_SYSTEM_UI);
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE//系统ui会有淡黑色背景
                    ;
            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            // 设置状态栏颜色
            ViewGroup contentLayout = (ViewGroup) findViewById(android.R.id.content);
            DeviceUtils.setupStatusBarView(this, contentLayout, Color.BLUE);

            // 设置Activity layout的fitsSystemWindows
            View contentChild = contentLayout.getChildAt(0);
            contentChild.setFitsSystemWindows(true);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        translucentStatusBar();
        hideActionBar();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                switch (visibility) {
                    case View.SYSTEM_UI_FLAG_HIDE_NAVIGATION:
//                        handler.removeMessages(HIDE_SYSTEM_UI);//最好不要这时候移除消息，在相应实效作用时移除最好
                        break;
                    case View.SYSTEM_UI_FLAG_VISIBLE:
                        handler.sendEmptyMessageDelayed(HIDE_SYSTEM_UI, DELAY_MILLIS);
                        break;
                }
            }
        });
       /* getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);*/
        test = (TextView) findViewById(R.id.test);
        cancel = (TextView) findViewById(R.id.cancel);

        test.setOnClickListener(this);
        cancel.setOnClickListener(this);
        findViewById(R.id.rootview).setBackgroundResource(R.drawable.beauty);
    }

    /**
     * 隐藏标题栏
     */
    private void hideActionBar() {
        getSupportActionBar().hide();
    }

    /**
     * 重写这个方法是因为只有在此方法执行后activity渲染才结束，才能得到本视图内所有组件信息，此后才开始绘制
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("TAG", "onWindowFocusChanged() hasFocus:" + hasFocus);
        if (hasFocus) {//确保是获得焦点时才调用
            translucentStatusBar();
        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
    }

    /**
     * 状态栏是否隐藏
     */
    private boolean enable = false;

    /**
     * 隐藏显示状态栏
     */
    private void hideAct() {
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
        if (!enable) {//显示状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            enable = true;
        } else {//隐藏状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(lp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            enable = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test:
//                test.setText("测试软引用");
//                Toast.makeText(MainActivity.this, "测试软引用", Toast.LENGTH_SHORT).show();
//                testWeekReference();
//                testJava();

//                AppLifeManager.init(getApplication());
//                go2Noti();
//                startService(new Intent(MainActivity.this, HomeService.class));
                boolean b = DeviceUtils.checkDeviceHasNavigationBar(this);
                Toast.makeText(MainActivity.this, "导航栏：" + DeviceUtils.checkNav(this), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
//                hideAct();
                nextActivity();
//                stopService(new Intent(MainActivity.this, HomeService.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        Log.e("TAG", "onResume");
        super.onResume();
    }

    private void nextActivity() {
        startActivity(new Intent(this, SecondActivity.class));
    }

    private void go2Noti(Context context) {
        Intent intent = new Intent(
                android.provider.Settings
                        .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

        context.startActivity(intent);
    }

    private void testJava() {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
//        List<ArrayList<Integer>> arrayLists = Arrays.asList(list);
//        String s = Base64.encodeToString(new byte[]{1, 2, 3}, Base64.DEFAULT);
//        System.out.println(/*arrayLists.toString()*/s);
//        byte[] decode = Base64.decode(s, Base64.DEFAULT);
//        System.out.println(Arrays.toString(decode));

        printCollection1(list);


        printCollection2((Collection) list);


        WeakHashMap<String, String> whm = new WeakHashMap();

    }

    void printCollection1(Collection c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); k++) {
            System.out.println(i.next());
        }
    }

    void printCollection2(Collection<Object> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }


    private void testWeekReference() {
        Object value = new Object();
        HashMap<Object, Object> map = new HashMap<>();
        referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, referenceQueue);
            map.put(weakReference, value);
//            map.put(bytes,value);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                WeakReference<byte[]> k;
                int i = 0;
                try {
                    while ((k = (WeakReference) referenceQueue.remove()) != null) {
                        System.out.println((i++) + "回收了:" + k);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        Toast.makeText(MainActivity.this, "map.size：" + map.size() /*+ "；回收了" + i + "次"*/, Toast.LENGTH_SHORT).show();
    }
}
