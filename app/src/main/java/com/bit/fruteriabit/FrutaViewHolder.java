package com.bit.fruteriabit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

//FrutaViewHolder class
public class FrutaViewHolder extends RecyclerView.ViewHolder{

    private final TextView frutaItemView;

   private FrutaViewHolder(View itemView) {
       super(itemView);
       frutaItemView = itemView.findViewById(R.id.textViewNombre);
   }

   public void bind (String texto) {
        frutaItemView.setText(texto);
   }

   static FrutaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruta_item, parent, false);
        return new FrutaViewHolder(view);
   }
}
