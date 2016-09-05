package net.ozielguimaraes.price.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.ozielguimaraes.price.R;
import net.ozielguimaraes.price.domain.entities.Despesa;

import java.util.Date;

/**
 * Created by Oziel on 01/09/2016.
 */
public class DespesaArrayAdapter extends ArrayAdapter<Despesa> {

    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;

    public DespesaArrayAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            view = inflater.inflate(resource, parent, false);

            viewHolder.txtCor = (TextView) view.findViewById(R.id.txtCor);
            viewHolder.txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
            viewHolder.txtValor = (TextView) view.findViewById(R.id.txtValor);
            viewHolder.txtVencimento = (TextView) view.findViewById(R.id.txtVencimento);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Despesa despesa = getItem(position);

        if (new Date().before(despesa.getVencimento()))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
        else if (new Date().after(despesa.getVencimento()))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        else
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        viewHolder.txtDescricao.setText(despesa.getDescricao());
        viewHolder.txtValor.setText(despesa.getValor().toString());
        viewHolder.txtVencimento.setText(despesa.getVencimento().toString());

        return view;
    }

    static class ViewHolder {
        TextView txtCor;
        TextView txtDescricao;
        TextView txtValor;
        TextView txtVencimento;
    }
}
