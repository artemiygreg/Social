package ru.social.View.AlertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import ru.social.R;


public class MyAlertDialogImpl implements MyAlertDialog {
    private Context context;
    private AlertDialog alertDialogProgress;

    public MyAlertDialogImpl(Context context){
        this.context = context;
    }

    @Override
    public void showAlertInfo(String textInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textInfo);
        builder.setNegativeButton(context.getResources().getString(R.string.close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void showProgressDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.layout_alert_progress, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(alertDialogProgress == null || !alertDialogProgress.isShowing()) {
            alertDialogProgress = builder.create();
            alertDialogProgress.show();
            alertDialogProgress.setContentView(v);
        }
    }

    @Override
    public void dismissProgressDialog() {
        if(alertDialogProgress != null) {
            if (alertDialogProgress.isShowing()) {
                alertDialogProgress.dismiss();
            }
        }
    }


    @Override
    public boolean progressIsShowing() {
        return alertDialogProgress != null && alertDialogProgress.isShowing();
    }
}
