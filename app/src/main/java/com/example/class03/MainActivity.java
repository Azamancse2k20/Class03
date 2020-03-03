package com.example.class03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandListAddapter expandListAddapter;
    private ExpandableListView expandableListView;
    List<MenuModel> menuModelList =new ArrayList<>();
    HashMap<MenuModel,List<MenuModel>> childlist = new HashMap<>();

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private HomeFragment homeFragment;
    private InboxFragment inboxFragment;
    private LocationFrament locationFrament;
    private PhoneFragment phoneFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationView = findViewById(R.id.navId);
        bottomNavigationView = findViewById(R.id.bottomNavId);
        bottomNavigationView.setSelectedItemId(R.id.homeId);
        expandableListView = findViewById(R.id.expanded_ListId);
        populateExpadableList();
        prepareMenu();


        frameLayout = findViewById(R.id.fremeId);
        homeFragment = new HomeFragment();
             setFragment(homeFragment);
        inboxFragment = new InboxFragment();
        locationFrament = new LocationFrament();
        phoneFragment = new PhoneFragment();

        toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.inboxId:
                        setFragment(inboxFragment);
                            return true;

                    case R.id.homeId:
                        setFragment(homeFragment);
                            return true;

                    case R.id.locationId:
                        setFragment(locationFrament);
                            return true;

                    case R.id.phoneId:
                        setFragment(phoneFragment);
                        return false;
                }

                return false;
            }
        });


//        BottomNavigationHelper.disableShiftMode(bottomNavigationView);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open
                , R.string.navigation_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.settingsId){

                    Toast.makeText(MainActivity.this, "Settings is clicked", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.shareId){
                    Toast.makeText(MainActivity.this, "Share is clicked", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(drawerLayout,"Share",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else if (id == R.id.logoutId){
                    Toast.makeText(MainActivity.this, "Logout is clicked", Toast.LENGTH_SHORT).show();
                }


                return false;
            }
        });

    }

    private void populateExpadableList() {
        //menuModeList, childList

      expandListAddapter = new ExpandListAddapter(this,menuModelList,childlist);
      expandableListView.setAdapter(expandListAddapter);

      expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
          @Override
          public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

             if (menuModelList.get(groupPosition).isGroup){
                 if (!menuModelList.get(groupPosition).hasChild){


                 }
             }


              return false;
          }
      });
    }

    private void prepareMenu() {
        MenuModel menuModel = new MenuModel("test",true,true);
        menuModelList.add(menuModel);
        List<MenuModel> childModeList = new ArrayList<>();
        MenuModel childItem = new MenuModel( "Item",false,false);
        MenuModel childItem2 = new MenuModel( "Item2",false,false);
        childModeList.add(childItem);
        childModeList.add(childItem2);

        if (menuModel.hasChild){
            childlist.put(menuModel,childModeList);
        }

    }

    private void setFragment(Fragment fragment ){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
//        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        fragmentTransaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        fragmentTransaction.replace(R.id.fremeId,fragment);
        fragmentTransaction.commit();

    }
}
