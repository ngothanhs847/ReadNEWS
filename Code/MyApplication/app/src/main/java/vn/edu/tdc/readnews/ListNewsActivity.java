package vn.edu.tdc.readnews;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ShareDialog shareDialog;
    private ShareLinkContent shareLinkContent;
    private CallbackManager callbackManager;

    List<RssItem> listItemRss = new ArrayList<RssItem>();
    private List<ItemSlideMenu> ListCategory = new ArrayList<>();
    private int paper = -1;
    private int index = 0;

    Intent intent;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.list_news_layout);

        //anh xa

        AnhXa();
//        String s = printKeyHash(ListNewsActivity.this);
//        Log.d("ma", s);
        shareDialog = new ShareDialog(ListNewsActivity.this);
        callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(ListNewsActivity.this, "Chia sẻ bài viết thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(ListNewsActivity.this, "Hủy chia sẻ bài viết!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(ListNewsActivity.this, "Chia sẻ bài viết không thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        //nhan index trang web
        intent = getIntent();
        paper = intent.getIntExtra("data", 0);

        //quan ly ket noi mang
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        //kiem tra ket noi mang
        if (isConnected)
        {
            dialog = ProgressDialog.show(ListNewsActivity.this, "", "Loading...");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadXML(paper).execute(Variables.LINKS[paper][0]);
                }
            });

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

            //chon danh muc
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


            //xem bai viet
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
            //loi ket noi mang
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

        registerForContextMenu(lvNews);
    }

    //result
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    //context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.news_menu, menu);
        //menu.add(0, R.id.share, 0, "Share News With Facebook");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(!isLoggedIn) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }
        
        if (ShareDialog.canShow(ShareLinkContent.class))
        {
            shareLinkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(listItemRss.get(adapterContextMenuInfo.position).getLink()))
                    .build();
            shareDialog.show(shareLinkContent);
        }

        return true;
    }


    //lay key cua facebook
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    // anh xa
    public void AnhXa() {
        lvCategory = (ListView) findViewById(R.id.lv_left);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lvNews = (ListView) findViewById(R.id.listNew);
        lvCategory.setTag(1);
    }

    //class doc xml
    public class ReadXML extends AsyncTask<String, Integer, String> {
        private int paper;

        public ReadXML(int paper) {

            this.paper = paper;
        }

        @Override
        protected String doInBackground(String... params) {
            String kq = "";
            if (paper != 6) {
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
                item.setDate(date);

                if(paper == 2 ) {
                    NodeList node = e.getElementsByTagName("description");
                    Element desElment = (Element) node.item(0);
                    kq = setDescription(desElment.getFirstChild().getNodeValue());
                    item.setDescription(kq);
                }
                else {
                    NodeList node = doc.getElementsByTagName("description");
                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    String cdata = node.item(i + 1).getTextContent();
                    Matcher matcher = p.matcher(cdata);
                    if (matcher.find()) {
                        item.setDescription(matcher.group(1));
                    }
                }

                itemList.add(item);
            }

            if (dialog != null) {
                dialog.dismiss();
            }
            listItemRss = itemList;
            NewsAdapter adapter = new NewsAdapter(ListNewsActivity.this, paper, itemList);
            lvNews.setAdapter(adapter);
        }
    }

    //lay anh tu xml
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

    //lay xml tu url
    private static String getXmlFromUrl(String link) {

        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(link);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

    public String getXmlFromUrlNomal(String url) {
        String xml = null;
        //Log.d("u", url + "  ....");
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

    //option menu
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
