package com.gaos.book.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.gaos.book.R;

public class DialogUtils {

    /**
     * 单个按钮弹窗(首页消息)
     *
     * @param activity
     * @param yesTask
     */
    public static void singleBtnDialogForMsg(final Activity activity, String msg, final Runnable noTask, final Runnable yesTask) {
        final AlertDialog dialog = new AlertDialog.Builder(activity, R.style.Dialog).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        window.setContentView(R.layout.singlebtn_dialog_layout_msg);
        TextView content = (TextView) window.findViewById(R.id.content);
        ImageButton ibClose = (ImageButton) window.findViewById(R.id.ib_close);
        content.setText(msg);
        //设置图片圆角角度
//        RoundedCorners roundedCorners = new RoundedCorners(10);
//        RequestOptions options = RequestOptions
//                .bitmapTransform(roundedCorners)
//                .skipMemoryCache(true)
//                .placeholder(R.mipmap.default_icon)
//                .dontAnimate();
//        ImageUtil.getImage(activity, imgUrl, image, options, new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                image.setImageBitmap(resource);
//            }
//
//            @Override
//            public void onLoadFailed(@Nullable Drawable errorDrawable) {
////                image.setImageDrawable(errorDrawable);
//                RequestOptions options2 = RequestOptions.centerCropTransform()
//                        .transform(roundedCorners)
//                        .override(640, 512)
//                        .dontAnimate();
//                ImageUtil.getImage(activity, R.mipmap.default_icon, options2, image);
//            }
//        });
//
//        image.setOnClickListener(v -> {
//            if (yesTask != null) {
//                yesTask.run();
//            }
//            dialog.dismiss();
//        });

        ibClose.setOnClickListener(v -> {
            if (noTask != null) {
                noTask.run();
            }
            dialog.dismiss();
        });

    }
}
