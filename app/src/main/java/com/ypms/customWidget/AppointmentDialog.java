package com.ypms.customWidget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ypms.R;

/**
 * Created by Hero on 2018/4/10.
 */

public class AppointmentDialog extends Dialog {
    public AppointmentDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_appointment_dialog);
        initDialogAttrs();
    }

    private void initDialogAttrs() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.windowAnimations = R.style.dialog_anim_bottom;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(true);
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
    }
}
