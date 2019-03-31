package com.example.khaledsabry.Client;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byox.drawview.views.DrawView;


public class DrawingFragment extends Fragment {
    DrawView drawView;

    public static DrawingFragment newInstance() {
        DrawingFragment fragment = new DrawingFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawing, container, false);
        //drawView = view.findViewById(R.id.draw_view);

        return view;
    }

}
