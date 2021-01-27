package com.gaos.book.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.gaos.book.R;

import java.io.ByteArrayOutputStream;

/**
 * 图片加载
 * Created by lcg on 2017/8/3.
 */

public class ImageUtil {

    /**
     * 默认图片加载inJustDecodeBounds
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void getImage(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
//                .error(R.mipmap.default_icon)
                .dontAnimate();
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void getImage(Context context, String url, ImageView imageView, int errImg) {
        RequestOptions requestOptions = new RequestOptions()
                .error(errImg)
                .dontAnimate();
        Glide.with(context).load(url).apply(requestOptions).into(imageView);

    }

    public static void getImage(Context context, int resourceId, ImageView imageView) {
        Glide.with(context).asBitmap().load(resourceId).into(imageView);
    }

    public static void getImage(Context context, int resourceId, RequestOptions requestOptions, ImageView imageView) {
        Glide.with(context).asBitmap().load(resourceId).apply(requestOptions).into(imageView);
    }

    public static void getImage(Context context, String url, RequestOptions requestOptions, ImageView imageView) {
        Glide.with(context).asBitmap().load(url).apply(requestOptions).into(imageView);
    }

    public static void getImage(Context context, String url, ImageView imageView, RequestOptions requestOptions, Target target) {
        Glide.with(context).asBitmap().load(url).apply(requestOptions).into(target);
    }

    /**
     * 加载圆角图片
     * @param context
     * @param url
     * @param imageView
     * @param cornerRadius
     */
    public static void getRoundCornerImage(Context context, String url, ImageView imageView, int cornerRadius) {
        RequestOptions requestOptions = new RequestOptions()
//                .error(R.mipmap.default_icon)
                .dontAnimate();
        Glide.with(context).asBitmap().load(url).apply(requestOptions).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                circularBitmapDrawable.setCornerRadius(cornerRadius);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void getLoadingImage(Context context, int resId, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).asGif().load(resId).apply(requestOptions).into(imageView);
    }

    public static void getLoadingImage(Context context, int resId, RequestOptions requestOptions, ImageView imageView) {
        if (requestOptions != null)
            Glide.with(context).asGif().load(resId).apply(requestOptions).into(imageView);
        else getLoadingImage(context, resId, imageView);
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath, int newWidth, int newHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, newWidth, newHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        Bitmap newBitmap = compressImage(bitmap, 500);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }


    /**
     * 质量压缩
     *
     * @param image
     * @param maxSize
     */
    public static Bitmap compressImage(Bitmap image, int maxSize) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 80;
        // Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while (os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
        }

        Bitmap bitmap = null;
        byte[] b = os.toByteArray();
        if (b.length != 0) {
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return bitmap;
    }
}
