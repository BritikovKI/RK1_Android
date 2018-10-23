package com.example.chapay.rk;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final ConstraintLayout view =(ConstraintLayout) inflater.inflate(R.layout.fragment_number,container,false);
        final Bundle bundle = getArguments();

        TextView num = view.findViewById(R.id.num);
        String n = bundle.getString("num");
        num.setText(n);
        if(Integer.parseInt(n)%2==1){
                num.setTextColor(0xFFF06D2F);
        }
        return view;
    }
}
