package app.the.iway.codersinc.recommendation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IWAY on 27-11-2016.
 */


    public class TabspagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public TabspagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:
                    return new AllProblemsFragment();
                case 1:
                    return new MyProblemsFragment();
                case 2:
                    return new TodolistFragment();
            }

            return new MyProblemsFragment();
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return 3;
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

