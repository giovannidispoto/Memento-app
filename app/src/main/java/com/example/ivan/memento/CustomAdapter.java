package com.example.ivan.memento; /**
 * Created by Ivan on 04/03/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    ArrayList nomeutente;
    ArrayList description;
    Context context;
    ArrayList immagineprofilo;
    ArrayList fotocaricata;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Activity mainActivity, ArrayList varnomeutente, ArrayList varimmagineprofilo, ArrayList varfotocaricata, ArrayList vardescription) {

        nomeutente=varnomeutente;
        context=mainActivity;
        immagineprofilo= varimmagineprofilo;
        fotocaricata = varfotocaricata;
        description = vardescription;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public class Holder
    {
        TextView granomeutente;
        ImageView grafotoprofilo;
        ImageView grafotocaricata;
        TextView gradescription;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.program_list, null);
        AnimationDrawable glidefotocaricataanimata;

        holder.granomeutente=(TextView) rowView.findViewById(R.id.nomeutente);
        holder.grafotoprofilo=(ImageView) rowView.findViewById(R.id.fotoprofilo);
        holder.grafotocaricata=(ImageView) rowView.findViewById(R.id.fotocaricata);
        holder.gradescription=(TextView) rowView.findViewById(R.id.description);

        holder.granomeutente.setText(nomeutente.get(position).toString());

        GlideDrawableImageViewTarget glidefotoprofilo = new GlideDrawableImageViewTarget(holder.grafotoprofilo);
        GlideDrawableImageViewTarget glidefotocaricata = new GlideDrawableImageViewTarget(holder.grafotocaricata);

        Glide.with(context).load(immagineprofilo.get(position).toString()).placeholder(R.drawable.fotoprofilodef).into(glidefotoprofilo);
        Glide.with(context).load(fotocaricata.get(position).toString()).into(glidefotocaricata);

        holder.gradescription.setText(description.get(position).toString());

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Post di " + nomeutente.get(position).toString(), Toast.LENGTH_LONG).show();
                Intent a = new Intent(context, postActivity.class);
                a.putExtra("nomeutente", nomeutente.get(position).toString());
                context.startActivity(a);
            }
        });

        holder.granomeutente.setOnClickListener(new OnClickListener() {
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