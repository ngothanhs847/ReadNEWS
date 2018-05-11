package vn.edu.tdc.readnews.Adapters;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.readnews.Models.Website;
import vn.edu.tdc.readnews.R;

public class PaperAdapter extends ArrayAdapter<Website> {
    private AppCompatActivity context;
    private int layout;
    private ArrayList<Website> list;

    public PaperAdapter(AppCompatActivity context, int layout, ArrayList<Website> list) {
        super(context, layout, list);
        this.context = context;
        this.list = list;
        this.layout = layout;
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
        LayoutInflater inflater = (LayoutInflater)context.getLayoutInflater();
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(this.layout, paprent, false);
            viewHolder.imageView =(ImageView)convertView.findViewById(R.id.item_img);
            viewHolder.textView =(TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Website website = list.get(position);

        viewHolder.imageView.setImageResource(website.getImage());
        viewHolder.textView.setText(website.getName());

        return convertView;
    }
}
