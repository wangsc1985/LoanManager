package com.wangsc.loanmanager.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wangsc.loanmanager.R;
import com.wangsc.loanmanager.activity.AddLoanActivity;
import com.wangsc.loanmanager.activity.LeftNavigationActivity;
import com.wangsc.loanmanager.activity.ScrollingActivity;
import com.wangsc.loanmanager.helper._Helper;

import java.util.UUID;

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
                    Intent intent = new Intent(getActivity(), AddLoanActivity.class);
                    intent.putExtra(AddLoanActivity.PARAM_LOAN_GROUP_ID, UUID.randomUUID().toString());
                    startActivity(intent);
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

            Button buttonScrolling = (Button)view.findViewById(R.id.button_scrolling);
            buttonScrolling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ScrollingActivity.class));
                }
            });
        } catch (Exception e) {
            _Helper.printException(getContext(),e);
        }

        return view;
    }

}
