package feicui.newsapp02.tools;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import feicui.newsapp02.R;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class CloseActivity {
    public static void settop(View view, final Activity a){
        ImageView home_right= (ImageView) view.findViewById(R.id.add_nws_back);
        home_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });
    }
}
