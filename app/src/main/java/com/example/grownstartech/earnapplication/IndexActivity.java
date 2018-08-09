package com.example.grownstartech.earnapplication;

import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class IndexActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.arrow_down));
        setSupportActionBar(toolbar);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new MenuFragment());
        tx.commit();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification_top) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new ActionBarNotificationFragment()).commit();
            return true;
        }
       if (id == R.id.action_user_top) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new NotifyFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawer.closeDrawer(GravityCompat.START);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new MenuFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_profile) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawer.closeDrawer(GravityCompat.START);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new ProfileFragment()).commit();

        }  else if (id == R.id.nav_transactions) {

            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, false);
            navigationView.getMenu().setGroupVisible(R.id.navTransMenu, true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);


        }
        else if (id == R.id.nav_trans_back_btn) {
            navigationView.getMenu().setGroupVisible(R.id.navTransMenu, false);
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, true);

        }

        else if (id == R.id.nav_payment_details) {

            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, false);
            navigationView.getMenu().setGroupVisible(R.id.navPinsMenu, true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        } else if (id == R.id.nav_pins_back_btn) {
            navigationView.getMenu().setGroupVisible(R.id.navPinsMenu, false);
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, true);

        }
        else if (id == R.id.nav_purchasing) {
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, false);
            navigationView.getMenu().setGroupVisible(R.id.navPurchasingMenu, true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

        }
        else if (id == R.id.nav_purchasing_back_btn) {
            navigationView.getMenu().setGroupVisible(R.id.navPurchasingMenu, false);
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, true);

        }
        else if (id==R.id.nav_summary){
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawer.closeDrawer(GravityCompat.START);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new SummaryFragment()).commit();

        }
        else if (id == R.id.nav_advertisements) {
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, false);
            navigationView.getMenu().setGroupVisible(R.id.nav_advertisments_Menu, true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

        }
        else if (id == R.id.nav_advertisments_back_btn) {
            navigationView.getMenu().setGroupVisible(R.id.nav_advertisments_Menu, false);
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, true);

        }
        else if (id == R.id.nav_network) {
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, false);
            navigationView.getMenu().setGroupVisible(R.id.nav_networks_Menu, true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

        }
        else if (id == R.id.nav_networks_back_btn) {
            navigationView.getMenu().setGroupVisible(R.id.nav_networks_Menu, false);
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, true);

        }
        else if (id == R.id.nav_settings) {
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, false);
            navigationView.getMenu().setGroupVisible(R.id.nav_settings_Menu, true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

        }
        else if (id == R.id.nav_settings_back_btn) {
            navigationView.getMenu().setGroupVisible(R.id.nav_settings_Menu, false);
            navigationView.getMenu().setGroupVisible(R.id.navMenuGroup, true);

        }

        else if (id == R.id.nav_view_ptc_ads) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawer.closeDrawer(GravityCompat.START);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new PtcFragment()).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);

        //Unlock It
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//        drawerLayout.closeDrawer(drawerView);
        return true;
    }
}
