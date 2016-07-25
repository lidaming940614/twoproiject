package feicui.newsapp02.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.adapter.LeadAdapter;
import feicui.newsapp02.tools.ProjectManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class LeadActivity extends Activity implements View.OnClickListener{
    ViewPager viewPager;
    Button button;
    List<ImageView> views;
    LeadAdapter ad;
    ProjectManager spf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isfirst();
        setlayout();
        initview();
        setview();
    }
    public void setlayout(){
        setContentView(R.layout.lead);
    }
    public void initview(){
        viewPager= (ViewPager) findViewById(R.id.my_viewpager);
        button= (Button) findViewById(R.id.lead_gotoloding);
        views=new ArrayList<ImageView>();
        button.setOnClickListener(this);
    }
    public void setview(){
        spf.savefirst("yes");
        ImageView leadone= (ImageView) LayoutInflater.from(this).inflate(R.layout.lead_item,null);
        leadone.setImageResource(R.mipmap.lead_1);
        ImageView leadtwo= (ImageView) LayoutInflater.from(this).inflate(R.layout.lead_item,null);
        leadtwo.setImageResource(R.mipmap.lead_2);
        ImageView leadthree= (ImageView) LayoutInflater.from(this).inflate(R.layout.lead_item,null);
        leadthree.setImageResource(R.mipmap.lead_3);
        views.add(leadone);
        views.add(leadtwo);
        views.add(leadthree);
        ad=new LeadAdapter(this);
        ad.setList(views);
        viewPager.setAdapter(ad);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    button.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lead_gotoloding:
                gotohome();
                break;
        }
    }
    public void gotohome(){
        Intent i = new Intent(this,LoadingActivity.class);
        startActivity(i);
        finish();
    }
    public void isfirst(){
        spf= ProjectManager.getspfManager(this);
        String islead=spf.getlead();
        if(islead.equals("yes")){
            gotohome();
        }
    }
}
