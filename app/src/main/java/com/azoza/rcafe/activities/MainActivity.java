package com.azoza.rcafe.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.azoza.rcafe.R;
import com.azoza.rcafe.fragments.HomeFragment;
import com.azoza.rcafe.model.User;
import com.azoza.rcafe.utill.DbHelper;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView sidenavigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    Toolbar toolbar2;
    private TextView nameView, emailView;

    Fragment homeFragment;
    User user, user2;

    FirebaseAuth auth;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar2 = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        auth = FirebaseAuth.getInstance();

        dbHelper = new DbHelper(getApplicationContext());
        user = new User();
        List<User> allUser = dbHelper.getAllUsers();
        int size = allUser.size();
        user2 = allUser.get(size - 1);
        //loadIcons();
//        int currentUserId =user.getId();

//
//        user2 = allUser.get(currentUserId);

        homeFragment = new HomeFragment();
        loadFragment(homeFragment);

        drawerLayout = findViewById(R.id.drawerLayout);
        sidenavigationView = findViewById(R.id.sideNavView);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,
                R.string.nav_open,
                R.string.nav_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadText();
                drawerLayout.open();

            }
        });

    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        } else if (id == R.id.menu_my_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }

//        View navHome = findViewById(R.id.sideNavHomes);
//        navHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                System.out.println("Home");
//                if (item.getItemId() == R.id.sideNavHomes) {
//                    loadFragment(new HomeFragment());
//                }
//            }
//        });


        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        }else if (id == R.id.menu_my_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }
        return true;
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

    public void loadText() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                nameView = findViewById(R.id.user_Names);
                emailView = findViewById(R.id.user_Email);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nameView.setText(String.valueOf(auth.getCurrentUser().getDisplayName()));
                        emailView.setText(String.valueOf(auth.getCurrentUser().getEmail()));

                    }
                });
            }
        }).start();
    }

    public void loadIcons() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MenuItem wishlist = findViewById(R.id.sideNavCart);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wishlist.setVisible(true);
                    }
                });


            }
        }).start();
    }
}