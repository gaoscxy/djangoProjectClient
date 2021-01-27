package com.gaos.book.api;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import com.gaos.book.R;
import com.gaos.book.common.MyApplication;
import com.gaos.book.utils.ImageUtil;

import io.reactivex.disposables.Disposable;

public abstract class ProgressObserver<T> extends BaseObserver<T> {
    private View mLoadingView;
    private ViewGroup contentView;
    public ProgressObserver(){
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!d.isDisposed()) {
            Activity activity = MyApplication.getInstance().getCurActivity();
            mLoadingView = LayoutInflater.from(activity).inflate(R.layout.view_loading_dialog, null);
            ImageView mLoadingImg = mLoadingView.findViewById(R.id.loading_dialog_img);
            contentView = MyApplication.getInstance().getCurActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.addView(mLoadingView);
            ImageUtil.getLoadingImage(activity, R.drawable.loading, mLoadingImg);
        }
    }

    @Override
    public void onComplete() {
        if (contentView != null) {
            contentView.removeView(mLoadingView);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        if (contentView != null) {
            contentView.removeView(mLoadingView);
        }
    }
}
