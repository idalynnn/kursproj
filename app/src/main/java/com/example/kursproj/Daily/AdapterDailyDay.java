package com.example.kursproj.Daily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursproj.R;

import io.realm.RealmResults;

public class AdapterDailyDay extends RecyclerView.Adapter<AdapterDailyDay.DailyViewHolderDay>{

    Context context;
    RealmResults<DailyNoteDay> DailyOneList;

    public AdapterDailyDay(Context context, RealmResults<DailyNoteDay> noteList) {
        this.context = context;
        this.DailyOneList = noteList;
    }
    @NonNull
    @Override
    public DailyViewHolderDay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DailyViewHolderDay(LayoutInflater.from(context).inflate(R.layout.dialy_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDailyDay.DailyViewHolderDay holder, int position) {
        DailyNoteDay DailyOne = DailyOneList.get(position);
        holder.TextDayOutput.setText(DailyOne.getTextDay());
        holder.TimeDayOutput.setText(DailyOne.getDataDay());
    }

    @Override
    public int getItemCount() {
        return DailyOneList.size();
    }

    public class DailyViewHolderDay extends RecyclerView.ViewHolder{

        TextView TextDayOutput;
        TextView TimeDayOutput;

        public DailyViewHolderDay(@NonNull View itemView) {
            super(itemView);
            TextDayOutput = itemView.findViewById(R.id.textoutput);
            TimeDayOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
