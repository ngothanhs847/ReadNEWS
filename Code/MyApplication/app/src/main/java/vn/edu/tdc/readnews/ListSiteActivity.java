package vn.edu.tdc.readnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import vn.edu.tdc.readnews.Adapters.PaperAdapter;
import vn.edu.tdc.readnews.Models.Website;

public class ListSiteActivity extends AppCompatActivity {
    GridView gvList;
    PaperAdapter adapter;
    ArrayList<Website> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_site_layout);

        gvList = (GridView) findViewById(R.id.gvList);

        takeData();

        adapter = new PaperAdapter(ListSiteActivity.this, R.layout.website_item_layout, list);

        gvList.setAdapter(adapter);


        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ListSiteActivity.this, i + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListSiteActivity.this, ListNewsActivity.class);
                intent.putExtra("data", i);
                intent.putExtra("name", list.get(i).getName());
                startActivity(intent);
            }
        });
    }

    private void takeData()
    {
        Website website = new Website();
        website.setImage(R.drawable.hai_tu);
        website.setName("24h");

        Website website2 = new Website();
        website2.setImage(R.drawable.tuoi_tre);
        website2.setName("Tuổi Trẻ");

        Website website3 = new Website();
        website3.setImage(R.drawable.dan_tri);
        website3.setName("Dân Trí");

        Website website4 = new Website();
        website4.setImage(R.drawable.express);
        website4.setName("VnExpress");

        Website website5 = new Website();
        website5.setImage(R.drawable.lao_dong);
        website5.setName("Lao Động");

        Website website6 = new Website();
        website6.setImage(R.drawable.thanh_nien);
        website6.setName("Thanh Niên");

        Website website7 = new Website();
        website7.setImage(R.drawable.viet_nam_vn);
        website7.setName("Việt Nam Net");

        Website website8 = new Website();
        website8.setImage(R.drawable.tien_phong_logo);
        website8.setName("Tiền Phong");

        list.add(website4);
        list.add(website3);
        list.add(website2);
        list.add(website5);
        list.add(website);
        list.add(website6);
        list.add(website7);
        list.add(website8);
    }
}
