package com.lin.android.hotelmenus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.lin.android.hotelmenus.bottomnav.MeowBottomNavigation;
import com.lin.android.hotelmenus.ui.cart.CartFragment;
import com.lin.android.hotelmenus.ui.home.HomeFragment;
import com.lin.android.hotelmenus.ui.settings.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.meow_bottom_view);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_add_circle_outline_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_settings_black_24dp));
        bottomNavigation.setOnBottomNavigationItemClickListener(new MeowBottomNavigation.IBottomNavigationItemClickListener() {
            @Override
            public void mOnBottomNavigationItemClickedListener(@NonNull MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        onNotify(1, getString(R.string.title_home));
                        break;
                    case 2:
                        onNotify(2, getString(R.string.title_cart));
                        break;
                    case 3:
                        onNotify(3, getString(R.string.title_settings));
                        break;
                    default:
                        break;
                }
            }
        });

        onNotify(1, getString(R.string.title_home));
        bottomNavigation.show(1, true);
    }

    private void onNotify(int notifyId, String notifications) {
        switch (notifyId) {
            case 1:
                loadHomeFragment(notifyId, notifications);
                break;
            case 2:
                loadCartFragment(notifyId, notifications);
                break;
            case 3:
                loadSettingsFragment(notifyId, notifications);
                break;
            default:
                break;
        }
    }

    private void loadHomeFragment(int notifyId, String notificationsTag) {
        if (!(getCurrentFragment()
                instanceof HomeFragment)) {
            Bundle bundle = new Bundle();
            HomeFragment homeFragment = HomeFragment.newInstance(bundle);
            //callFragmentHandler(homeFragment, notifyId, notifications, false);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            String tag = notifyId + "$" + notificationsTag;
            transaction.replace(R.id.nav_host_fragment, homeFragment, tag);
            transaction.commit();
        }
    }

    private void loadCartFragment(int notifyId, String notificationsTag) {
        if (!(getCurrentFragment()
                instanceof CartFragment)) {
            Bundle bundle = new Bundle();
            CartFragment cartFragment = CartFragment.newInstance(bundle);
            //callFragmentHandler(cartFragment, notifyId, notifications, false);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            String tag = notifyId + "$" + notificationsTag;
            transaction.replace(R.id.nav_host_fragment, cartFragment, tag);
            transaction.commit();
        }
    }

    private void loadSettingsFragment(int notifyId, String notificationsTag) {
        if (!(getCurrentFragment()
                instanceof SettingsFragment)) {
            Bundle bundle = new Bundle();
            SettingsFragment settingsFragment = SettingsFragment.newInstance(bundle);
            //callFragmentHandler(settingsFragment, notifyId, notifications, false);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            String tag = notifyId + "$" + notificationsTag;
            transaction.replace(R.id.nav_host_fragment, settingsFragment, tag);
            transaction.commit();
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    }

    /*private void callFragmentHandler(Fragment fragment, int notifyId, String notificationsTag,
                                     boolean needToAddBackStack) {

        //to handle fragment in backpressed this type of tag created.
        String tag = notifyId + "$" + notificationsTag;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(tag, 0);

        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.nav_host_fragment, fragment, tag);
            if (needToAddBackStack) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }
    }*/
}
