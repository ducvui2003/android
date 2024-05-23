package com.example.truyenapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.truyenapp.view.activity.Signin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
public class DialogHelper {
    Context context;
    DialogEvent dialogEvent;
    private static final Class SIGN_IN_ACTIVITY = Signin.class;

    public DialogHelper(Context context) {
        this.context = context;
    }

    public DialogHelper(Context context, DialogEvent dialogEvent) {
        this.context = context;
        this.dialogEvent = dialogEvent;
    }

    public AlertDialog.Builder showDialogLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Nhắc nhở").setMessage("Vui lòng đăng nhập để sử dụng chức năng này.");
        builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogEvent != null)
                    dialogEvent.onPositiveClick();
                Intent intent = new Intent(context, SIGN_IN_ACTIVITY);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogEvent != null)
                    dialogEvent.onNegativeClick();
                dialogInterface.cancel();
            }
        });
        builder.setOnCancelListener(dialogInterface -> {
            if (dialogEvent != null)
                dialogEvent.onCancel();
            dialogInterface.cancel();
        });
        return builder;
    }

    public AlertDialog.Builder showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Điểm danh").setMessage(message);

        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.cancel();
            }
        });
        return builder;
    }
}
