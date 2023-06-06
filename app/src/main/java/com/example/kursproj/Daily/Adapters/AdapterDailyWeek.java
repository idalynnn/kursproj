package com.example.kursproj.Daily.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursproj.Daily.DailyNoteWeek;
import com.example.kursproj.R;

import io.realm.Realm;
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
        return new AdapterDailyWeek.DailyViewHolderWeek(LayoutInflater.from(context).inflate(R.layout.dialy_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolderWeek holder, int position) {
        DailyNoteWeek note = notesList.get(position);
        holder.TextOutput.setText(note.getTextWeek());
        holder.TimeOutput.setText(note.getDataWeek());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("Удалить");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Удалить")){
                            //delete the note
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context,"Заметка удалена",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });
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
            TextOutput = itemView.findViewById(R.id.textoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
