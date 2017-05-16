package com.example.deyvi.juanfutbol;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.deyvi.juanfutbol.adapter.DeporteAdapter;
import com.example.deyvi.juanfutbol.pojo.Evento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private boolean notificaciones = false ;
    private  final  String URL_TO_HIT="http://mobile.unam.mx/app2016/deporte/deporte.json";
    private Activity activity;
    public ArrayList<Evento> eventos;
    public LinearLayoutManager llm;
    public RecyclerView rvDeporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventos = new ArrayList<>();



        new JsonTask().execute(URL_TO_HIT);
        activity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_notification) {

            AlertDialog.Builder builder= new AlertDialog.Builder(activity);
            builder.setMessage(R.string.notifica).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //hay que agregar las firebase Cloud_Notification

                    Toast.makeText(activity, "hola", Toast.LENGTH_SHORT).show();

                }
                //para la forma negativa
            }).setNegativeButton(R.string.cancelar, null);

            AlertDialog alert = builder.create();
            alert.show();

        } else if (id == R.id.nav_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setMessage(R.string.cerrar).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(activity, getResources().getString(R.string.cerrar_despues), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }).setNegativeButton(R.string.cancelar, null);

            AlertDialog alert =  builder.create();
            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public   class JsonTask extends AsyncTask<String, Void, String>{



        @Override
            protected String doInBackground(String... params) {
            StringBuffer buffer = new StringBuffer();
            try {
                URL datosFeed = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(datosFeed.openStream()));
                String l = "";
                while ((l = br.readLine()) != null) {
                    buffer.append(l);
                }
                br.close();
            } catch(MalformedURLException mue) {
                mue.printStackTrace();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray  jsonArray  = jsonObject.getJSONArray("Deporte");
                jsonObject = jsonArray.getJSONObject(0);
                jsonArray = jsonObject.getJSONArray("events");

                for(int i= 0; i <= jsonArray.length(); i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    //parseo de cada parte de nuestro objeto
                    String name     = object.getString("name");
                    String category = object.getString("category");
                    String type     = object.getString("public");
                    String video    = (object.has("video"))? object.getString("video"): "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

                    //Para la fecha del evento
                    long dateUnix = object.getLong("dateUnix");

                    String image = object.getString("image");
                    String place = object.getString("place");
                    String description = object.getString("description");

                    /**Al json parceado le pedimos el objeto en la posicion 0 al cual le pedimos el Double
                     * donde estan almacenadas nustra latitude y longitude.*/

                    //Double lat = object.getJSONArray("location").getJSONObject(0).getDouble("latitude");
                    //Double lon = object.getJSONArray("location").getJSONObject(0).getDouble("longitude");
                    JSONObject ubicacion = object.getJSONArray("location").getJSONObject(0);

                    Double lat  = (ubicacion.has("latitude"))? ubicacion.getDouble("latitude"): ubicacion.getDouble("latitude:");
                    Double lon  = (ubicacion.has("longitude"))? ubicacion.getDouble("longitude"):ubicacion.getDouble("longitude:");

                    eventos.add(new Evento(name, category, type, video, dateUnix,image,place,description,lat,lon));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //hay que buscar Nuestro recycler View
            rvDeporte = (RecyclerView) findViewById(R.id.rvDeporte);

            llm = new LinearLayoutManager(getApplicationContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rvDeporte.setLayoutManager(llm);

            //ahora hay que agregar nuestro adaptador
            DeporteAdapter deporteAdapter = new DeporteAdapter(activity,  eventos);
            rvDeporte.setAdapter(deporteAdapter);


        }


    }

}
