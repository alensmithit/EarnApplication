package com.example.grownstartech.earnapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.grownstartech.earnapplication.transactions.TransListAdapter;


public class MenuFragment extends Fragment {

    boolean isExpandTrans;






    public MenuFragment() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);



        ImageView expandTrans = (ImageView) view.findViewById(R.id.expandTransView);
        final LinearLayout showTrans =(LinearLayout) view.findViewById(R.id.show_trans_layout);

        expandTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showTrans.getVisibility()==View.VISIBLE) {

                    showTrans.setVisibility(View.GONE);

                    isExpandTrans = false;
                }
                else if(showTrans.getVisibility()==View.GONE){

                    showTrans.setVisibility(View.VISIBLE);
                    isExpandTrans=true;
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ListRecyclerView);

        TransListAdapter listAdapter = new TransListAdapter();

        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }




}
