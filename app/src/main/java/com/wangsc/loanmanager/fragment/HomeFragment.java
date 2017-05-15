package com.wangsc.loanmanager.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wangsc.loanmanager.R;
import com.wangsc.loanmanager.activity.AddLoanActivity;
import com.wangsc.loanmanager.activity.BasicActivity;
import com.wangsc.loanmanager.activity.LeftNavigationActivity;
import com.wangsc.loanmanager.activity.LoginActivity;
import com.wangsc.loanmanager.activity.MapsActivity;
import com.wangsc.loanmanager.activity.ScrollingActivity;
import com.wangsc.loanmanager.activity.TabbedActivity;
import com.wangsc.loanmanager.helper._Helper;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        try {
            /**
             *
             */
            FloatingActionButton fabCreate= (FloatingActionButton)view.findViewById(R.id.fab_create);
            fabCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), AddLoanActivity.class));
                }
            });
            Button buttonBasic = (Button)view.findViewById(R.id.button_basic);
            buttonBasic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), BasicActivity.class));
                }
            });

            Button buttonCreate = (Button)view.findViewById(R.id.button_create);
            buttonCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                    } catch (Exception e) {
                        _Helper.printException(getContext(),e);
                    }
                }
            });

            Button buttonNavigation = (Button)view.findViewById(R.id.button_navigation);
            buttonNavigation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LeftNavigationActivity.class));
                }
            });

            Button buttonMaps = (Button)view.findViewById(R.id.button_maps);
            buttonMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), MapsActivity.class));
                }
            });

            Button buttonScrolling = (Button)view.findViewById(R.id.button_scrolling);
            buttonScrolling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ScrollingActivity.class));
                }
            });

            Button buttonTabbed = (Button)view.findViewById(R.id.button_tabbed);
            buttonTabbed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), TabbedActivity.class));
                }
            });
        } catch (Exception e) {
            _Helper.printException(getContext(),e);
        }

        return view;
    }

}
