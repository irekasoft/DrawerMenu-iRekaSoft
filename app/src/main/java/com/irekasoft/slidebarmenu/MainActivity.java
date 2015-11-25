package com.irekasoft.slidebarmenu;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private WebView webView;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout drawer;

    ActionBarDrawerToggle mDrawerToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // WEBVIEW
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://irekasoft.com");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
            }
        });

        // RECYCLER VIEW
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);

        // add adapter
        mAdapter = new MyRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.openDrawer,
                R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        // Drawer Listener set to the Drawer toggle
        drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        //
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this,

                new GestureDetector.SimpleOnGestureListener() {
                    @Override public boolean onSingleTapUp(MotionEvent e) {

                        return true;
                    }

                });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(),e.getY());

                if(child!=null && mGestureDetector.onTouchEvent(e)){
                    drawer.closeDrawers();
                    int idx = rv.getChildPosition(child);
                    Toast.makeText(MainActivity.this, "Loading for page: " + idx, Toast.LENGTH_SHORT).show();
                    loadWeb(idx);
                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

   }

    private void loadWeb(int idx){

        String url ="http://";
        switch (idx){
            case 0:
                url ="http://irekasoft.com";
                break;
            case 1:
                url ="http://irekasoft.com/apps";
                break;
            case 2:
                url ="http://irekasoft.com/services";
                break;
            case 3:
                url ="http://irekasoft.com/about";
                break;
            case 4:
                url ="http://irekasoft.com/learn";
                break;
            case 5:
                url ="http://irekasoft.com/developer";
                break;
            case 6:
                url ="http://facebook.com/irekasoft";
                break;
            case 7:
                url ="http://twitter.com/irekasoft";
                break;
            case 8:
                url ="http://irekasoft.com/developer";
                break;
            default:
                url ="http://";

        }
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
