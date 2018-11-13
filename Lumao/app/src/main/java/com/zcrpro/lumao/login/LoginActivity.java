package com.zcrpro.lumao.login;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import com.zcrpro.bmob.bean.YjUser;
import com.zcrpro.lumao.R;
import com.zcrpro.lumao.login.adapter.SplashAdapter;
import com.zcrpro.lumao.login.scroll.ScollLinearLayoutManager;

public class LoginActivity extends Activity {


    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.iv_wechat)
    LinearLayout ivWechat;
    @Bind(R.id.ll_bootom)
    LinearLayout llBootom;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.ed_mobile)
    EditText edMobile;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.ed_sms_code)
    EditText edSmsCode;
    @Bind(R.id.btn_sms)
    Button btnSms;

    private TranslateAnimation mHiddenDownAction;
    private TranslateAnimation mShowAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setAdapter(new SplashAdapter(LoginActivity.this));
        mRecyclerView.setLayoutManager(new ScollLinearLayoutManager(LoginActivity.this));

        //smoothScrollToPosition滚动到某个位置（有滚动效果）
        mRecyclerView.smoothScrollToPosition(Integer.MAX_VALUE / 2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FZLanTingHeiS-L-GB-Regular.TTF");
        appName.setTypeface(typeface);

        mHiddenDownAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
        mHiddenDownAction.setDuration(700);

        mShowAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(700);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llBootom.startAnimation(mHiddenDownAction);
                llBootom.setVisibility(View.GONE);
                rlLogin.startAnimation(mShowAction);
                rlLogin.setVisibility(View.VISIBLE);
                btnNext.setText("注册");
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llBootom.startAnimation(mHiddenDownAction);
                llBootom.setVisibility(View.GONE);
                rlLogin.startAnimation(mShowAction);
                rlLogin.setVisibility(View.VISIBLE);
                btnNext.setText("登录");
            }
        });

        edMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    btnSms.setEnabled(true);
                } else {
                    btnSms.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNext.getText().toString().trim().equals("注册")){
                    userRegister(edMobile.getText().toString().trim(),edSmsCode.getText().toString().trim());
                }else {
                    userLogin(edMobile.getText().toString().trim(),edSmsCode.getText().toString().trim());
                }
            }
        });

    }

    private void userRegister(String username,String password){
        BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<YjUser>() {
            @Override
            public void done(YjUser yjUser, BmobException e) {
                if (e==null){
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userLogin(String username,String password){
        BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<YjUser>() {
            @Override
            public void done(YjUser yjUser, BmobException e) {
                if (e==null){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
