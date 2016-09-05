package net.ozielguimaraes.price.infra.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oziel on 01/09/2016.
 */
public class DbConnection  extends SQLiteOpenHelper {

    public DbConnection(Context context) { super(context, "Price", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbScript.createTableDespesa());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}