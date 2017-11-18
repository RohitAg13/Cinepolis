package com.when.threemb.cinepolis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Transaction extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    protected BottomNavigationView navigation;
    String URl ="https://5b2cadbc.ngrok.io/tickets?username=rohit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        ListView lv = (ListView)findViewById(R.id.listview);

        String[] values= new String[]{
                "1st transaction",
                "2nd transaction"
        };
        List <String> list = new ArrayList<String>(Arrays.asList(values));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
        // we can add item as list.add("  ");
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        navigation.postDelayed(new Runnable() {
            @Override
            public void run() {

                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                     startActivity(new Intent(Transaction.this, Profile.class));
                } else if (itemId == R.id.navigation_dashboard) {
                      startActivity(new Intent(Transaction.this, Dashboard.class));
                } else if (itemId == R.id.navigation_notifications) {

                }
                finish();
            }
        }, 300);

        return true;
    }
    public void retrieveData(){
        
    }
}
