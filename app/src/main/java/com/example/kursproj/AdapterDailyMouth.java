package com.example.kursproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class AdapterDailyMouth extends RecyclerView.Adapter<AdapterDailyMouth.DailyViewHolderMouth>{

    Context context;
    RealmResults<DailyNoteMouth> notesList;

    public AdapterDailyMouth(Context context, RealmResults<DailyNoteMouth> noteList) {
        this.context = context;
        this.notesList = noteList;
    }

    @NonNull
    @Override
    public DailyViewHolderMouth onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDailyMouth.DailyViewHolderMouth(LayoutInflater.from(context).inflate(R.layout.dialy_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolderMouth holder, int position) {
        DailyNoteMouth note = notesList.get(position);
        holder.TextOutput.setText(note.getTextMouth());
        holder.TimeOutput.setText("Data");
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    public class DailyViewHolderMouth extends RecyclerView.ViewHolder{

        TextView TextOutput;
        TextView TimeOutput;

        public DailyViewHolderMouth(@NonNull View itemView) {
            super(itemView);
            TextOutput = itemView.findViewById(R.id.titleoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
