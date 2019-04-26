package com.example.khaledsabry.Client.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.khaledsabry.Client.Controllers.ChatController;
import com.example.khaledsabry.Client.Controllers.SignInUpControlller;
import com.example.khaledsabry.Client.Data.Data;
import com.example.khaledsabry.Client.Functions.Toasts;
import com.example.khaledsabry.Client.MainActivity;
import com.example.khaledsabry.Client.R;


public class CreateRoomFragment extends Fragment {
    EditText nickName,roomName,password;
    Button create,join;
    Data data;
    ImageView aboutUs,info;

    SignInUpControlller signInUpControlller;

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
        aboutUs = view.findViewById(R.id.about_us);
        info = view.findViewById(R.id.info);

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
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutUs();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info();
            }
        });
        signInUpControlller = SignInUpControlller.getInstance();
        data = Data.getInstance();
        signInUpControlller.setCreateRoomFragment(this);
        return view;
    }

    /**
     * how to use the program
     */
    private void info() {
        MainActivity.loadFragmentWithReturn(R.id.main_container, InfoFragment.newInstance());

    }

    /**
     * info about us
     */
    private void aboutUs() {
        MainActivity.loadFragmentWithReturn(R.id.main_container, AboutMeFragment.newInstance());

    }

    /**
     * to join to room
     */
    void join()
    {
        if(!check())
            return;
        signInUpControlller.joinRoom(roomName.getText().toString(),password.getText().toString(),nickName.getText().toString());
    }

    /**
     * to create new room
     */
    void create()
    {
        if(!check())
            return;

        signInUpControlller.createRoom(roomName.getText().toString(),password.getText().toString(),nickName.getText().toString());



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


    /**
     * room created successfully
     * @param roomId room id
     */
    public void created(int roomId)
    {
        //load chat fragment
        Toasts.success("Room Created Successfully");
        data.setNickName(nickName.getText().toString());
        data.setRoomId(roomId);
        data.setRoomName(roomName.getText().toString());
        MainActivity.loadFragmentWithReturn(R.id.main_container, ChatFragment.newInstance());
    }

    /**
     * you have been joined to the room
     * @param roomId room id
     */
    public void Joined(int roomId)
    {
        Toasts.success("you are joined to the room");
        data.setNickName(nickName.getText().toString());
        data.setRoomId(roomId);
        data.setRoomName(roomName.getText().toString());
        MainActivity.loadFragmentWithReturn(R.id.main_container, ChatFragment.newInstance());
    }

    /**
     *
     * room didn't be created
     * @param msg the problem
     */
    public void notCreated(String msg)
    {
        Toasts.error(msg);
    }

    /**
     * something went wrong when you join
     * @param msg the problem
     */
    public void notJoined(String msg)
    {
        Toasts.error(msg);
    }

}
