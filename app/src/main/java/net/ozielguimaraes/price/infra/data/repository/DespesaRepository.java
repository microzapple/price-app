package net.ozielguimaraes.price.infra.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.ozielguimaraes.price.R;
import net.ozielguimaraes.price.domain.entities.Despesa;
import net.ozielguimaraes.price.helpers.DespesaArrayAdapter;

import java.util.Date;

/**
 * Created by Oziel on 01/09/2016.
 */
public class DespesaRepository {
    private SQLiteDatabase conn;

    public DespesaRepository(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    private ContentValues setContentValues(Despesa despesa)
    {
        ContentValues values = new ContentValues();

        values.put(Despesa.Descricao    , despesa.getDescricao());
        values.put(Despesa.Valor    , despesa.getValor());
        values.put(Despesa.Vencimento, despesa.getVencimento().getTime());

        return values;
    }

    public void insert(Despesa contato)
    {
        ContentValues values = setContentValues(contato);
        conn.insertOrThrow(Despesa.Tabela, null, values);
    }
    public DespesaArrayAdapter getContacts(Context context)
    {

        DespesaArrayAdapter adpDespesas = new DespesaArrayAdapter(context, R.layout.content_list_despesas );

        Cursor cursor  =  conn.query(Despesa.Tabela, null, null, null, null, null, null);

        if (cursor.getCount() > 0 )
        {
            cursor.moveToFirst();
            do {
                Despesa despesa = new Despesa();
                despesa.setId( cursor.getLong( cursor.getColumnIndex(Despesa.Id)));
                despesa.setDescricao( cursor.getString( cursor.getColumnIndex(Despesa.Descricao)));
                despesa.setValor(new Double(cursor.getString( cursor.getColumnIndex(Despesa.Valor ))));
                despesa.setVencimento( new Date(cursor.getLong( cursor.getColumnIndex(Despesa.Vencimento))));

                adpDespesas.add(despesa);

            }while (cursor.moveToNext());

        }
        return adpDespesas;
    }

}
