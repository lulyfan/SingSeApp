package com.yxb.android.shengseshequ.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.fragment.BanKuaiFragment;
import com.yxb.android.shengseshequ.fragment.MineFragment;
import com.yxb.android.shengseshequ.fragment.ShouYeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.radiobutton_shouye)
    RadioButton radiobuttonShouye;
    @Bind(R.id.radiobutton_bankuai)
    RadioButton radiobuttonBankuai;
    @Bind(R.id.radiobutton_mine)
    RadioButton radiobuttonMine;
    @Bind(R.id.radioGroupbutton)
    RadioGroup radioGroupbutton;
//    @Bind(R.id.id_toolbar)
//    Toolbar idToolbar;

    private List<Fragment> fragments ;
    private FragmentManager manger;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bmob.initialize(this,"05a23ce82bce07788272ab4a17d7b7b8");//应用名称：shengse


//        setSupportActionBar(idToolbar);//绑定toolbar
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
        fragments = new ArrayList<>();
        fragments.add(new ShouYeFragment());
        fragments.add(new BanKuaiFragment());
        fragments.add(new MineFragment());
        manger = getSupportFragmentManager();
        initEvent();
        transaction = manger.beginTransaction();
        transaction.replace(R.id.Fragment_content, fragments.get(0));//默认显示首页
        transaction.commit();
    }

    private void initEvent() {
        radioGroupbutton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction;
                switch (checkedId){
                    case R.id.radiobutton_shouye:
                        transaction = manger.beginTransaction();
                        transaction.replace(R.id.Fragment_content,fragments.get(0));
                        transaction.commit();
                        break;
                    case R.id.radiobutton_bankuai:
                        transaction = manger.beginTransaction();
                        transaction.replace(R.id.Fragment_content,fragments.get(1));
                        transaction.commit();
                        break;
                    case R.id.radiobutton_mine:
                        transaction = manger.beginTransaction();
                        transaction.replace(R.id.Fragment_content,fragments.get(2));
                        transaction.commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
                    .setTitle("退出")
                    .setMessage("确定退出吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
