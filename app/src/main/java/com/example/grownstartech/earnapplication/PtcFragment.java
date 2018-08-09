package com.example.grownstartech.earnapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PtcFragment extends Fragment {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    public PtcFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ptc, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutPtc);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerPtc);

        ViewPagerAdapterPtc adapter = new ViewPagerAdapterPtc(getChildFragmentManager());

        adapter.AddFragment(new TwentySecPtcFragment(), "20 Sec");
        adapter.AddFragment(new ThirtySecPtcFragment(), "30 Sec");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("");

    }




}
