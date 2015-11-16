package camlebell.com.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.fragment.DrawLeftFragment;
import camlebell.com.myapplication.R;


public class HomeActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener,
        DrawLeftFragment.OnLeftFragmentInteractionListener {
    private FrameLayout vLeftLayout;
    private FrameLayout vHomeLayout;
    private DrawerLayout vMainDrawerLayout;
    private FragmentManager fragmentManager;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void findViews() {
        vMainDrawerLayout = findView(R.id.main_drawer_layout);
    }

    @Override
    protected void iniData() {

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu_nav_n);
        setTitle(R.string.home_title);

        DrawHomeFragment homeFragment = DrawHomeFragment.newInstance("", "");
        DrawLeftFragment leftFragment = DrawLeftFragment.newInstance("", "");
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_container_layout, homeFragment);
        fragmentTransaction.replace(R.id.left_container_layout, leftFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void setListener() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                vMainDrawerLayout,         /* DrawerLayout object */
                vToolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.title_activity_home,  /* "open drawer" description for accessibility */
                R.string.title_activity_home  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
//                setTitle("标题");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
//                setTitle("标题");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        vMainDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public void onLeftFragmentInteraction(Uri uri) {

    }

    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }

    //    @Override
    public void clickBackButtonListener() {
        Toast.makeText(this, "test", 1).show();
    }
}
