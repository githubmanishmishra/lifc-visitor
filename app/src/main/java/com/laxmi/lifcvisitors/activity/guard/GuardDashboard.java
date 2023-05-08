package com.laxmi.lifcvisitors.activity.guard;

import static com.laxmi.lifcvisitors.fragments.languageconvert.LocaleManager.setNewLocale;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.laxmi.lifcvisitors.Contactus;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.EmployeeDashboard;
import com.laxmi.lifcvisitors.activity.employee.EmployeeLogin;
import com.laxmi.lifcvisitors.fragments.GuardDashboardFragment;
import com.laxmi.lifcvisitors.fragments.languageconvert.BaseActivity;
import com.laxmi.lifcvisitors.fragments.languageconvert.LocaleManager;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

public class GuardDashboard extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    PrefConfig prefConfig;
    AlertDialog.Builder alertDialogBuilder;

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
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        View headerView = navigationView.inflateHeaderView(R.layout.gaurd_header);
        TextView tvGuardName = headerView.findViewById(R.id.tv_guard_name);
        TextView tvGuardEmail = headerView.findViewById(R.id.tv_guard_email);
        tvGuardName.setText(prefConfig.readName()+"");
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Logout");
        alertDialogBuilder.setIcon(R.drawable.logout);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(GuardDashboard.this, GaurdLogin.class));
                Toast.makeText(GuardDashboard.this, "You successfully logout", Toast.LENGTH_SHORT).show();
                finish();   }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getApplicationContext();


            }
        });


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

//        Fragment fragment = null;
//        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_slideshow) {
        }
      /*  else if (id == R.id.nav_guarddesignation) {

        }*/
        else if (id == R.id.nav_guardContactus) {
            Intent intents = new Intent(GuardDashboard.this, Contactus.class);
            startActivity(intents);
            
        } else if (id== R.id.nav_guardprofile) {
            Intent intent= new Intent(GuardDashboard.this,GuardProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_guardlanguage) {

                final Dialog dialogLanguages =
                        new Dialog(GuardDashboard.this, android.R.style.Theme_DeviceDefault);
                dialogLanguages.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialogLanguages.setCancelable(true);
                dialogLanguages.setContentView(R.layout.dialog_languages);

                dialogLanguages.show();

                final RadioButton rbEnglish = dialogLanguages.findViewById(R.id.rb_english);
                final RadioButton rbHindi = dialogLanguages.findViewById(R.id.rb_hindi);
                final RadioButton rbGujarati = dialogLanguages.findViewById(R.id.rb_gujarati);

                rbEnglish.setOnClickListener(view1 -> {
                    dialogLanguages.dismiss();
                    setNewLocale(GuardDashboard.this, LocaleManager.ENGLISH);

                    //Finish All Activity and return to main Screen or wherever you want
                    Intent intent1 = new Intent(GuardDashboard.this, GuardDashboard.class);
                    startActivity(intent1);
                    finishAffinity();
                });

                rbHindi.setOnClickListener(view12 -> {
                    dialogLanguages.dismiss();
                    setNewLocale(GuardDashboard.this, LocaleManager.HINDI);

                    Intent intent2 = new Intent(GuardDashboard.this, GuardDashboard.class);
                    startActivity(intent2);
                    finishAffinity();
                });
                rbGujarati.setOnClickListener(view13 -> {
                    dialogLanguages.dismiss();
                    setNewLocale(GuardDashboard.this, LocaleManager.GUJARATI);

                    Intent intent3 = new Intent(GuardDashboard.this, GuardDashboard.class);
                    startActivity(intent3);
                    finishAffinity();
                });




        }else if (id == R.id.nav_guardlogout) {

            alertDialogBuilder.show();
            prefConfig.writeLoginStatus(false);
        //    startActivity(new Intent(GuardDashboard.this,GaurdLogin.class));
          //  finishAffinity();

       }
      //  fragmentManager.beginTransaction().replace(R.id.frameLayoutguard, fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout_guard);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}