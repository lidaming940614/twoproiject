package feicui.newsapp02.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import feicui.newsapp02.R;
import feicui.newsapp02.base.BaseActivity;
import feicui.newsapp02.tools.CloseActivity;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class AboutusActicity extends BaseActivity {
    TextView versions,author;
    View view,view_rl;
    @Override
    public void setlayout() {
        setContentView(R.layout.aboutour);
    }
    @Override
    public void settoolbar() {

    }
    @Override
    public void getview() {
        view=findViewById(R.id.aboutus_top);
        view_rl=findViewById(R.id.top_rl);
        CloseActivity.settop(view,this);
        versions= (TextView) findViewById(R.id.versions);
        author= (TextView) findViewById(R.id.author);
    }
    @Override
    public void setview() {
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            versions.setText("版本:"+version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {

    }


}
