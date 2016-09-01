package net.ozielguimaraes.price;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import net.ozielguimaraes.price.helpers.Notification;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itAbout) {
            String body = "Oziel Guimaraes is software developer at the government of Rondonia, bachelor in information systems and postgraduate student in mobile development.\n" +
                    "Phone: +55 69 99226-6791\n" +
                    "Email: contato@ozielguimaraes.net";

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("About the author");
            dlg.setMessage(body);
            dlg.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Notification.ShowAlert(MainActivity.this, "Alert.","Couldn't make the call, permission denied.");
                    }
                    else{
                        String phone = "+5569992266791";
                        String full = String.format("tel: ", phone);
                        Uri uri = Uri.parse(full);
                        Intent intent = new Intent(android.content.Intent.ACTION_CALL, uri);
                        startActivity(intent);
                    }
                }
            });
            dlg.setNeutralButton("OK", null);
            dlg.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void listDespesa(View view){
        Intent intent = new Intent(this, DespesaActivity.class);
        startActivity(intent);
    }

    public void addDespesa(View view){
        Intent intent = new Intent(this, AddDespesaActivity.class);
        startActivity(intent);
    }
}
