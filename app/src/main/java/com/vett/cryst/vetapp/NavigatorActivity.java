package com.vett.cryst.vetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import Servicios.ServiceRecordatorios;
import layout.AnadirmascotaFragment;
import layout.CalendarioFragment;
import layout.ContactoFragment;
import layout.DesarrolladoFragment;
import layout.MascotaFragment;
import layout.MicalendarioFragment;
import layout.ScrollingFragment;
import layout.ServiciosFragment;
import layout.consejos_fragment;

public class NavigatorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,ContactoFragment.OnFragmentInteractionListener, DesarrolladoFragment.OnFragmentInteractionListener, MascotaFragment.OnFragmentInteractionListener, AnadirmascotaFragment.OnFragmentInteractionListener, consejos_fragment.OnFragmentInteractionListener, ScrollingFragment.OnFragmentInteractionListener, MicalendarioFragment.OnFragmentInteractionListener, CalendarioFragment.OnFragmentInteractionListener,ServiciosFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawer;
    TextView textViewCorreo;
    Toolbar toolbar;
    private FirebaseAuth auth;
    public static String correoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //AQUI LLAMO AL FRAGMENT DEL PRINCIPIO
        getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new ServiciosFragment()).commit();
        //Inicio el servicio
        Intent servicio = new Intent(NavigatorActivity.this, ServiceRecordatorios.class);
        startService(servicio);











        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //ESTO ES PARA SETEAR EL CORREO EN EL NAVIGATOR
        View header=navigationView.getHeaderView(0);
        textViewCorreo = (TextView) header.findViewById(R.id.textViewCorreo);
        auth= FirebaseAuth.getInstance();
        correoLogin= auth.getCurrentUser().getEmail().toString();
        textViewCorreo.setText(correoLogin);


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
        getMenuInflater().inflate(R.menu.navigator, menu);
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
            finishAffinity();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            auth.signOut();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Mi mascota
            toolbar.setTitle("Veterinaria Alcarria");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new MascotaFragment()).commit();
        } else if (id == R.id.nav_gallery) {
            //Servicios
            toolbar.setTitle("Servicios");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new ServiciosFragment()).commit();
        } else if (id == R.id.nav_slideshow) {
            //Consejo
            toolbar.setTitle("Consejos");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new consejos_fragment()).commit();
        } else if (id == R.id.nav_manage) {
            //este es el de contacto
            toolbar.setTitle("Veterinaria Alcarria");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new ContactoFragment()).commit();
        } else if (id == R.id.nav_share) {
            //este es el de llamar
            toolbar.setTitle("Veterinaria Alcarria");
            Intent callintent = new Intent(Intent.ACTION_DIAL);
            callintent.setData(Uri.parse("tel:910138302"));
            startActivity(callintent);
        } else if (id == R.id.nav_desarrollo) {
            //desarrollo
            toolbar.setTitle("Veterinaria Alcarria");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new DesarrolladoFragment()).commit();
        } else if (id == R.id.nav_calendario) {
            //calendario
            toolbar.setTitle("Recordatorios");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigator,new CalendarioFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
