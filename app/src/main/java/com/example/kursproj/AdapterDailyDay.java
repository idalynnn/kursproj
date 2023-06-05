package com.example.kursproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class AdapterDailyDay extends RecyclerView.Adapter<AdapterDailyDay.DailyViewHolderDay>{

    Context context;
    RealmResults<DailyNoteDay> notesList;

    public AdapterDailyDay(Context context, RealmResults<DailyNoteDay> noteList) {
        this.context = context;
        this.notesList = noteList;
    }
    @NonNull
    @Override
    public DailyViewHolderDay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DailyViewHolderDay(LayoutInflater.from(context).inflate(R.layout.dialy_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolderDay holder, int position) {
        DailyNoteDay note = notesList.get(position);
        holder.TextOutput.setText(note.getTextDay());
        holder.TimeOutput.setText("Data");
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class DailyViewHolderDay extends RecyclerView.ViewHolder{

        TextView TextOutput;
        TextView TimeOutput;

        public DailyViewHolderDay(@NonNull View itemView) {
            super(itemView);
            TextOutput = itemView.findViewById(R.id.titleoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
