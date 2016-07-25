package feicui.newsapp02.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setlayout();
        settoolbar();
        getview();
        setview();
    }
    public abstract void setlayout();
    public abstract void settoolbar();
    public abstract void getview();
    public abstract void setview();

    @Override
    public void onClick(View v) {

    }
}
