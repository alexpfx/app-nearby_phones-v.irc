package br.com.alexpfx.irctest.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import br.com.alexpfx.irctest.app.fragments.ConnectionSettingsFragment;
import br.com.alexpfx.irctest.app.fragments.IdentitySettingsFragment;
import br.com.alexpfx.irctest.app.fragments.OtherSettingsFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        final ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        setupDrawerContent();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedHandle(getSupportFragmentManager()));

    }

    class NavigationItemSelectedHandle implements NavigationView.OnNavigationItemSelectedListener {
        private FragmentManager fragmentManager;

        public NavigationItemSelectedHandle(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            handleSelections(menuItem);
            return true;
        }

        private void handleSelections(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:

                    ;
                case R.id.nav_identity:
                    IdentitySettingsFragment isf = new IdentitySettingsFragment();
                    changeFragment(isf);
                case R.id.nav_network:
                    ConnectionSettingsFragment csf = new ConnectionSettingsFragment();
                    changeFragment(csf);
                case R.id.nav_settings:
                    OtherSettingsFragment ocs = new OtherSettingsFragment();
                    changeFragment(ocs);
            }
        }

        private void changeFragment(Fragment fragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
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

}
