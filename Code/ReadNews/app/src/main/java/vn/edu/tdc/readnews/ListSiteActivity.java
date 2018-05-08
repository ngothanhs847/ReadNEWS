package vn.edu.tdc.readnews;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        adapter = new PaperAdapter(ListSiteActivity.this, list);
    }
}
