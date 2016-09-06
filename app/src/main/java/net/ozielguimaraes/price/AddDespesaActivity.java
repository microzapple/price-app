package net.ozielguimaraes.price;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import net.ozielguimaraes.price.domain.entities.Despesa;
import net.ozielguimaraes.price.helpers.Notification;
import net.ozielguimaraes.price.infra.data.repository.DbConnection;
import net.ozielguimaraes.price.infra.data.repository.DespesaRepository;

import java.text.DateFormat;

/**
 * Created by Oziel on 01/09/2016.
 */
public class AddDespesaActivity extends AppCompatActivity {
    private EditText editDescricao;
    private EditText editValor;
    private EditText editVencimento;

    private DespesaRepository despesaRepository;
    private Despesa despesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_despesa);

        editDescricao = (EditText)findViewById(R.id.editDescricao);
        editValor = (EditText)findViewById(R.id.editValor);
        editVencimento = (EditText)findViewById(R.id.editVencimento);

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey(DespesaActivity.PAR_DESPESA)))
        {
            despesa = (Despesa)bundle.getSerializable(DespesaActivity.PAR_DESPESA);
            preencherDados();
        }
        else despesa = new Despesa();
        try {
            DbConnection dataBase = new DbConnection(this);
            SQLiteDatabase conn = dataBase.getWritableDatabase();

            despesaRepository = new DespesaRepository(conn);

            showCalendarVencimento();
        }catch(SQLException ex)
        {
            Notification.ShowAlert(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }
    }

    public void showCalendarVencimento(){
        editVencimento = (EditText)findViewById(R.id.editVencimento);

        editVencimento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(dialogId);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == dialogId) return new DatePickerDialog(this, dtPickerListener, year_x, month_x, day_x);
        return super.onCreateDialog(id);
    }

    public void buttonGravar (View view){
        salvar();
        finish();
    }

    private void salvar() {
        try {
            despesa.setDescricao(editDescricao.getText().toString());
            despesa.setValor(Double.parseDouble(editValor.getText().toString()));
            despesa.setVencimento(Despesa.toDate(editVencimento.getText().toString()));

            if (despesa.getId() == 0) despesaRepository.insert(despesa);
            else despesaRepository.update(despesa);

            Notification.ShowInfo(this, "Message", "Despesa gravada com sucesso");
        } catch (Exception ex) {
            Notification.ShowAlert(this, "Erro", "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    private void preencherDados()
    {
        editDescricao.setText( despesa.getDescricao() );
        editValor.setText( despesa.getValor().toString() );
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format( despesa.getVencimento() );

        editVencimento.setText( dt );
    }

    int year_x = 1980, month_x, day_x;
    static final int dialogId = 0;
    private DatePickerDialog.OnDateSetListener dtPickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month;
            day_x = dayOfMonth;
            String date = day_x + "/" + (month_x + 1) + "/" + year_x;
            editVencimento.setText(date);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_despesa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.itListDespesa) {
            Intent intent = new Intent(this, DespesaActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.itHome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
