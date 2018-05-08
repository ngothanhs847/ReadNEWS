package vn.edu.tdc.readnews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.readnews.Models.Website;
import vn.edu.tdc.readnews.R;


/**
 * Created by NGUYEN DUC LINH on 25/03/2016.
 */
public class PaperAdapter extends ArrayAdapter<Website> {
    private Context context;
    private int layout;
    private ArrayList<Website> list;

    public PaperAdapter(Context context, int layout, ArrayList<Website> list) {
        super(context, layout, list);
        this.context = context;
        this.list = list;
    }

//    public PaperAdapter(Context context, ArrayList<Website> list) {
//        super(context, list);
//        this.context = context;
//        this.list = list;
//    }

    private class ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup paprent){
        ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.website_item_layout, paprent, false);
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            viewHolder.imageView =(ImageView)convertView.findViewById(R.id.item_img);
            viewHolder.textView =(TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(list.get(position).getImage());
        viewHolder.textView.setText(list.get(position).getName());

        return convertView;
    }
}
