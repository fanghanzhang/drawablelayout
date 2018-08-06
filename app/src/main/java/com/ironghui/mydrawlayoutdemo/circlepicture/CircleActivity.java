package com.ironghui.mydrawlayoutdemo.circlepicture;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ironghui.mydrawlayoutdemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CircleActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button selectImageBtn;
    private ImageView imageView;

    private File sdcardTempFile;
    private AlertDialog dialog;
    private int crop = 180;
    private de.hdodenhof.circleimageview.CircleImageView imageView1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        selectImageBtn = (Button) findViewById(R.id.selectImageBtn); //头像框
        imageView = (ImageView) findViewById(R.id.imageView);//选择头像
        imageView1 = findViewById(R.id.circleimageview);
        requestPermission();
        selectImageBtn.setOnClickListener(this);
        sdcardTempFile = new File("/mnt/sdcard/", "abcdefg.jpg");//路径可以自己选择只是参考
        imageView1.setImageResource(R.drawable.bianji);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(CircleActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(CircleActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 权限还没有授予，进行申请
            ActivityCompat.requestPermissions(CircleActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 300); // 申请的 requestCode 为 300
        } else {
            // 权限已经申请，直接拍照
        }
    }

    @Override
    public void onClick(View v) {
        if (v == selectImageBtn) {
            if (dialog == null) {
                dialog = new AlertDialog.Builder(this).setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra("output", Uri.fromFile(sdcardTempFile));
                            intent.putExtra("crop", "true");
                            intent.putExtra("aspectX", 1);// 裁剪框比例
                            intent.putExtra("aspectY", 1);
                            intent.putExtra("outputX", crop);// 输出图片大小
                            intent.putExtra("outputY", crop);
                            startActivityForResult(intent, 101);
                        } else {
                            Intent intent = new Intent("android.intent.action.PICK");
                            intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                            intent.putExtra("output", Uri.fromFile(sdcardTempFile));
                            intent.putExtra("crop", "true");
                            intent.putExtra("aspectX", 1);// 裁剪框比例
                            intent.putExtra("aspectY", 1);
                            intent.putExtra("outputX", crop);// 输出图片大小
                            intent.putExtra("outputY", crop);
                            startActivityForResult(intent, 100);
                        }
                    }
                }).create();
            }
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            Bitmap bmp = BitmapFactory.decodeFile(sdcardTempFile.getAbsolutePath());
            imageView.setImageBitmap(toRoundBitmap(bmp));
        }
    }

    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;

            left = 0;
            top = 0;
            right = width;
            bottom = width;

            height = width;

            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;

            float clip = (width - height) / 2;

            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;

            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);// 设置画笔无锯齿

        canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas

        // 以下有两种方法画圆,drawRounRect和drawCircle
//		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
        canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle

        ByteArrayOutputStream logoStream = new ByteArrayOutputStream();
        boolean res = output.compress(Bitmap.CompressFormat.PNG, 100, logoStream);
        byte[] logoBuf = logoStream.toByteArray();//将图像保存到byte[]中
        Bitmap temp = BitmapFactory.decodeByteArray(logoBuf, 0, logoBuf.length);//将图像从byte[]中读取生成Bitmap 对象 temp
        saveMyBitmap("tttt", temp);//将图像保存到SD卡中
        delFolder("/mnt/sdcard/abcdefg.jpg");
        return temp;
    }

    public void saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("/sdcard/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹
     *
     * @param String      文件夹路径及名称 如c:/fqf
     * @param fileContent String
     * @return boolean
     */
    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹

        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();

        }
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path String 文件夹路径 如 c:/fqf
     */
    public void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
            }
        }
    }

}
