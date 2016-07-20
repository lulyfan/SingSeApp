package com.yxb.android.shengseshequ.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yxb.android.shengseshequ.R;
import com.yxb.android.shengseshequ.ui.LoginActivity;
import com.yxb.android.shengseshequ.utils.SelectPicPopupWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/15.
 */
@SuppressLint("SdCardPath")
public class MineFragment extends Fragment {
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_idNum)
    TextView userIdNum;
    @Bind(R.id.user_icon_change)
    ImageView userIconChange;
    @Bind(R.id.user_icon)
    LinearLayout userIcon;
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.chat_with)
    TextView chatWith;
    @Bind(R.id.friends)
    TextView friends;
    //    @Bind(R.id.mine_toolbar)
//    Toolbar toolbar;
    @Bind(R.id.my_remind)
    LinearLayout myRemind;
    @Bind(R.id.read_history)
    LinearLayout readHistory;
    @Bind(R.id.my_note)
    LinearLayout myNote;
    @Bind(R.id.my_reply)
    LinearLayout myReply;
    @Bind(R.id.setting)
    LinearLayout setting;
    @Bind(R.id.final_icon)
    ImageView finalIcon;
    @Bind(R.id.mine_fragment)
    LinearLayout linearLayout;

    private Bitmap head;//头像Bitmap
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "kaola.jpg";
    String cameraImgPath;
    //    Toolbar idToolbar;
    Context context;
    SelectPicPopupWindow menuWindow;
    private File path;

    public MineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.user_name, R.id.user_idNum, R.id.user_icon_change, R.id.user_icon, R.id.add,
            R.id.chat_with, R.id.friends, R.id.my_remind, R.id.read_history, R.id.my_note, R.id.my_reply, R.id.setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_name:

                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.user_idNum:
                break;
            case R.id.user_icon_change:

                break;
            case R.id.user_icon:
                //实例化SelectPicPopupWindow

                menuWindow = new SelectPicPopupWindow(getActivity(), itemsOnClick);
                System.out.println("调用窗口事件发生=================================");
                //显示窗口
                menuWindow.showAtLocation(getActivity().findViewById(R.id.mine_fragment),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.add:
                Intent addIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(addIntent);
                break;
            case R.id.chat_with:
                break;
            case R.id.friends:
                break;
            case R.id.my_remind:

                break;
            case R.id.read_history:
                break;
            case R.id.my_note:
                break;
            case R.id.my_reply:
                break;
            case R.id.setting:
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            System.out.println("点击事件发生=================================");
            switch (v.getId()) {
                case R.id.btn_take_photo://调用相机拍照
//                    Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                    // 判断存储卡是否可用，存储照片文件
//                    if (hasSdcard()) {
//                        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
//                                .fromFile(new File(Environment
//                                        .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
//                    }
//
//                    startActivityForResult(intentFromCapture, 1);
                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File
//                                        (Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME)));
                    startActivityForResult(takeIntent, 1);//采用ForResult打开
                    break;
                case R.id.btn_pick_photo://从相册里面取照片
                    Intent intentFromGallery = new Intent();
                    // 设置文件类型
                    intentFromGallery.setType("image/*");
                    intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intentFromGallery, 2);
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
//                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "Image/*");
//                    startActivityForResult(pickIntent, 2);
                    break;
                case R.id.btn_cancel:
                    menuWindow.dismiss();
                    break;
                default:
                    break;
            }


        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
//                if (hasSdcard()) {
//                    File tempFile = new File(
//                            Environment.getExternalStorageDirectory(),
//                            IMAGE_FILE_NAME);
//                    cropPhoto(Uri.fromFile(tempFile));
//                } else {
//                    Toast.makeText(getActivity().getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
//                            .show();
//                }

                if (resultCode == getActivity().RESULT_OK) {//相机调用正常返回
                    String sdStatus = Environment.getExternalStorageState(); //检查sdcard是否正常
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用

                        return;
                    }
                    //保存自己的图片的文件格式命名
                    String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";


                    //从返回data对象找出图片数据
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

                    //将bitmap写入按指定文件格式保存到sdcard中
                    FileOutputStream b = null;
                    String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
                    File file = new File(sdcard + "/myImage/");
                    file.mkdirs();// 创建文件夹
                    String fileName = sdcard + "/myImage/" + name;
                    cameraImgPath = fileName;
                    try {
                        b = new FileOutputStream(fileName);
                        /* bitmap 变成 jpg格式文件保存 */
                        if (bitmap != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (b != null) {
                                b.flush();
                            }
                            if (b != null) {
                                b.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    finalIcon.setImageBitmap(bitmap);// 将图片显示在ImageView里

                }


//               try {
//                   cropPhoto(data.getData());//裁剪图片
//               }catch (NullPointerException e){
//                   e.printStackTrace();// 用户点击取消操作
//               }

                break;
            case 2:
                cropPhoto(data.getData());

//                    File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
//                    cropPhoto(Uri.fromFile(temp));//裁剪图片

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        finalIcon.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        String name2 = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        String sdcard2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileOutputStream b = null;
        File file = new File(sdcard2 + "/myImage/");
        file.mkdirs();// 创建文件夹
        String fileName2 = sdcard2 + "/myImage/" + name2;//图片名字
        try {
            b = new FileOutputStream(fileName2);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
