package com.example.unica.pruebaandroidnapps.gui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.gui.Actividades.ActividadesFragment;
import com.example.unica.pruebaandroidnapps.gui.Negocios.NegociosFragment;
import com.example.unica.pruebaandroidnapps.gui.Organizaciones.OrganizacionesFragment;
import com.example.unica.pruebaandroidnapps.gui.Personas.PersonasFragment;

public class CrmActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.openDrawer(Gravity.START);

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        String tag = null;
        Fragment fragment = null;
        String title = "";

        if (id == R.id.nav_home) {
            tag = "homeFragment";
            fragment = new HomeFragment();
            title = "Home";
        } else if (id == R.id.nav_personas) {
            tag = "personasFragment";
            fragment = new PersonasFragment();
            title = "Personas";
        } else if (id == R.id.nav_organizaciones) {
            tag = "organizacionesFragment";
            fragment = new OrganizacionesFragment();
            title = "Organización";
        } else if (id == R.id.nav_negocios) {
            tag = "negociosFragment";
            fragment = new NegociosFragment();
            title = "Negocio";
        } else if (id == R.id.nav_actividades) {
            tag = "actividadesFragment";
            fragment = new ActividadesFragment();
            title = "Actividades";
        }

        if(fragment != null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, tag).commit();
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
