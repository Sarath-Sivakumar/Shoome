package com.market.shoome.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.market.shoome.R;
import com.market.shoome.ui.account.Account;
import com.market.shoome.ui.cart.cartActivity;
import com.market.shoome.ui.categories.categoriesActivity;
import com.market.shoome.ui.orders.Orders;
import com.market.shoome.ui.whishList.wishList;

public class homeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.accBtn) {
                startActivity(new Intent(homeActivity.this, Account.class));
                Toast.makeText(this, "What the fuck", Toast.LENGTH_SHORT).show();
                Log.e("btn", "Clicked");
            } else if (item.getItemId() == R.id.shopByCat) {
                startActivity(new Intent(homeActivity.this, categoriesActivity.class));
            } else if (item.getItemId() == R.id.offAndRew) {
                //startActivity(new Intent(homeActivity.this, ));
                Snackbar.make(drawerLayout, "Under construction..", Snackbar.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.orders) {
                startActivity(new Intent(homeActivity.this, Orders.class));
            } else if (item.getItemId() == R.id.wishList) {
                startActivity(new Intent(homeActivity.this, wishList.class));
            } else if (item.getItemId() == R.id.cart) {
                startActivity(new Intent(homeActivity.this, cartActivity.class));
            } else {
                Toast.makeText(this, "What the fuck", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}