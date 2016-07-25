package feicui.newsapp02.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import feicui.newsapp02.R;
import feicui.newsapp02.adapter.HomerightAdapter;
import feicui.newsapp02.db.Share;
import feicui.newsapp02.tools.HomeManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    HomeManager ham;
    TextView textView, login;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionbardrawertoggle;
    Button one, two, three;
    View view;
    RelativeLayout view_rl;
    List<Share> shareList;
    HomerightAdapter homerightAdapter;
    LinearLayout linearLayout;
    ImageView shareimg;
    OnekeyShare oks;
    MybroadcastReceiver mr;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    toolbar.setBackgroundColor(getResources().getColor(R.color.settingcolor3));
                    Log.i("receiver", "绿色");
                    break;
                case 1:
                    toolbar.setBackgroundColor(getResources().getColor(R.color.settingcolor2));
                    Log.i("receiver", "");
                    break;
                case 2:
                    toolbar.setBackgroundColor(getResources().getColor(R.color.settingcolor1));
                    Log.i("receiver", "黄色");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mr = new MybroadcastReceiver();
        setlayout();
        getview();
        setview();
        String action01 = "corlor01";
        String action02 = "corlor02";
        String action03 = "corlor03";
        myRigisterReciver(action01);
        Log.i("ms", "11111111");
        myRigisterReciver(action02);
        myRigisterReciver(action03);
    }
    public void setlayout() {
        showShare();
        setContentView(R.layout.home_toolbar);
    }
    public void getview() {
        login = (TextView) findViewById(R.id.toolbar_login);
        view_rl = (RelativeLayout) findViewById(R.id.top_rl);
        ham = new HomeManager(this);
        textView = (TextView) findViewById(R.id.home_buttom_one_tv);
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        drawer = (DrawerLayout) findViewById(R.id.mydrawer);
        one = (Button) findViewById(R.id.drawer_star);
        two = (Button) findViewById(R.id.aboveme);
        three = (Button) findViewById(R.id.select);
        shareimg = (ImageView) findViewById(R.id.toobar_share);
        view = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
        linearLayout = (LinearLayout) findViewById(R.id.home_ll);
        shareList = new ArrayList<Share>();
    }
    public void setview() {
        ham.initHomeButtomChange();
        ham.initfragment();
        //设置toolbar和抽屉
        toolbar.setTitle("NewsDay");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        actionbardrawertoggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        actionbardrawertoggle.syncState();//保持同步
        drawer.setDrawerListener(actionbardrawertoggle);//绑定
        login.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        shareimg.setOnClickListener(this);
        homerightAdapter = new HomerightAdapter(this);
    }


    public void onClick(View v) {
        ham.setbuttomchick(v);
        ham.changefragment(v);
        Intent i;
        switch (v.getId()) {
            case R.id.toobar_share:
                oks.show(this);
                break;
            case R.id.drawer_star:
                i = new Intent(this, CollectActivity.class);
                startActivity(i);
                break;
            case R.id.aboveme:
                i = new Intent(this, AboutusActicity.class);
                startActivity(i);
                break;
            case R.id.select:
                i = new Intent(this, SettingActivity.class);
                startActivity(i);
                break;
            case R.id.toolbar_login:
                i = new Intent(this, LoginActivity.class);
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT);
                startActivity(i);
                break;
        }
    }

    /**
     * 分享
     */
    public void showShare() {
        ShareSDK.initSDK(this);
        oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

    }

    /**
     * 传递参数停止服务
     */
    public void stopservice() {
        Intent i = new Intent(this, MyService.class);
        Bundle b = new Bundle();
        b.putInt("number", 4);
        i.putExtras(b);
        startService(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mr);
    }

    public void myRigisterReciver(String... action) {
        Log.i("receiver", "意图过滤器");
        mr = new MybroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        for (int i = 0; i < action.length; i++) {
            intentFilter.addAction(action[i]);
            Log.i("receiver", action[i]);
        }
        registerReceiver(mr, intentFilter);
    }

    class MybroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals("corlor01")) {
                handler.sendEmptyMessage(0);
            }
            if (action.equals("corlor02")) {
                handler.sendEmptyMessage(1);
            }
            if (action.equals("corlor03")) {
                handler.sendEmptyMessage(2);
            }
        }
    }

}
