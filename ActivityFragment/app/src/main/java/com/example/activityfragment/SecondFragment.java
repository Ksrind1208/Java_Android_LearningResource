package com.example.activityfragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecondFragment extends Fragment {
    ConstraintLayout constraintLayout;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        btn=view.findViewById(R.id.button2);
        constraintLayout=view.findViewById(R.id.second_fragment_layout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}