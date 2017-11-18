package com.when.threemb.cinepolis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Description extends AppCompatActivity {
    TextView title, realease, description,book;
    ImageView cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        title = (TextView)findViewById(R.id.tv_title);
        realease =(TextView) findViewById(R.id.tv_realease);
        description = (TextView)findViewById(R.id.tv_description);
        cover = (ImageView) findViewById(R.id.im_cover);
        book =(TextView) findViewById(R.id.tv_book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Description.this,Confirmation.class));
            }
        });

        /*
            {
                title: 'thor',
                release: '30/5/2017'
                description: ' Aman says its a great movie'

            }


         */

        // After json parsing

        description.setText("Imprisoned on the other side of the universe, the mighty Thor finds himself in a deadly gladiatorial contest that pits him against the Hulk, his former ally and fellow Avenger. Thor's quest for survival leads him in a race against time to prevent the all-powerful Hela from destroying his home world and the Asgardian civilization.");
        realease.setText("30/5/2017");


    }
}
