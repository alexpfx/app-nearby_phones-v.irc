package br.com.alexpfx.irctest.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import br.com.alexpfx.irctest.app.fragments.NetworkSettingsFragment;
import br.com.alexpfx.irctest.app.fragments.HomeFragment;
import br.com.alexpfx.irctest.app.fragments.IdentitySettingsFragment;
import br.com.alexpfx.irctest.app.fragments.OtherSettingsFragment;
import br.com.alexpfx.irctest.app.utils.TagUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @Bind(R.id.drawer_layout)
//    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

//    @Bind(R.id.navigation_view)
//    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupDrawerContent();
    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent() {
//        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedHandle(getSupportFragmentManager()));

    }

    class NavigationItemSelectedHandle implements NavigationView.OnNavigationItemSelectedListener {
        private FragmentManager fragmentManager;

        public NavigationItemSelectedHandle(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
//            drawerLayout.closeDrawers();
            handleSelections(menuItem);
            return true;
        }

        private int handleSelections(MenuItem menuItem) {
            Log.i(TagUtils.tag(), String.valueOf(menuItem.getItemId()));
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    HomeFragment hf = new HomeFragment();
                    return changeFragment(hf);
                case R.id.nav_identity:
                    IdentitySettingsFragment isf = new IdentitySettingsFragment();
                    return changeFragment(isf);
                case R.id.nav_network:
                    NetworkSettingsFragment csf = new NetworkSettingsFragment();
                    return changeFragment(csf);
                case R.id.nav_settings:
                    OtherSettingsFragment ocs = new OtherSettingsFragment();
                    return changeFragment(ocs);
                default:
                    throw new IllegalArgumentException("Fragmento nao existe");
            }
        }

        private int changeFragment(Fragment fragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            return fragmentTransaction.commit();
        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @OnClick(R.id.fab)
    public void onFabButtonClick(View view) {

        final Snackbar bar = Snackbar.make(findViewById(R.id.action_bar_root), "Connected", Snackbar.LENGTH_LONG);
        bar.show();

    }


}
