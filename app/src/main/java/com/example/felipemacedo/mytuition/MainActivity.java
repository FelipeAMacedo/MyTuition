package com.example.felipemacedo.mytuition;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private BottomNavigationView navigation;
    private ViewPager mViewPager;
    private Fragment[] fragments = {new HomeFragment(), new DisciplinasFragment(), new AchievementsFragment()};

    private ImageButton ibtnHome;
    private ImageButton ibtnLicoes;
    private ImageButton ibtnAchievements;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(new HomeFragment());
                    return true;
                case R.id.navigation_train:
                    switchFragment(new DisciplinasFragment());
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(MainActivity.this, "Notification taped", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        initListeners();

        switchFragment(new HomeFragment());
    }

    private void initComponents() {
        ibtnHome = (ImageButton) findViewById(R.id.ibtnHome);
        ibtnLicoes = (ImageButton) findViewById(R.id.ibtnLicoes);
        ibtnAchievements = (ImageButton) findViewById(R.id.ibtnAchievements);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mViewPager = (ViewPager) findViewById(R.id.vpMain);

        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
    }

    private void initListeners() {

        ibtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });

        ibtnLicoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });

        ibtnAchievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
            }
        });
    }

    private class MainAdapter extends FragmentStatePagerAdapter {
        private final int NUM_PAGES = 3;

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_home, fragment, fragment.getTag()).commit();
    }
}
