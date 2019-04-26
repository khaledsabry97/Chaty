package com.example.khaledsabry.Client.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.Client.Adapters.MessageAdapter;
import com.example.khaledsabry.Client.Controllers.ChatController;
import com.example.khaledsabry.Client.Data.Data;
import com.example.khaledsabry.Client.Data.Message;
import com.example.khaledsabry.Client.MainActivity;
import com.example.khaledsabry.Client.R;
import com.example.khaledsabry.Client.Functions.UpdateConnection;


public class ChatFragment extends Fragment {
    ImageView logOut;
    ImageView send;
    TextView roomName, messageNo;
    EditText inputMessage;
    RecyclerView messagesRecycler;
    Data data;
    ChatController chatController;
    MessageAdapter messageAdapter;

    UpdateConnection updateConnection;
    Thread updateConnectionThread;

    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        logOut = view.findViewById(R.id.log_out);
        send = view.findViewById(R.id.send);
        roomName = view.findViewById(R.id.room_name);
        messageNo = view.findViewById(R.id.count);
        inputMessage = view.findViewById(R.id.text_input);
        messagesRecycler = view.findViewById(R.id.chat_recycler);
        data = Data.getInstance();
        chatController = ChatController.getInstance();
        messageAdapter = new MessageAdapter(messageNo);
        updateConnection = new UpdateConnection();
        updateConnectionThread = new Thread(updateConnection);
        updateConnectionThread.start();

        setInputs();
        return view;
    }

    /**
     * set all the clicks that appears on the view to user
     */
    private void setInputs() {
        chatController.setChatFragment(this);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        roomName.setText(data.getRoomName());

        messagesRecycler.setAdapter(messageAdapter);
        messagesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
messagesRecycler.setFocusable(true);
        messageNo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                messageAdapter.clear();
                return false;
            }
        });
    }

    /**
     * send message to others in the room
     */
    private void send() {

        String text = inputMessage.getText().toString();
        if (text.equals(""))
            return;

        chatController.sendRequest(text);
        inputMessage.setText("");


    }

    private void logOut() {
        chatController.logOut();

        MainActivity.loadFragmentNoReturn(R.id.main_container, CreateRoomFragment.newInstance());
    }

    public void updateSentCount(Message message)
    {
        messageAdapter.updateMessageSent(message);
    }
    public void deleteMessage(Message message)
    {
        messageAdapter.delteMessage(message);
    }

    public void addMessage(Message message)
    {
        messageAdapter.addMessage(message);
    }


    @Override
    public void onDestroy() {
        updateConnectionThread.interrupt();
        chatController.logOut();


        super.onDestroy();
    }
}
