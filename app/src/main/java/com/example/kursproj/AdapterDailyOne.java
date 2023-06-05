package com.example.kursproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class AdapterDailyOne extends RecyclerView.Adapter<AdapterDailyOne.DailyViewHolderOne>{


    Context context;
    RealmResults<DailyNoteDay> notesList;

    public AdapterDailyOne(Context context, RealmResults<DailyNoteDay> noteList) {
        this.context = context;
        this.notesList = noteList;
    }
    @NonNull
    @Override
    public DailyViewHolderOne onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DailyViewHolderOne(LayoutInflater.from(context).inflate(R.layout.diary_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolderOne holder, int position) {
        DailyNoteDay note = notesList.get(position);
        holder.TextOutput.setText(note.getTextDay());
        holder.TimeOutput.setText("Data");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DailyViewHolderOne extends RecyclerView.ViewHolder{

        TextView TextOutput;
        TextView TimeOutput;

        public DailyViewHolderOne(@NonNull View itemView) {
            super(itemView);
            TextOutput = itemView.findViewById(R.id.titleoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
