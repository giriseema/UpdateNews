package com.updatenews.www.updatenews.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.updatenews.www.updatenews.R;


public class CommonProgressDialog {

    private static ProgressDialog prodio = null;
    private static Dialog dialog;

    public static void showProgDialog(Context context) {
        if (prodio == null) {
            prodio = new ProgressDialog(context);
            prodio.setCanceledOnTouchOutside(false);
            prodio.setCancelable(false);
            prodio.setMessage(context.getResources().getString(R.string.loading));
            prodio.show();

        }
    }

    public static void showLoader(Context context) {
        if (dialog == null) {
            dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            dialog.setContentView(R.layout.progress_dialog);
            ImageView img = dialog.findViewById(R.id.loader_img);
            Glide.with(context)
                    .load(R.raw.load)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(img);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    public static void hideLoader() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public static void hideProgDialog() {
        if (prodio != null && prodio.isShowing()) {
            prodio.dismiss();
            prodio = null;
        }
    }
}
