package com.dubeard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dubeard.R;
import com.dubeard.activity.model.Servico;

import java.util.List;

public class ServicoAdapter extends ArrayAdapter<Servico> {

    public ServicoAdapter(Context context, List<Servico> servicos) {
        super(context, 0, servicos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.servico_lista, parent, false);
        }

        Servico servico = getItem(position);

        TextView textDescricao = convertView.findViewById(R.id.textDescricao);
        TextView textValor = convertView.findViewById(R.id.textValor);

        textDescricao.setText(servico.getDescricao());
        textValor.setText(String.format("R$ %.2f", servico.getValor()));

        return convertView;
    }
}
