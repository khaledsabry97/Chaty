package com.example.khaledsabry.Client.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.Client.Controllers.ChatController;
import com.example.khaledsabry.Client.Data.Data;
import com.example.khaledsabry.Client.Data.Message;
import com.example.khaledsabry.Client.R;

import java.util.ArrayList;

/**
 * use it to add the messages to view
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    ArrayList<Message> messages = new ArrayList<>();
    ChatController chatController = ChatController.getInstance();
    Data data = Data.getInstance();
    TextView count;

    public MessageAdapter(TextView count)
    {
        this.count = count;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chat_message,parent,false);
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

        public MessageViewHolder(View itemView) {
            super(itemView);
            messageCard = itemView.findViewById(R.id.message_card);
            sent = itemView.findViewById(R.id.sent);
            content = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
            nickName = itemView.findViewById(R.id.nick_name);

        }

        /**
         * update the gui of the chat fragment
         * @param message
         */
        private void updateUi(final Message message)
        {

            messageCard.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    deleteMessage(message);
                    return true;
                }
            });

            nickName.setText(message.getNickName());
            time.setText(message.getServerTime());
            sent.setText("Sent : "+String.valueOf(message.getSent()));
            content.setText(message.getContent());

            if(message.getNickName().equals(data.getNickName()))
                messageCard.setForegroundGravity(View.FOCUS_RIGHT);
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
