package feicui.newsapp02.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import feicui.newsapp02.R;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class LoadingActivity extends Activity {
    ImageView loding;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://handler接收数据0 执行操作
                    Intent i=new Intent(LoadingActivity.this,HomeActivity.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.enter_alpha,R.anim.exit_alpha);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setlayout();
        initview();
        setview();
    }
    public void setlayout(){
        setContentView(R.layout.loading);
    }
    public void initview(){
        loding= (ImageView) findViewById(R.id.home_loding);
    }
    public void setview(){
        loding.setImageResource(R.drawable.logo);
        new Thread(new GotoHome()).start();
    }
    class GotoHome implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                handler.sendEmptyMessage(0);//handler传递数据0
            }
        }
    }
}
