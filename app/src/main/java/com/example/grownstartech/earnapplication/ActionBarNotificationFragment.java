package com.example.grownstartech.earnapplication;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActionBarNotificationFragment extends Fragment {
    private TabLayout tabLayoutNotification;
    private AppBarLayout appBarLayoutNotification;
    private ViewPager viewPagerNotification;

    public ActionBarNotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_action_bar_notification, container, false);

        tabLayoutNotification = (TabLayout) view.findViewById(R.id.tabLayoutNotification);
        viewPagerNotification = (ViewPager) view.findViewById(R.id.viewPagerNotification);

        ViewPagerAdapterNotification adapter = new ViewPagerAdapterNotification(getChildFragmentManager());

        adapter.AddFragment(new SystemNotificationFragment(), "System");
        adapter.AddFragment(new ActionBtnTransFragment(), "Transactions");

        viewPagerNotification.setAdapter(adapter);

        tabLayoutNotification.setupWithViewPager(viewPagerNotification);

        return view;
    }

}
