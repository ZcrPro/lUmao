package com.zcrpro.lumao.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.zcrpro.lumao.R;

public class LoginActivity extends Activity {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.ok)
    Button ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }
}
