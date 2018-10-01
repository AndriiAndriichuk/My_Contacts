package com.example.andrii.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrii.contacts.entity.Contact;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TransactionAdapterContacts extends RecyclerView.Adapter<TransactionAdapterContacts.CardViewHolder> {
    List<Contact> contacts;

    public TransactionAdapterContacts(List<Contact> list) {
        this.contacts = list;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.image.setImageBitmap(contact.getPhoto());


    }
    public void removeItem(int position){
        contacts.remove(position);
        notifyItemChanged(position);
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }



    public static class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected CircleImageView image;

        public CardViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            image = (CircleImageView) itemView.findViewById(R.id.user_icon);

        }


    }
}
