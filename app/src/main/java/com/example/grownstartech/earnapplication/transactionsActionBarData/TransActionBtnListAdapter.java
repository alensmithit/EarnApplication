package com.example.grownstartech.earnapplication.transactionsActionBarData;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grownstartech.earnapplication.R;

public class TransActionBtnListAdapter extends RecyclerView.Adapter{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trans_actionbtn_list, viewGroup, false);
        return new com.example.grownstartech.earnapplication.transactionsActionBarData.TransActionBtnListAdapter.TransListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((com.example.grownstartech.earnapplication.transactionsActionBarData.TransActionBtnListAdapter.TransListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return TransActionBtnData.heading.length;
    }

    private class TransListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mItemHeading;
        private TextView mItemActionBarTransDesc;
        private ImageView mItemCircleTransActionBtn;
        private ImageView mArrorExpandTransAB;

        public TransListViewHolder(View itemView){
            super(itemView);
            mItemHeading = (TextView) itemView.findViewById(R.id.itemTransHeadingBtn);
            mItemActionBarTransDesc = (TextView) itemView.findViewById(R.id.itemDetailTransBtn);
            mItemCircleTransActionBtn = (ImageView) itemView.findViewById(R.id.circle_img_trans);
            mArrorExpandTransAB = (ImageView) itemView.findViewById(R.id.arrowTransBtn);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){

            mItemHeading.setText(TransActionBtnData.heading[position]);
            mItemActionBarTransDesc.setText(TransActionBtnData.detail[position]);
           //mItemCircleTransActionBtn.setImageResource(TransActionBtnData.colorCircle[position]);
           // mArrorExpandTransAB.setImageResource(TransActionBtnData.extendTrans[position]);
        }
        public void onClick(View view){


        }
    }
}


