package feicui.newsapp02.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import feicui.newsapp02.R;
import feicui.newsapp02.base.BaseActivity;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class LoginActivity extends BaseActivity {
    private TextInputLayout username_layout, password_layout;
    private EditText edit_password;
    private ImageView img_qq;
    private Button btn_login;
    private Platform qq_plat;//授权者资料
    private CoordinatorLayout container;
    private final int SUCCESS = 1001;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SUCCESS) {
                PlatformDb platDB = qq_plat.getDb();//获取数平台数据DB
                //通过DB获取各种数据
//            platDB.getToken();
//            platDB.getUserGender();
//            platDB.getUserIcon();
//            platDB.getUserId();
//            platDB.getUserName();
//            Toast.makeText(LoginActivity.this, platDB.getUserIcon()+platDB.getUserIcon()+" ", 1).show();
                Intent intent = getIntent();
                intent.putExtra("username", platDB.getUserName());
                intent.putExtra("usericon", platDB.getUserIcon());
                setResult(3, intent);
                finish();
            }
        }
    };
    public LoginActivity() {
    }

    @Override
    public void setlayout() {
        setContentView(R.layout.activity_login);
    }
    private void authorize(Platform plat) {
        if (plat == null) {
            popupOthers();
            return;
        }
//判断指定平台是否已经完成授权
        if (plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (userId != null) {
                handler.sendEmptyMessage(SUCCESS);
                return;
            }
        }
        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(true);
        //获取用户资料
        plat.showUser(null);
//        若本地没有授权过就请求用户数据

//        plat.SSOSetting(false);//此处设置为false，则在优先采用客户端授权的方法，设置true会采用网页方式
//        plat.showUser(null);//获得用户数据
    }

    private void popupOthers() {
        qq_plat = ShareSDK.getPlatform(QQ.NAME);
        qq_plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
                handler.sendEmptyMessage(SUCCESS);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Snackbar.make(container, "授权失败！！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        qq_plat.authorize();
    }
    @Override
    public void settoolbar() {

    }

    @Override
    public void getview() {
        container = (CoordinatorLayout) findViewById(R.id.container);
        username_layout = (TextInputLayout) findViewById(R.id.layout_username);
        password_layout = (TextInputLayout) findViewById(R.id.layout_password);
        username_layout.setHint("用户名");
        password_layout.setHint("密码");

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        img_qq = (ImageView) findViewById(R.id.img_qq);
        img_qq.setOnClickListener(this);

        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.edit_password || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void setview() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_qq:
                authorize(qq_plat);
                break;
            case R.id.btn_login:
                attemptLogin();
                break;
        }
    }
    boolean cancel = false;
    View focusView = null;

    private void attemptLogin() {
        // Reset errors.
        edit_password.setError(null);
        String password = edit_password.getText().toString();
        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
        if (password.length() < 6 || !password.equals("123456")) {
//            edit_password.setError(getString(R.string.error_invalid_password));
            edit_password.setError("账号名/密码错误");
            focusView = edit_password;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            setResult(2);
            finish();
        }

    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
