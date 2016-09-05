package net.ozielguimaraes.price;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import net.ozielguimaraes.price.domain.entities.Despesa;
import net.ozielguimaraes.price.helpers.DespesaArrayAdapter;
import net.ozielguimaraes.price.helpers.Notification;
import net.ozielguimaraes.price.infra.data.repository.DbConnection;
import net.ozielguimaraes.price.infra.data.repository.DespesaRepository;


/**
 * Created by Oziel on 31/08/2016.
 */
public class DespesaActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageButton btnPesquisar;
    private EditText editPesquisa;
    private ListView lstDespesas;
    private ArrayAdapter<Despesa> adpDespesas;
    private DbConnection dataBase;
    private SQLiteDatabase conn;
    private DespesaRepository despesaRepository;

    public static final String PAR_DESPESA = "Despesa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_depesas);

        btnPesquisar = (ImageButton)findViewById(R.id.btnPesquisar);
        editPesquisa  = (EditText)findViewById(R.id.editPesquisa);
        lstDespesas  = (ListView)findViewById(R.id.listDespesas);

        btnPesquisar.setOnClickListener(this);
        lstDespesas.setOnItemClickListener(this);

        try {
            dataBase = new DbConnection(this);
            conn = dataBase.getWritableDatabase();
            despesaRepository = new DespesaRepository(conn);

            adpDespesas = despesaRepository.getDespesas(this);

            lstDespesas.setAdapter(adpDespesas);

            FiltrarDados filtrarDados = new FiltrarDados(adpDespesas);
            editPesquisa.addTextChangedListener(filtrarDados);
        }
        catch(SQLException ex)
        {
            Notification.ShowAlert(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null)conn.close();
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, AddDespesaActivity.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpDespesas = despesaRepository.getDespesas(this);
        lstDespesas.setAdapter(adpDespesas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_despesas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.itAddDespesa) {
            Intent intent = new Intent(this, AddDespesaActivity.class);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Despesa despesa = adpDespesas.getItem(position);

        Intent it = new Intent(this, AddDespesaActivity.class);
        it.putExtra(PAR_DESPESA, despesa);
        startActivityForResult(it, 0);
    }

    private class FiltrarDados implements TextWatcher
    {
        private ArrayAdapter<Despesa> arrayAdapter;

        private FiltrarDados(ArrayAdapter<Despesa> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
