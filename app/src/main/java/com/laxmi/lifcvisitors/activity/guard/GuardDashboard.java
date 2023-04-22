package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.fragments.GuardDashboardFragment;
import com.laxmi.lifcvisitors.languageconvert.BaseActivity;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

public class GuardDashboard extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guard_dashboard);

        prefConfig = new PrefConfig(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_guard);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_guard);
        View headerView = navigationView.inflateHeaderView(R.layout.gaurd_header);
        TextView tvGuardName = headerView.findViewById(R.id.tv_guard_name);
        tvGuardName.setText(prefConfig.readName());


        FragmentManager fragmentManager = getSupportFragmentManager();
        GuardDashboardFragment fragment = new GuardDashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutguard, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_guard);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_guarddesignation) {
        } else if (id == R.id.nav_guardContactus) {

        }else if (id == R.id.nav_guardslideshow) {


        } else if (id == R.id.nav_guardlogout) {

            prefConfig.writeLoginStatus(false);
            startActivity(new Intent(GuardDashboard.this,GaurdLogin.class));
            finishAffinity();

       }
        fragmentManager.beginTransaction().replace(R.id.frameLayoutguard, fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout_guard);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}