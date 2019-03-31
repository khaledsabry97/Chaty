package com.example.khaledsabry.Client.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.khaledsabry.Client.Functions.Toasts;
import com.example.khaledsabry.Client.R;


public class CreateRoomFragment extends Fragment {
    EditText nickName,roomName,password;
    Button create,join;

    public static CreateRoomFragment newInstance() {
        CreateRoomFragment fragment = new CreateRoomFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_room, container, false);
        nickName = view.findViewById(R.id.nick_name);
        roomName = view.findViewById(R.id.room_name);
        password = view.findViewById(R.id.password);

        join = view.findViewById(R.id.join_room);
        create = view.findViewById(R.id.create_room);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
        return view;
    }

    void join()
    {
        if(!check())
            return;

    }

    void create()
    {
        if(!check())
            return;

    }


    boolean check()
    {
        if(nickName.getText().toString().isEmpty() || roomName.getText().toString().isEmpty() || password.getText().toString().isEmpty())
        {
            Toasts.error("please complete all the fields!");
            return false;
        }
        return true;
    }

}
