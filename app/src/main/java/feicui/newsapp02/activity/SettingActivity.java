package feicui.newsapp02.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import feicui.newsapp02.R;
import feicui.newsapp02.base.BaseActivity;
import feicui.newsapp02.tools.CloseActivity;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class SettingActivity extends BaseActivity {
    View view,view_rl;
    Button purple,gold,green,setting_btn4_start, setting_btn5_remuse, setting_btn6_end;
    Intent intent;
    @Override
    public void setlayout() {
        setContentView(R.layout.set);
    }
    @Override
    public void settoolbar() {

    }
    @Override
    public void getview() {
        view_rl=findViewById(R.id.top_rl);
        view=findViewById(R.id.set_top);
        purple= (Button) findViewById(R.id.set_purple);
        gold= (Button) findViewById(R.id.set_gold);
        green= (Button) findViewById(R.id.set_green);
        setting_btn4_start= (Button) findViewById(R.id.setting_btn4_start);
        setting_btn5_remuse= (Button) findViewById(R.id.setting_btn5_remuse);
        setting_btn6_end= (Button) findViewById(R.id.setting_btn6_end);
    }

    @Override
    public void setview() {
        intent=new Intent(this,MyService.class);
        CloseActivity.settop(view,this);
        purple.setOnClickListener(this);
        gold.setOnClickListener(this);
        green.setOnClickListener(this);
        setting_btn4_start.setOnClickListener(this);
        setting_btn5_remuse.setOnClickListener(this);
        setting_btn6_end.setOnClickListener(this);
        intent=new Intent(this,MyService.class);}
    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.set_purple:
                Intent ip = new Intent();
                ip.setAction("corlor01");
                Toast.makeText(this, "TOOLBAR主题颜色以变黄", Toast.LENGTH_SHORT).show();
                sendBroadcast(ip);
                break;
            case R.id.set_gold:
                Intent ig = new Intent();
                ig.setAction("corlor02");
                Toast.makeText(this, "TOOLBAR主题颜色以变红", Toast.LENGTH_SHORT);
                sendBroadcast(ig);
                break;
            case R.id.set_green:
                Intent igr = new Intent();
                igr.setAction("corlor03");
                Toast.makeText(this, "TOOLBAR主题颜色以变绿", Toast.LENGTH_SHORT);
                sendBroadcast(igr);
                break;
            case R.id.setting_btn4_start:

                bundle.putInt("number", 1);
                break;
            case R.id.setting_btn5_remuse:

                bundle.putInt("number", 2);
                break;
            case R.id.setting_btn6_end:
                bundle.putInt("number", 3);
                break;
        }
        intent.putExtras(bundle);
        startService(intent);
    }

}

