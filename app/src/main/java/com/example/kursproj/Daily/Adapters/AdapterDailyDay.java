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

import com.example.kursproj.Daily.DailyNoteDay;
import com.example.kursproj.R;

import io.realm.Realm;
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
                            DailyOne.deleteFromRealm();
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
