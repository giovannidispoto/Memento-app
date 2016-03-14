package com.example.ivan.memento; /**
 * Created by Ivan on 04/03/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    ArrayList<String> nomeutente;
    ArrayList<String> description;
    Context context;
    ArrayList<String> immagineprofilo;
    ArrayList<String> fotocaricata;
    ArrayList<String> data;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Activity mainActivity, ArrayList<String> varnomeutente, ArrayList<String>varimmagineprofilo, ArrayList<String> varfotocaricata, ArrayList<String> vardescription, ArrayList<String> data) {

        nomeutente=varnomeutente;
        context=mainActivity;
        immagineprofilo= varimmagineprofilo;
        fotocaricata = varfotocaricata;
        description = vardescription;
        this.data = data;
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
        TextView data;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.program_list, null);

        holder.granomeutente=(TextView) rowView.findViewById(R.id.textView1);
        holder.grafotoprofilo=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.grafotocaricata=(ImageView) rowView.findViewById(R.id.imageView2);
        holder.gradescription=(TextView) rowView.findViewById(R.id.description);

        holder.granomeutente.setText(nomeutente.get(position));
        Picasso.with(context).load(immagineprofilo.get(position)).into(holder.grafotoprofilo);
        Picasso.with(context).load(fotocaricata.get(position)).into(holder.grafotocaricata);
        holder.gradescription.setText(description.get(position));

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+nomeutente.get(position), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
