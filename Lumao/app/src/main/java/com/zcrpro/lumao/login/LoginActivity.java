package com.zcrpro.lumao.login;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.zcrpro.lumao.MainActivity;
import com.zcrpro.lumao.R;
import com.zcrpro.lumao.login.adapter.SplashAdapter;
import com.zcrpro.lumao.login.scroll.ScollLinearLayoutManager;

public class LoginActivity extends Activity {


    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.app_name)
    TextView appName;

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
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF");
        appName.setTypeface(typeface);
    }
}
