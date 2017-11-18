package com.when.threemb.cinepolis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    protected BottomNavigationView navigation;
    private  AlbumAdadpter adadpter;
    private List<Album> albumList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adadpter = new AlbumAdadpter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),0);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adadpter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.theater).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        private void prepareAlbums() {
            int[] covers = new int[]{
                    R.drawable.thor,
                    R.drawable.ribbon,
            };
            Album a = new Album("Thor Ragnarok", covers[0]);
            albumList.add(a);
            a = new Album("Ribbon",covers[1]);
            albumList.add(a);
            adadpter.notifyDataSetChanged();
        }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Dashboard.this, R.color.colorPrimaryDark));
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    toolbar.setBackgroundColor(ContextCompat.getColor(Dashboard.this, R.color.alpha));
                    isShow = false;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }
    private void updateNavigationBarState(){
        int actionId = R.id.navigation_dashboard;
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigation.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        navigation.postDelayed(new Runnable() {
            @Override
            public void run() {

                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    startActivity(new Intent(Dashboard.this, Profile.class));
                } else if (itemId == R.id.navigation_dashboard) {
                    //  startActivity(new Intent(this, DashboardActivity.class));
                } else if (itemId == R.id.navigation_notifications) {
                    startActivity(new Intent(Dashboard.this, Transaction.class));
                }
                finish();
            }
            }, 300);

        return true;
    }
}
