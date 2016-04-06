package com.zhy.colorfulstatusbar;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.HandlerThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.mingle.sweetpick.CustomDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.zhy.colorfulstatusbar.Adapter.MainTabAdapter;
import com.zhy.colorfulstatusbar.Fragments.NestedscrollFragment;
import com.zhy.colorfulstatusbar.Fragments.RecyclerGridFragment;
import com.zhy.colorfulstatusbar.Fragments.RecyclerLinearFragment;
import com.zhy.colorfulstatusbar.Fragments.RecyclerStaggeredFragment;
import com.zhy.colorfulstatusbar.Interface.RecycleViewItemOnClickListener;
import com.zhy.colorfulstatusbar.R;

import java.util.ArrayList;
import java.util.List;

public class OperatorLogActivity extends BaseCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainTabAdapter mAdapter;
    private SweetSheet mSweetSheet;
    private HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_log);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.id_relativelayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
//        toolbar.setTitle(getResources().getString(R.string.operatelog));
        setTitle(getResources().getString(R.string.operatelog));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerLinearFragment());
        fragments.add(new RecyclerGridFragment());
        fragments.add(new RecyclerStaggeredFragment());
        fragments.add(new NestedscrollFragment());

        List<String> titles = new ArrayList<>();
        titles.add("RecyclerLinear");
        titles.add("RecyclerGrid");
        titles.add("RecyclerStaggered");
        titles.add("Nestedscroll");

        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));


        mAdapter = new MainTabAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        FloatingActionButton f = (FloatingActionButton) findViewById(R.id.fab);
        mSweetSheet = new SweetSheet(relativeLayout);
        CustomDelegate customDelegate = new CustomDelegate(true,
                CustomDelegate.AnimationType.DuangLayoutAnimation);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_view, null, false);
//设置自定义视图.
        customDelegate.setCustomView(view);
//设置代理类
        mSweetSheet.setDelegate(customDelegate);
//因为使用了 CustomDelegate 所以mSweetSheet3中的 setMenuList和setOnMenuItemClickListener就没有效果了
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSweetSheet.dismiss();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSweetSheet.toggle();

            }
        });

    }

    @Override
    public void onBackPressed() {
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_scroller_hidden_toolbar) {
//            startActivity(new Intent(this, JianShuActivity.class));
//
//
//        } else if (id == R.id.nav_recycler_hidden_toolbar) {
//            startActivity(new Intent(this, HideToolBarActivity.class));
//        } else if (id == R.id.nav_viewpager) {
//            startActivity(new Intent(this, HeaderViewPagerActivty.class));
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}