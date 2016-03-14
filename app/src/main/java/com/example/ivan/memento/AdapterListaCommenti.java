package com.example.ivan.memento;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

/**
 * Created by Ivan on 13/03/2016.
 */
public class AdapterListaCommenti extends BaseAdapter {
    ArrayList nomeutente;
    ArrayList commento;
    Context context;
    ArrayList immagineprofilo;
    private static LayoutInflater inflater = null;

    public AdapterListaCommenti(Activity mainActivity, ArrayList varnomeutente, ArrayList varimmagineprofilo, ArrayList varcommento) {

        nomeutente = varnomeutente;
        context = mainActivity;
        immagineprofilo = varimmagineprofilo;
        commento = varcommento;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return nomeutente.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView granomeutente;
        ImageView grafotoprofilo;
        TextView gracommento;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listacommenti, null);

        holder.granomeutente = (TextView) rowView.findViewById(R.id.textView8);
        holder.grafotoprofilo = (ImageView) rowView.findViewById(R.id.imageView);
        holder.gracommento = (TextView) rowView.findViewById(R.id.textView9);

        holder.granomeutente.setText(nomeutente.get(position).toString());
        holder.gracommento.setText(commento.get(position).toString());

        GlideDrawableImageViewTarget glidefotoprofilo = new GlideDrawableImageViewTarget(holder.grafotoprofilo);
        Glide.with(context).load(immagineprofilo.get(position).toString()).into(glidefotoprofilo);

        holder.granomeutente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Utente " + nomeutente.get(position).toString(), Toast.LENGTH_LONG).show();
                Intent a = new Intent(context, profiloActivity.class);
                a.putExtra("nomeutente", nomeutente.get(position).toString());
                context.startActivity(a);
            }
        });

        return rowView;
    }
}
