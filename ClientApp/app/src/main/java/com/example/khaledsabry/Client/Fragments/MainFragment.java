package com.example.khaledsabry.Client.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.Client.DrawingFragment;
import com.example.khaledsabry.Client.Enums.Type;
import com.example.khaledsabry.Client.Functions.Functions;
import com.example.khaledsabry.Client.MainActivity;
import com.example.khaledsabry.Client.R;


public class MainFragment extends Fragment {

   public static DrawerLayout drawerLayout;
    NavigationView navigationView;
    public Type type = Type.HISTORY;
    int id;

    Bundle savedInstance;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("type", type);
        outState.putSerializable("index", id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            return;
        type = (Type) savedInstanceState.getSerializable("type");
        id = (int) savedInstanceState.getSerializable("index");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        drawerLayout = view.findViewById(R.id.drawer_layout_id);
        navigationView = view.findViewById(R.id.navigation_view_id);
        savedInstance = savedInstanceState;
        setNavigationView();
        return view;
    }

    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                id = item.getItemId();

                selectIndex(id);


                Functions.closeDrawerLayout(drawerLayout);
                return true;
            }
        });

        if (savedInstance == null)
            selectIndex(R.id.general_id);
    }

    void selectIndex(int id) {
        this.id = id;
        switch (id) {
            case R.id.general_id:
                type = Type.HISTORY;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, DrawingFragment.newInstance());
                break;
            case R.id.movie_id:
                type = Type.CREATE_ROOM;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, DrawingFragment.newInstance());
                break;
            case R.id.music_id:
                type = Type.JOIN_ROOM;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, DrawingFragment.newInstance());
                break;
            case R.id.tv_id:
                type = Type.NOTES;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, DrawingFragment.newInstance());
                break;
            case R.id.games_id:
                type = Type.ABOUT_US;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, DrawingFragment.newInstance());
                break;

        }
    }


}
