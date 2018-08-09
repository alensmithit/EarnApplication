package com.example.grownstartech.earnapplication.transactions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grownstartech.earnapplication.R;

public class TransListAdapter extends RecyclerView.Adapter{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trans_item_list, viewGroup, false);
        return new TransListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((TransListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return TransData.title.length;
    }

    private class TransListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mItemText;
        private TextView mItemDesc;
        private TextView mItemAmount;

        public TransListViewHolder(View itemView){
            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.itemTransHeadingBtn);
            mItemAmount = (TextView) itemView.findViewById(R.id.balance_trans);
            mItemDesc = (TextView) itemView.findViewById(R.id.itemDetailTransBtn);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){

            mItemText.setText(TransData.title[position]);
            mItemAmount.setText(TransData.amount[position]);
            mItemDesc.setText(TransData.date[position]);
        }
        public void onClick(View view){


        }
    }
}
