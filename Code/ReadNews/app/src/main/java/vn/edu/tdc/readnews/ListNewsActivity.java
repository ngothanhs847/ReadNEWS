package vn.edu.tdc.readnews;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.ArrayList;
import java.util.List;

import vn.edu.tdc.readnews.Models.RssItem;

public class ListNewsActivity extends AppCompatActivity {

//    private List<ItemSlideMenu> listItemSlideMenu;
//    private List<ItemSlideMenu> listItemDanhMuc;

    //private SlidingMenuAdapter adapter, adapterDanhMuc;

    private ListView listViewSliding;
    private ListView listTrangWeb;
    private ListView listChiTietWeb;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int paper = 0;

    List<RssItem> itemList1 = new ArrayList<RssItem>();
    private ProgressDialog dialog;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_news_layout);
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (isConnected)
        {
            dialog = ProgressDialog.show(this, "", "Loading...");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //new ReadXML(0).execute(Variables.LINKS[0][0]);
                }
            });

//            listItemSlideMenu = new ArrayList<>();
//
//            for (int i = 0; i < Variables.PAPERS.length; i++) {
//                listItemSlideMenu.add(new ItemSlideMenu(Variables.ICONS[i], Variables.PAPERS[i]));
//            }
//            adapter = new SlidingMenuAdapter(this, listItemSlideMenu);
//            listViewSliding.setAdapter(adapter);
//
//            listItemDanhMuc = new ArrayList<>();
//            for (int i = 0; i < Variables.CATEGORIES[0].length; i++) {
//                listItemDanhMuc.add(new ItemSlideMenu(Variables.ICON_ITEM[0][i], Variables.CATEGORIES[0][i]));
//            }
//            adapterDanhMuc = new SlidingMenuAdapter(this, listItemDanhMuc);
//            listChiTietWeb.setAdapter(adapterDanhMuc);

            // hien thi icon open/close sliding list
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // set Tilte
            //setTitle(listItemSlideMenu.get(0).getTitle());
            //item selected
            listViewSliding.setItemChecked(0, true);

            // close menu
            drawerLayout.closeDrawer(listViewSliding);
            //replaceFragment(0);
            listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    //setTitle(listItemSlideMenu.get(position).getTitle());
                    listViewSliding.setItemChecked(position, true);
                    paper = position;


//                    listItemDanhMuc = new ArrayList<>();
//                    for (int i = 0; i < Variables.CATEGORIES[position].length; i++) {
//                        listItemDanhMuc.add(new ItemSlideMenu(Variables.ICON_ITEM[position][i], Variables.CATEGORIES[position][i]));
//                    }
//                    adapterDanhMuc = new SlidingMenuAdapter(ListRssActivity.this, listItemDanhMuc);
//                    listChiTietWeb.setAdapter(adapterDanhMuc);


                    listTrangWeb.setAdapter(null);
                    //dialog = ProgressDialog.show(ListNewsActivity.this, "", "Loading " + Variables.CATEGORIES[position][0] + "...");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //new ReadXML(position).execute(Variables.LINKS[position][0]);
                        }
                    });
                    listTrangWeb.clearTextFilter();
                    drawerLayout.closeDrawer(listViewSliding);

                }
            });

            listChiTietWeb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    listViewSliding.setItemChecked(position, true);

                    listTrangWeb.setAdapter(null);
                    //dialog = ProgressDialog.show(ListRssActivity.this, "", "Loading " + Variables.CATEGORIES[paper][position] + " ...");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //new ReadXML(paper).execute(Variables.LINKS[paper][position]);
                        }
                    });
                    listTrangWeb.clearTextFilter();
                    drawerLayout.closeDrawer(listChiTietWeb);
                }
            });


            listTrangWeb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ListNewsActivity.this, NewsActivity.class);
                    //intent.putExtra(Variables.LINK, itemList1.get(position).getLink());
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
    }

    public class ReadXML extends AsyncTask<String, Integer, String> {
        private int paper;

        public ReadXML(int paper) {

            this.paper = paper;
        }

        @Override
        protected String doInBackground(String... params) {
            String kq = "";
            if (paper < 5) {
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
                item.setDate(pubDateElement.getFirstChild().getNodeValue());

                NodeList node = e.getElementsByTagName("description");
                Element desElment = (Element) node.item(0);
                kq = setDescription(desElment.getFirstChild().getNodeValue());
                item.setDescription(kq);
                itemList.add(item);
            }

            // Toast.makeText(ListRssActivity.this,""+itemList.get(0).getDescription(),Toast.LENGTH_LONG).show();
//            if (dialog != null) {
//                dialog.dismiss();
//            }
//            itemList1 = itemList;
//            NewsAdapter adapter = new NewsAdapter(ListRssActivity.this, paper, itemList);
//            listTrangWeb.setAdapter(adapter);
        }
    }

    public String setDescription(String description) {
//        descrip = description;
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

//            descrip = img;
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
        // return XML
        return xml;
    }
}
