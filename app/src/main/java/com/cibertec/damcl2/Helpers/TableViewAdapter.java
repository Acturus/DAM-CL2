package com.cibertec.damcl2.Helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cibertec.damcl2.R;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<UserRow> userInfo;

    public TableViewAdapter(List<UserRow> userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtNom.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtApePa.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtApeMa.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtEmail.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtCel.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNac.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtUbi.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtReg.setBackgroundResource(R.drawable.table_header_cell_bg);

        }
        else {
            UserRow fila = userInfo.get(rowPos-1);

            rowViewHolder.txtNom.setText(fila.getNombre());
            rowViewHolder.txtApePa.setText(fila.getAparteno());
            rowViewHolder.txtApeMa.setText(fila.getAmaterno());
            rowViewHolder.txtEmail.setText(fila.getEmail());
            rowViewHolder.txtCel.setText(fila.getCelular());
            rowViewHolder.txtNac.setText(fila.getFechaNac());
            rowViewHolder.txtUbi.setText(fila.getUbicacion());
            rowViewHolder.txtReg.setText(fila.getFechaReg());
        }
    }

    @Override
    public int getItemCount() {
        return userInfo.size()+1; // one more to add header row
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtNom, txtApePa, txtApeMa, txtEmail, txtCel, txtNac, txtUbi, txtReg;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtNom = itemView.findViewById(R.id.txtNombre);
            txtApePa = itemView.findViewById(R.id.txtApePa);
            txtApeMa = itemView.findViewById(R.id.txtApeMa);
            txtEmail = itemView.findViewById(R.id.email);
            txtCel = itemView.findViewById(R.id.celular);
            txtNac = itemView.findViewById(R.id.fechaNac);
            txtUbi = itemView.findViewById(R.id.ubica);
            txtReg = itemView.findViewById(R.id.registro);
        }
    }
}
