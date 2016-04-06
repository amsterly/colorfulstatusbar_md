package com.zhy.colorfulstatusbar;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zhy.colorfulstatusbar.Adapter.ChatAdapter;
import com.zhy.colorfulstatusbar.Fragment.CalibrationRecordFragment;
import com.zhy.colorfulstatusbar.Fragment.DetectionRecordFragment;
import com.zhy.colorfulstatusbar.Fragment.DetectionStatisticsReportFragment;
import com.zhy.colorfulstatusbar.Fragment.DeviceStateFragment;
import com.zhy.colorfulstatusbar.Fragment.MainFragment;
import com.zhy.colorfulstatusbar.Fragment.OperatelogFragment;
import com.zhy.colorfulstatusbar.Fragment.SubstanceDBFragment;
import com.zhy.colorfulstatusbar.Fragment.SysUpdateFragment;
import com.zhy.colorfulstatusbar.Fragment.SysUserManagerFragment;
import com.zhy.colorfulstatusbar.Fragment.DeviceSetupFragment;
import com.zhy.colorfulstatusbar.Fragment.SystemStateFragment;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity  {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment mFragment;
    private int mMenuItemID = -1;
    private NavigationView navigationView;
    private MainFragment mainFragment = null;
    private CalibrationRecordFragment calibrationRecordFragment = null;

    private DetectionRecordFragment detectionRecordFragment = null;
    private OperatelogFragment operatelogFragment = null;
    private SubstanceDBFragment substanceDBFragment = null;
    private DetectionStatisticsReportFragment detectionStatisticsReportFragment = null;
    private DeviceStateFragment deviceStateFragment = null;
    private SysUpdateFragment sysUpdateFragment=null;
    private SystemStateFragment systemStateFragment=null;
    private SysUserManagerFragment sysUserManagerFragment=null;
    //承载fragment
    private DeviceSetupFragment deviceSetupFragment =null;
    private int tabIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == savedInstanceState) {
            mMenuItemID = R.id.id_detectionrecord;
        } else {
            mMenuItemID = savedInstanceState.getInt("mMenuItemID");
        }
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

