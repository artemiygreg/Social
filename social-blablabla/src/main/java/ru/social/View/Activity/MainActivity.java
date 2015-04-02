package ru.social.View.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ru.social.R;
import ru.social.View.Adapter.MenuAdapter;
import ru.social.View.Fragments.MyProfileFragment;
import ru.social.View.Fragments.NewsFragment;


public class MainActivity extends BaseActivity implements ListView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ListView menuListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private String[] arrayItems;
    public static final int MY_PROFILE = 1;
    public static final int MY_EVENT = 2;
    public static final int NEWS = 3;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar(toolbar, "", true, true);
        arrayItems = getResources().getStringArray(R.array.arrayItemsMenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuListView = (ListView) findViewById(R.id.menuListView);

        menuListView.addHeaderView(header());
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        menuListView.setAdapter(new MenuAdapter(this, arrayItems));
        menuListView.setOnItemClickListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.titleMainMenu, R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(3);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }
    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        actionBarDrawerToggle.onConfigurationChanged(config);
    }
    private void selectItem(int position) {
        drawerLayout.closeDrawer(menuListView);
        if(position >= 1) {
            switch (position) {
                case MY_PROFILE:
                    fragment = new MyProfileFragment();
                    break;
                case NEWS:
                    fragment = new NewsFragment();
                    break;
            }
            assert fragment != null;
            final FragmentManager fragmentManager = getFragmentManager();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }
            }, 350);
            menuListView.setItemChecked(position - 1, true);
            setTitle(arrayItems[position - 1]);
        }
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(menuListView))
            drawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }
    private View header(){
        return getLayoutInflater().inflate(R.layout.layout_header_menu, null, false);
    }
}
