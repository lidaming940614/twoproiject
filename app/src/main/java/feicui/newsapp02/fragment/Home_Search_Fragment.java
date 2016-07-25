package feicui.newsapp02.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import feicui.newsapp02.R;
import feicui.newsapp02.activity.TwoActivity;
import feicui.newsapp02.base.BaseFragment;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class Home_Search_Fragment extends BaseFragment implements View.OnClickListener{
    View view;
    Button select_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_search,null);
        getview();
        setview();
        return view;
    }
    @Override
    public void getview() {
        select_btn = (Button) view.findViewById(R.id.select_btn);
    }
    @Override
    public void setview() {
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), TwoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
