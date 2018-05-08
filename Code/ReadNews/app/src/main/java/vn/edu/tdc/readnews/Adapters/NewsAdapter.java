package vn.edu.tdc.readnews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

import vn.edu.tdc.readnews.Models.RssItem;
import vn.edu.tdc.readnews.R;

public class NewsAdapter extends ArrayAdapter<RssItem> {
    private Context context;
    private List<RssItem> items;

    public NewsAdapter(Context context, int ivIcon, List<RssItem> items) {
        super(context, ivIcon, items);
        this.context = context;
        this.items = items;
    }

    private class ViewHolder
    {
        ImageView iv;
        TextView tv, txtPubdate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup paprent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.news_title_layout, paprent, false);
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.imgIcon);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.title);
            viewHolder.txtPubdate = (TextView)convertView.findViewById(R.id.pubDate);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //View rowView = inflater.inflate(R.layout.news_title_layout, paprent, false);

        if(items.get(position).getDescription().length()<5){
            viewHolder.tv.setText(items.get(position).getTitle());
        }else {
            Picasso.get().load(items.get(position).getDescription())
                    .placeholder(R.mipmap.bai_bao).error(R.mipmap.bai_bao).into(viewHolder.iv);

            viewHolder.tv.setText(items.get(position).getTitle().trim());
            viewHolder.txtPubdate.setText(items.get(position).getDate().trim());

        }
        return convertView;
    }
}
