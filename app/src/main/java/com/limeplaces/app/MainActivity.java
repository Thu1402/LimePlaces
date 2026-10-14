package com.limeplaces.app;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.screens.AddPlaceFragment;
import com.limeplaces.app.screens.DetailFragment;
import com.limeplaces.app.screens.FriendsFragment;
import com.limeplaces.app.screens.HomeFragment;
import com.limeplaces.app.screens.MapFragment;
import com.limeplaces.app.screens.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) showMain(new HomeFragment());
            else if (id == R.id.nav_map) showMain(new MapFragment());
            else if (id == R.id.nav_add) showMain(new AddPlaceFragment());
            else if (id == R.id.nav_friends) showMain(new FriendsFragment());
            else if (id == R.id.nav_profile) showMain(new ProfileFragment());
            return true;
        });
        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.nav_home);
        }
    }

    private void showMain(Fragment fragment) {
        navigation.setVisibility(View.VISIBLE);
        findViewById(R.id.content).setPadding(0, 0, 0, 0);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.content, fragment)
            .commit();
    }

    public void showDetail(int placeIndex) {
        navigation.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.content, DetailFragment.newInstance(placeIndex))
            .addToBackStack("detail")
            .commit();
    }

    public void openAddPlace() {
        navigation.setSelectedItemId(R.id.nav_add);
    }

    public void returnHome() {
        navigation.setSelectedItemId(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            navigation.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
