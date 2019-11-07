package com.spacetech.moovme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class DialogException {

    public static void CreateDialog(String title,String msg , final Context ctx){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ctx,AlertDialog.THEME_HOLO_LIGHT);
        dialogo1.setTitle(title);
        dialogo1.setMessage(msg);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Toast t=Toast.makeText(ctx,"xd", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        dialogo1.show();
    }

    public static void aceptar(Context ctx) {

    }


}
