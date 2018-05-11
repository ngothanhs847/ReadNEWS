package vn.edu.tdc.readnews;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.edu.tdc.readnews.Adapters.NewsAdapter;
import vn.edu.tdc.readnews.Adapters.SlidingMenuAdapter;
import vn.edu.tdc.readnews.Models.ItemSlideMenu;
import vn.edu.tdc.readnews.Models.RssItem;

public class ListNewsActivity extends AppCompatActivity {

    private ListView lvCategory;
    private ListView lvNews;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ProgressDialog dialog;

    private SlidingMenuAdapter adapter;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    List<RssItem> listItemRss = new ArrayList<RssItem>();
    //ArrayList<ItemSlideMenu> ListWeb;
    private List<ItemSlideMenu> ListCategory = new ArrayList<>();
    private int paper = -1;
    private int index = 0;

    Intent intent;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_news_layout);

        //anh xa
        AnhXa();

        //setup data
//        ListWeb = new ArrayList<>();
//
//        for (int i = 0; i < Variables.PAPERS.length; i++) {
//            ListWeb.add(new ItemSlideMenu(Variables.ICONS[i], Variables.PAPERS[i]));
//        }

        intent = getIntent();
        paper = intent.getIntExtra("data", 0);
        //Toast.makeText(ListNewsActivity.this, paper + "---", Toast.LENGTH_SHORT).show();



        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected)
        {
            dialog = ProgressDialog.show(ListNewsActivity.this, "", "Loading...");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadXML(paper).execute(Variables.LINKS[paper][0]);
                }
            });


//            adapter = new SlidingMenuAdapter(this, listItemSlideMenu);
//            listViewSliding.setAdapter(adapter);
//
            ListCategory = new ArrayList<>();
            for (int i = 0; i < Variables.CATEGORIES[paper].length; i++) {
                ListCategory.add(new ItemSlideMenu(Variables.ICON_ITEM[paper][i], Variables.CATEGORIES[paper][i]));
            }
            adapter  = new SlidingMenuAdapter(ListNewsActivity.this, ListCategory);
            lvCategory.setAdapter(adapter);


            // hien thi icon open/close sliding list
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // set Tilte
            setTitle(ListCategory.get(index).getTitle());

            //item selected
            lvCategory.setItemChecked(0, true);

            // close menu
            drawerLayout.closeDrawer(lvCategory);

            //replaceFragment(0);
