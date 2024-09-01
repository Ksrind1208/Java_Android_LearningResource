package com.example.activityfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstFragment extends Fragment {
    TextView textView;
    Button btn;
    ConstraintLayout constraintLayout;
    @Override   
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);
        btn=view.findViewById(R.id.button2);
        textView=view.findViewById(R.id.textView3);

        constraintLayout=view.findViewById(R.id.first_fragment_layout);
        Bundle data=getArguments();
        if(data!=null){
            textView.setText(data.getString("dataFromActivity"));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new SecondFragment()).addToBackStack(null).commit();

            }
        });

        return view;
    }
}