package com.example.kursproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class AdapterDailyWeek extends RecyclerView.Adapter<AdapterDailyWeek.DailyViewHolderWeek>{

    Context context;
    RealmResults<DailyNoteWeek> notesList;

    public AdapterDailyWeek(Context context, RealmResults<DailyNoteWeek> noteList) {
        this.context = context;
        this.notesList = noteList;
    }

    @NonNull
    @Override
    public DailyViewHolderWeek onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDailyWeek.DailyViewHolderWeek(LayoutInflater.from(context).inflate(R.layout.diary_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolderWeek holder, int position) {
        DailyNoteWeek note = notesList.get(position);
        holder.TextOutput.setText(note.getTextWeek());
        holder.TimeOutput.setText("Data");
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class DailyViewHolderWeek extends RecyclerView.ViewHolder{

        TextView TextOutput;
        TextView TimeOutput;

        public DailyViewHolderWeek(@NonNull View itemView) {
            super(itemView);
            TextOutput = itemView.findViewById(R.id.titleoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