//            listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                    //setTitle(listItemSlideMenu.get(position).getTitle());
//                    listViewSliding.setItemChecked(position, true);
//                    paper = position;
//
//                    listItemDanhMuc = new ArrayList<>();
//                    for (int i = 0; i < Variables.CATEGORIES[position].length; i++) {
//                        listItemDanhMuc.add(new ItemSlideMenu(Variables.ICON_ITEM[position][i], Variables.CATEGORIES[position][i]));
//                    }
//                    adapterDanhMuc = new SlidingMenuAdapter(ListRssActivity.this, listItemDanhMuc);
//                    listChiTietWeb.setAdapter(adapterDanhMuc);
//
//                    listTrangWeb.setAdapter(null);
//                    //dialog = ProgressDialog.show(ListNewsActivity.this, "", "Loading " + Variables.CATEGORIES[position][0] + "...");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //new ReadXML(position).execute(Variables.LINKS[position][0]);
//                        }
//                    });
//                    listTrangWeb.clearTextFilter();
//                    drawerLayout.closeDrawer(listViewSliding);
//
//                }
//            });

            lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    lvCategory.setItemChecked(position, true);
                    lvNews.setAdapter(null);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ReadXML(paper).execute(Variables.LINKS[paper][position]);
                        }
                    });

                    lvNews.clearTextFilter();
                    drawerLayout.closeDrawer(lvCategory);
                    index = position;
                    setTitle(ListCategory.get(position).getTitle());
                }
            });


            lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ListNewsActivity.this, NewsActivity.class);
                    intent.putExtra(Variables.LINK, listItemRss.get(position).getLink());
                    startActivity(intent);
                }
            });
        }
        else
        {
            String name = "Bạn không có kết nối internet. \n" +
                    "Hãy bật Wifi hoặc 3G để sử dụng!";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {

                int state = (int)drawerView.getTag();
                if(state == 1){
                    super.onDrawerOpened(drawerView);
                    setTitle("Chọn danh mục");
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                int state = (int)drawerView.getTag();
                if(state == 1) {
                    super.onDrawerClosed(drawerView);
                    setTitle(ListCategory.get(index).getTitle());
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                int state = (int)drawerView.getTag();
                if(state == 1) {
                    super.onDrawerSlide(drawerView, slideOffset);
                }
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    public void AnhXa() {
        lvCategory = (ListView) findViewById(R.id.lv_left);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lvNews = (ListView) findViewById(R.id.listNew);

        lvCategory.setTag(1);
    }

    public class ReadXML extends AsyncTask<String, Integer, String> {
        private int paper;

        public ReadXML(int paper) {

            this.paper = paper;
        }

        @Override
        protected String doInBackground(String... params) {
            String kq = "";
            if (paper < 6) {
                kq = getXmlFromUrlNomal(params[0]);
            } else {
                kq = getXmlFromUrl(params[0]);
            }
            return kq;
        }

        @Override
        protected void onPostExecute(String s) {
            List<RssItem> itemList = new ArrayList<RssItem>();
            String kq = "";
            //RssItem item = new RssItem();
            XMLDOMParser parser = new XMLDOMParser();
            Document doc = parser.getDocument(s);
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                RssItem item = new RssItem();
                Element e = (Element) nodeList.item(i);

                NodeList titleNode = e.getElementsByTagName("title");
                Element titleElement = (Element) titleNode.item(0);
                item.setTitle(titleElement.getFirstChild().getNodeValue());

                NodeList linkNode = e.getElementsByTagName("link");
                Element linkElement = (Element) linkNode.item(0);
                item.setLink(linkElement.getFirstChild().getNodeValue());

                NodeList pubDateNode = e.getElementsByTagName("pubDate");
                Element pubDateElement = (Element) pubDateNode.item(0);
                String date = pubDateElement.getFirstChild().getNodeValue();

//                Date d = null;
//                try {
//                    d = simpleDateFormat.parse(date);
//                } catch (ParseException e1) {
//                    //e1.printStackTrace();
//                }
//                String tg = simpleDateFormat.format(d);
                item.setDate(date);

                NodeList node = doc.getElementsByTagName("description");
                Element desElment = (Element) node.item(0);
                //kq = setDescription(desElment.getFirstChild().getNodeValue());
                //item.setDescription(kq);

                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                String cdata = node.item(i + 1).getTextContent();
                Matcher matcher = p.matcher(cdata);
                if(matcher.find())
                {
                    item.setDescription(matcher.group(1));
                }

                itemList.add(item);
            }

             //Toast.makeText(ListNewsActivity.this,""+itemList.get(0).getDescription(),Toast.LENGTH_LONG).show();
            if (dialog != null) {
                dialog.dismiss();
            }
            listItemRss = itemList;
            NewsAdapter adapter = new NewsAdapter(ListNewsActivity.this, paper, itemList);
            lvNews.setAdapter(adapter);
        }
    }

    public String setDescription(String description) {
        String img = "";




        //parse description for any image or video links
        if (description.contains("<img ")) {
            img = description.substring(description.indexOf("<img "));
            String cleanUp = img.substring(0, img.indexOf(">") + 1);
            img = img.substring(img.indexOf("src=") + 5);
            int indexOf = img.indexOf("'");
            if (indexOf == -1) {
                indexOf = img.indexOf("\"");
            }
            img = img.substring(0, indexOf);
        }
        return img;
    }

    private static String getXmlFromUrl(String link) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(link);

            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public String getXmlFromUrlNomal(String url) {
        String xml = null;

        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            if (drawerLayout.isDrawerOpen(lvCategory)) {
                drawerLayout.closeDrawer(lvCategory);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
