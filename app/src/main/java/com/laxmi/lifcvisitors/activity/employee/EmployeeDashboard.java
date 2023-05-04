package com.laxmi.lifcvisitors.activity.employee;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.laxmi.lifcvisitors.Contactus;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GaurdLogin;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.fragments.DashboardFragment;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

public class EmployeeDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    PrefConfig prefConfig;
    AlertDialog.Builder abd;

    AlertDialog  alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        prefConfig = new PrefConfig(this);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
     /*  TextView tv_emp_name = navigationView.findViewById(R.id.tv_emp_name);

       tv_emp_name.setText("sdvfcb");*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        DashboardFragment fragment = new DashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            /* abd = new AlertDialog.Builder(this);
            abd.setTitle("confirm Logout?");
                    abd.setMessage("Are you sure You Want to logout");
                            abd.setCancelable(false);
                                    abd.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });
                                    abd.setPositiveButton("No",null);
            AlertDialog  alertDialog = abd.create();*/

           super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
      //  Fragment fragment = null;
     //   FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_slideshow)
        {
            Intent intent = new Intent(EmployeeDashboard.this,Employee_profile_update.class);
            startActivity(intent);
        } else if (id == R.id.nav_Contactus) {
            Intent intents = new Intent(EmployeeDashboard.this, Contactus.class);
            startActivity(intents);
        } else if (id == R.id.nav_logout) {
            abd = new AlertDialog.Builder(this);
            abd.setTitle("confirm Logout?");
            abd.setMessage("Are you sure You Want to logout");
            abd.setCancelable(false);
            abd.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            abd.setPositiveButton("No",null);
            AlertDialog  alertDialog = abd.create();
            prefConfig.writeLoginStatus(false);
            alertDialog.show();
            startActivity(new Intent(EmployeeDashboard.this, EmployeeLogin.class));
            finishAffinity();

        }
      //  fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}