package com.example.oshan.fullscreenbottomsheetexample;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<Food> mList;
    private OnItemClickedListener listener;

    ItemAdapter(List<Food> list, OnItemClickedListener listener) {
        this.mList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        //view.setOnClickListener(innerListener);
        return new MyViewHolder(view);
    }

    private final android.view.View.OnClickListener innerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onItemClicked("Clicked: ");
        }
    };

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Food item = this.mList.get(position);
        holder.txtFoodName.setText(item.getName());
        Picasso.get().load(item.image).into(holder.imageView);

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(holder.txtFoodName.getText().toString());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView txtFoodName;


        MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked("Clicked: ");
            }
        }

    }

    public interface OnItemClickedListener {
        void onItemClicked(String name);
    }

}