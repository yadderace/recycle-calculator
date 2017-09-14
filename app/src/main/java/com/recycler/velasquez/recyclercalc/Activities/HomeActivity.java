package com.recycler.velasquez.recyclercalc.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.recycler.velasquez.recyclercalc.Activities.Dialogs.NewProductDialog;
import com.recycler.velasquez.recyclercalc.Activities.Fragments.BuyFragment;
import com.recycler.velasquez.recyclercalc.Activities.Fragments.ProductsFragment;
import com.recycler.velasquez.recyclercalc.Activities.Fragments.RatesFragment;
import com.recycler.velasquez.recyclercalc.Adapters.ViewPagerAdapter;
import com.recycler.velasquez.recyclercalc.R;

public class HomeActivity extends AppCompatActivity{

    AppCompatActivity           activity;

    ViewPager                   viewPager;
    TabLayout                   tabLayout;
    FloatingActionButton        floatingbutton_new_product;

    private static final int IDX_BUYS_FRAGMENT = 0;
    private static final int IDX_RATES_FRAGMENT = 1;
    private static final int IDX_PRODUCTS_FRAGMENT = 2;

    private int[] tabIcons = {
            R.drawable.ic_buys,
            R.drawable.ic_rates,
            R.drawable.ic_products
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingbutton_new_product  = (FloatingActionButton) findViewById(R.id.floatingbutton_new_product);

        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        floatingbutton_new_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idxCurrentFragment = viewPager.getCurrentItem();
                if(idxCurrentFragment == IDX_PRODUCTS_FRAGMENT)
                    openNewProductDialog();
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
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


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BuyFragment(), getResources().getString(R.string.buys));
        adapter.addFragment(new RatesFragment(), getResources().getString(R.string.rates));
        adapter.addFragment(new ProductsFragment(), getResources().getString(R.string.products));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void openNewProductDialog(){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        NewProductDialog newFragment = new NewProductDialog();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).commit();
    }


}
