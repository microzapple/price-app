package net.ozielguimaraes.price.helpers;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Oziel on 01/09/2016.
 */
public class Notification {

    public static void ShowInfo(Context ctx, String title, String msg)
    {
        Show(ctx, title, msg, android.R.drawable.ic_dialog_info);
    }

    public static void ShowAlert(Context ctx, String title, String msg)
    {
        Show(ctx, title, msg, android.R.drawable.ic_dialog_alert);
    }

    public static void Show(Context ctx, String title, String msg, int iconId)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
        dlg.setIcon(iconId);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }
}
