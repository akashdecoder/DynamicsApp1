package com.example.dynamicsapp1;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynamicsapp1.ui.gallery.GalleryFragment;
import com.example.dynamicsapp1.ui.home.HomeFragment;
import com.example.dynamicsapp1.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    TextView userText;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static final String COMMON_TAG = "Orientation Change";


    /*Commented for reference use */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.blankFragment, R.id.uploads, R.id.about, R.id.contact)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                return true;
//            }
//        });
//        upDateHeader();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/email");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"akash.ranjan1999@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Add your subject");
                email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Dynamics"+"");
                startActivity(Intent.createChooser(email, "Send Feedback:"));

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//        upDateHeader();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new HomeFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_gallery:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new GalleryFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_slideshow:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new SlideshowFragment()).addToBackStack(null).commit();
                break;
            case R.id.blankFragment:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new BlankFragment()).addToBackStack(null).commit();
                break;
            case R.id.uploads:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new Uploads()).addToBackStack(null).commit();
                break;
            case R.id.about:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new About()).addToBackStack(null).commit();
                break;
            case R.id.contact:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new contactus()).addToBackStack(null).commit();
                break;
//            case R.id.logout:
//                logout();
//                break;
            default:
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void upDateHeader(){
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        View headerView = navigationView.getHeaderView(0);
//        userText = headerView.findViewById(R.id.userText);
//        userText.setText(currentUser.getEmail());
//    }

//    private void logout(){
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(COMMON_TAG, "landscape");
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(COMMON_TAG, "potrait");
        }
    }
}