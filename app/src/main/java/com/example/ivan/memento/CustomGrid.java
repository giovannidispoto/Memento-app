package com.example.ivan.memento;

/**
 * Created by Ivan on 08/03/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CustomGrid extends BaseAdapter{
    private Context mContext;
    private final String[] descrizione;
    private final String[] immagine;

    public CustomGrid(Context c, String[] vardescrizione, String[] varimmagine ) {
        mContext = c;
        this.immagine = varimmagine;
        this.descrizione = vardescrizione;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return descrizione.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridlayout, null);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            Picasso.with(mContext).load(immagine[position]).into(imageView);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}