//        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.id_nestedscrollview);
//        nestedScrollView.setNestedScrollingEnabled(false);

        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
        navigationView = (NavigationView) findViewById(R.id.id_navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mMenuItemID = menuItem.getItemId();
                switch (mMenuItemID) {

                    case R.id.id_detection:
                        setTitle(R.string.detection);
                        if (mainFragment == null)
                            mainFragment = new MainFragment();
                        switchContent(mFragment, mainFragment);
                        break;
                    case R.id.id_calibration:
                        setTitle(R.string.calibration);
                        if (mainFragment == null)
                            mainFragment = new MainFragment();
                        switchContent(mFragment, mainFragment);

                        break;
                    case R.id.id_manualcalibration:
                        setTitle(R.string.manualcalibration);
                        if (mainFragment == null)
                            mainFragment = new MainFragment();
                        switchContent(mFragment, mainFragment);
                        break;
                    case R.id.id_calibrationrecord:
                        setTitle(R.string.calibrationrecord);
                        if (calibrationRecordFragment == null)
                            calibrationRecordFragment = new CalibrationRecordFragment();
                        switchContent(mFragment, calibrationRecordFragment);
                        break;
                    case R.id.id_detectionrecord:
                        setTitle(R.string.detectionrecord);
                        if (detectionRecordFragment == null)
                            detectionRecordFragment = new DetectionRecordFragment();
                        switchContent(mFragment, detectionRecordFragment);
                        break;
                    case R.id.id_operatelog:
                        setTitle(R.string.operatelog);
//                        if (operatelogFragment == null)
//                            operatelogFragment = new OperatelogFragment();
//                        switchContent(mFragment, operatelogFragment);

                        startActivity(new Intent(MainActivity.this, OperatorLogActivity.class));
                        break;
                    case R.id.id_substancedb:
                        setTitle(R.string.substancedb);
                        if (substanceDBFragment == null)
                            substanceDBFragment = new SubstanceDBFragment();
                        switchContent(mFragment, substanceDBFragment);
                        break;
                    case R.id.id_detectionstatisticsreport:
                        setTitle(R.string.detectionstatisticsreport);
                        if (detectionStatisticsReportFragment == null)
                            detectionStatisticsReportFragment = new DetectionStatisticsReportFragment();
                        switchContent(mFragment, detectionStatisticsReportFragment);
                        break;
                    case R.id.id_deepclean:
                        setTitle(R.string.deepclean);
                        if (mainFragment == null)
                            mainFragment = new MainFragment();
                        switchContent(mFragment, mainFragment);
                        break;
                    case R.id.id_deivicestate:
                        setTitle(R.string.deivicestate);
                        if(null==deviceStateFragment)
                            deviceStateFragment = new DeviceStateFragment();
                        switchContent(mFragment, deviceStateFragment);

                        break;
                    case R.id.id_systemupdate:
                        setTitle(R.string.systemupdate);
                        if(null==sysUpdateFragment)
                            sysUpdateFragment = new SysUpdateFragment();
                        switchContent(mFragment, sysUpdateFragment);
                        break;
                    case R.id.id_systemstate:
                        setTitle(R.string.systemstate);
                        if(null== systemStateFragment)
                            systemStateFragment = new SystemStateFragment();
                        switchContent(mFragment, systemStateFragment);
                        break;
                    case R.id.id_usermanagerr:
                        setTitle(R.string.sysusermanager);
                        if(null==sysUserManagerFragment)
                            sysUserManagerFragment = new SysUserManagerFragment();
                        switchContent(mFragment, sysUserManagerFragment);
                        break;
                    //-------
                    case R.id.id_setupsysparameter:
                        switchTabFragments(0);
                        break;
                    case R.id.id_setuphardwareparameter:
                        switchTabFragments(1);
                        break;
                    case R.id.id_setupalgorithmparameter:
                        switchTabFragments(2);
                        break;

                }
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.menu_drawer);
                navigationView.getMenu().findItem(mMenuItemID).setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return true;

            }
        });
        int width = ScreenUtils.getScreenWidth(this);
        if (width > 0) {
            ViewGroup.LayoutParams params = navigationView.getLayoutParams();
            params.width = width * 2 / 3;
            navigationView.setLayoutParams(params);
        }
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        StatusBarCompat.compat(this, 0xFFFF0000);
        StatusBarCompat.compat(this);
        initFragment(savedInstanceState);
    }

    private void switchTabFragments(int position) {
        tabIndex=position;
        if(deviceSetupFragment==null)
        {
            deviceSetupFragment = new DeviceSetupFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            deviceSetupFragment.setArguments(bundle);
        }
        else
        {
            deviceSetupFragment.ChangePageViewByPosition(position);
        }


        switchContent(mFragment, deviceSetupFragment);
    }

    private void initFragment(Bundle savedInstanceState) {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if (savedInstanceState == null) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

            MainFragment mainFragment = new MainFragment();
            mFragment = mainFragment;
            ft.replace(R.id.id_fragmentMain, mainFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main,menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        startActivity(new Intent(this,ChatActivity.class));
        startActivity(new Intent(this,ChatActivity.class));
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Glide.clear();
//        Glide.get(this).clearDiskCache();
        Glide.get(this).clearMemory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("mMenuItemID", mMenuItemID);
//        outState.putBundle("mMenuItemID", outState);
        super.onSaveInstanceState(outState);
    }

    //切换或者隐藏fragment通用做法
    public void switchContent(Fragment from, Fragment to) {
        if (mFragment != to) {
            mFragment = to;
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画  
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
//            ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
//                    R.  animator.fragment_slide_right_exit);9
            if (!to.isAdded()) {    // 先判断是否被add过  
                ft.hide(from).add(R.id.id_fragmentMain, to).commit(); // 隐藏当前的fragment，add下一个到Activity中  
            } else {
                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个  
            }

        }
    }


    boolean isEdit = false;

    /*
    切换toolBar布局
     */
    public void btnOnClick(View view) {
        isEdit = !isEdit;
        if (isEdit) {
            mToolbar.getMenu().clear();
            getMenuInflater().inflate(R.menu.test, mToolbar.getMenu());
        } else {
            mToolbar.getMenu().clear();
            getMenuInflater().inflate(R.menu.menu_main, mToolbar.getMenu());
        }

    }


}
