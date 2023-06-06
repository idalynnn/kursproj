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

import com.example.kursproj.Daily.DailyNoteMouth;
import com.example.kursproj.R;

import io.realm.Realm;
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
        holder.TimeOutput.setText(note.getDataMouth());

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


    public class DailyViewHolderMouth extends RecyclerView.ViewHolder{

        TextView TextOutput;
        TextView TimeOutput;

        public DailyViewHolderMouth(@NonNull View itemView) {
            super(itemView);
            TextOutput = itemView.findViewById(R.id.textoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
