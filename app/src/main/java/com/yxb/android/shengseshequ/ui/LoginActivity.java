package com.yxb.android.shengseshequ.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.bean.UserBean;
import com.yxb.android.shengseshequ.utils.ZdingyiImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_out)
    ImageView loginOut;
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.edit_pwd)
    EditText editPwd;
    @Bind(R.id.button_login)
    Button buttonLogin;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.forget_pwd)
    TextView forgetPwd;
    @Bind(R.id.login_way_icon)
    ZdingyiImageView loginWayIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.login_out, R.id.button_login, R.id.register, R.id.forget_pwd, R.id.login_way_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_out:
                finish();
                break;
            case R.id.button_login:
                break;
            case R.id.register:

                String username= editName.getText().toString();
                String password = editPwd.getText().toString();
                UserBean user = new UserBean();
                user.setNickName(username);
                user.setPassword(password);
                user.signUp(new SaveListener<UserBean>() {

                    @Override
                    public void done(UserBean userBean, BmobException e) {
//                                                   if (e==null){
//                                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
//                            }else {
//
//                        }
                    }
                });
                break;
            case R.id.forget_pwd:
                break;
            case R.id.login_way_icon:
                break;
        }
    }
}
