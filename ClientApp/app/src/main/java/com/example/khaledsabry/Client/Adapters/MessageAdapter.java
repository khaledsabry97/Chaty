package com.example.khaledsabry.Client.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khaledsabry.Client.Controllers.ChatController;
import com.example.khaledsabry.Client.Data.Data;
import com.example.khaledsabry.Client.Data.Message;
import com.example.khaledsabry.Client.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * use it to add the messages to view
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    ArrayList<Message> messages = new ArrayList<>();
    ChatController chatController = ChatController.getInstance();
    Data data = Data.getInstance();
    TextView count;
    RecyclerView recyclerView;
    public MessageAdapter(TextView count,RecyclerView recyclerView)
    {
        this.count = count;
        this.recyclerView = recyclerView;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sided_chat,parent,false);
        return new MessageViewHolder(view);
    }

    public void clear()
    {
        messages.clear();
        count.setText(String.valueOf(messages.size()));
        notifyDataSetChanged();
    }

    /**
     * to add new message from you or others
     * @param message the message to add
     */
    public void addMessage(Message message)
    {
        messages.add(message);
        notifyItemInserted(messages.size());
        count.setText(String.valueOf(messages.size()));

recyclerView.scrollToPosition(messages.size()-1);
    }

    /**
     * delete message from your view
     * @param message message to delete
     */
    public void delteMessage(Message message)
    {
        for(int i = messages.size() -1;i >=0;i--)
        {
            if(messages.get(i).getNickName().equals(message.getNickName()) && messages.get(i).getLocalTime().equals(message.getLocalTime())) {
                messages.remove(i);
                notifyItemRemoved(i);
                count.setText(String.valueOf(messages.size()));
            }
        }

    }

    /**
     * update the no. of sent of the message
     * @param message the message to update
     */
    public void updateMessageSent(Message message)
    {
        for(int i = messages.size() -1;i >=0;i--)
        {
            if(messages.get(i).getNickName().equals(message.getNickName()) && messages.get(i).getLocalTime().equals(message.getLocalTime())) {
                messages.get(i).setSent(message.getSent());
                notifyItemChanged(i);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.updateUi(message);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{
        CardView messageCard;
        TextView sent,content,time,nickName;
        View view;
        LinearLayout left,right;

        public MessageViewHolder(View itemView) {
            super(itemView);
            left = itemView.findViewById(R.id.chat_left_msg_layout);
            right =itemView.findViewById(R.id.chat_right_msg_layout);

        }

        private void bind(View view)
        {
            sent = view.findViewById(R.id.sent);
            content = view.findViewById(R.id.message);
            time = view.findViewById(R.id.time);
            nickName = view.findViewById(R.id.nick_name);
        }

        /**
         * update the gui of the chat fragment
         * @param message
         */
        private void updateUi(final Message message)
        {
            if(message.getNickName().equals(data.getNickName()))
            {
                left.setVisibility(View.GONE);
                right.setVisibility(View.VISIBLE);

                view = right;
            }
            else
            {
                right.setVisibility(View.GONE);
                left.setVisibility(View.VISIBLE);

                view = left;
            }
            bind(view);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    deleteMessage(message);
                    return true;
                }
            });

            nickName.setText(message.getNickName());
            long k = Long.valueOf(message.getServerTime());
            Date date = new java.util.Date(k);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            String formattedDate = sdf.format(date);
            time.setText(formattedDate.substring(0,19));
            sent.setText("Sent : "+String.valueOf(message.getSent()));
            content.setText(message.getContent());


        }

        /**
         * delete a message of yours
         * @param message the message to delete
         */
        private void deleteMessage(Message message) {
            if(!message.getNickName().equals(data.getNickName()))
                return;
            chatController.deleteMessageRequest(message);
        }
    }
}
