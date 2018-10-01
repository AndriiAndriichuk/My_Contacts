package com.example.andrii.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrii.contacts.entity.Notification;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TransactionAdapterCalls extends RecyclerView.Adapter<TransactionAdapterCalls.CardViewHolder> {
    List<Notification> notifications;

    public TransactionAdapterCalls(List<Notification> list) {
        this.notifications = list;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_calls, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.name.setText(notification.getContact_name());
        holder.number.setText(notification.getContact_number());
        holder.image.setImageBitmap(notification.getContact_photo());
        holder.call_time.setText(new SimpleDateFormat("HH:mm").format(notification.getDate()));//DateFormat.format("HH:mm", Long.valueOf(notification.getDate().toString())).toString() );
        if (notification.getNostif_type() == "call"){
            holder.call_sms.setImageResource(R.drawable.ic_call);
            switch (notification.getNostif_state()){
                case "OUTGOING_TYPE":holder.call_type.setImageResource(R.drawable.ic_outgoing_call);break;
                case "INCOMING_TYPE":holder.call_type.setImageResource(R.drawable.ic_incoming_call);break;
                case "MISSED_TYPE":holder.call_type.setImageResource(R.drawable.ic_missed_call);break;
                case "REJECTED_TYPE":holder.call_type.setImageResource(R.drawable.ic_rejected_call);break;
            }
        }else if(notification.getNostif_type() == "sms"){

              //holder.call_type.setImageResource(R.drawable.ic_outgoing_call);
            switch (notification.getNostif_state()){
                case "sent":holder.call_type.setImageResource(R.drawable.ic_sent);
                    holder.call_sms.setImageResource(R.drawable.ic_sms);break;
                case "inbox":holder.call_type.setImageResource(R.drawable.ic_inbox);
                    holder.call_sms.setImageResource(R.drawable.ic_sms);break;
                case "inbox1":
                    holder.call_type.setImageResource(R.drawable.ic_inbox);
                    holder.call_sms.setImageResource(R.drawable.ic_readed);
                    break;
                /*case "MISSED_TYPE":holder.call_type.setImageResource(R.drawable.ic_missed_call);break;
                case "REJECTED_TYPE":holder.call_type.setImageResource(R.drawable.ic_rejected_call);break;*/
            }
      //
          }




    }
    public void removeItem(int position){
        notifications.remove(position);
        notifyItemChanged(position);
    }


    @Override
    public int getItemCount() {
        return notifications.size();
    }



    public static class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected CircleImageView image;
        protected TextView number;
        protected TextView call_time;
        protected ImageView call_type;
        protected ImageView call_sms;


        public CardViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            image = (CircleImageView) itemView.findViewById(R.id.user_icon);
            number = (TextView) itemView.findViewById(R.id.contact_number);
            call_time = (TextView) itemView.findViewById(R.id.call_time);
            call_type = (ImageView) itemView.findViewById(R.id.call_type);
            call_sms = (ImageView) itemView.findViewById(R.id.call_sms);

        }


    }
}
