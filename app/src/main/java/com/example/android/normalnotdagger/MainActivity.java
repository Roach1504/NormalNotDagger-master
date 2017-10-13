package com.example.android.normalnotdagger;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.normalnotdagger.api.MessageServise;
import com.example.android.normalnotdagger.ui.history.IHistoryFragment;
import com.example.android.normalnotdagger.ui.map.BlankFragment;
import com.example.android.normalnotdagger.ui.map.MapFragment;
import com.example.android.normalnotdagger.ui.message.list_dialog.ImessageFragment;
import com.example.android.normalnotdagger.ui.news.InewsFragment;
import com.example.android.normalnotdagger.ui.cread_news.CreadNewsFragment;
import com.example.android.normalnotdagger.ui.user_info.UserFragment;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    SharedPreferences user;
    NavigationView navigationView;


    // Binder given to clients

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getSharedPreferences("user", Context.MODE_PRIVATE);

        Intent intent = new Intent(MainActivity.this, MessageServise.class);
        startService(intent);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        if(getIntent().getStringExtra("push")!= null){
            ImessageFragment youFragment = new ImessageFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
        }
        else {
            InewsFragment youFragment = new InewsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();

        }


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(user.getString("id", "n").equals("n")){
            Log.e("vis", "vis");
            navigationView.getMenu().getItem(2).setVisible(false);
            navigationView.getMenu().getItem(3).setVisible(false);
            navigationView.getMenu().getItem(4).setVisible(false);

        }
        else{
            navigationView.getMenu().getItem(2).setVisible(true);
            navigationView.getMenu().getItem(3).setVisible(true);
            navigationView.getMenu().getItem(4).setVisible(true);
        }




        Log.e("Menu", "Cread");
    }

    @Override
    public void onBackPressed() {
        Log.e("Menu", "onBackPressed");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);




        Log.e("Menu", "onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Log.e("Menu", "onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    boolean marker = false;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.e("Menu", "onNavigationItemSelected");


        // Handle navigation view item clicks here.
        int id = item.getItemId();


        Fragment fragment = null;

        Class fragmentClass = null;

        if (id == R.id.nav_camera) {
            if(marker){clear();}
            InewsFragment youFragment = new InewsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
//            item.setChecked(true);// Выводим выбранный пункт в заголовке
//            setTitle(item.getTitle());
        } else if (id == R.id.nav_gallery) {
            if(marker){clear();}
            UserFragment youFragment = new UserFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
//            item.setChecked(true);// Выводим выбранный пункт в заголовке
//            setTitle(item.getTitle());
        } else if (id == R.id.nav_slideshow) {
            if(marker){clear();}
            CreadNewsFragment youFragment = new CreadNewsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
//            item.setChecked(true);// Выводим выбранный пункт в заголовке
//            setTitle(item.getTitle());

        } else if (id == R.id.nav_manage) {
            marker = true;
            fragment = null;
            try {
                fragment = (Fragment) MapFragment.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
//            item.setChecked(true);// Выводим выбранный пункт в заголовке
//            setTitle(item.getTitle());

        }
        else if (id == R.id.nav_history) {
            if (marker) {
                clear();
            }
            IHistoryFragment youFragment = new IHistoryFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
//            item.setChecked(true);// Выводим выбранный пункт в заголовке
//            setTitle(item.getTitle());
        }
        else if(id == R.id.nav_my_lent){
            if(marker){clear();}
            InewsFragment youFragment = new InewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("pod", "podpiski");
            youFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();

        }
        else if(id == R.id.nav_message){
            if (marker) {
                clear();
            }
            ImessageFragment youFragment = new ImessageFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
        }
        item.setChecked(true);// Выводим выбранный пункт в заголовке
        setTitle(item.getTitle());
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void clear(){
        marker  = false;
        Fragment fragment = null;
        try {
            fragment = (Fragment) BlankFragment.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.content, fragment).commit();
    }

}
