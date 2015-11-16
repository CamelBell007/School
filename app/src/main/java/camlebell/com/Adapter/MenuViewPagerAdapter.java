package camlebell.com.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import camlebell.com.fragment.AllMenusFragment;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.fragment.PublishDinnerMenusFragment;
import camlebell.com.fragment.WeakMenusFragment;
import camlebell.com.myapplication.R;

/**
 * Created by sunyan on 2015/11/6.
 */
public class MenuViewPagerAdapter extends FragmentPagerAdapter {
    public AllMenusFragment allMenusFragment;
    public WeakMenusFragment weakMenusFragment;
    public PublishDinnerMenusFragment publishDinnerMenusFragment;

    public MenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private final String[] titles = { "游记", "专题", "目的地" };

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (allMenusFragment == null) {
                    allMenusFragment = new AllMenusFragment();
                }
                return allMenusFragment;
            case 1:
                if (weakMenusFragment == null) {
                    weakMenusFragment = new WeakMenusFragment();
                }
                return weakMenusFragment;
            case 2:
                if (publishDinnerMenusFragment == null) {
                    publishDinnerMenusFragment = new PublishDinnerMenusFragment();
                }
                return publishDinnerMenusFragment;
            default:
                return null;
        }
    }



}
