package com.example.kursproj.Daily.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursproj.Daily.DailyNoteWeek;
import com.example.kursproj.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        String ans = note.getTextWeek();

        SpannableString spannableString = new SpannableString(ans);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Calendar calendar = Calendar.getInstance();
        long createdTime = System.currentTimeMillis();
        calendar.setTimeInMillis(createdTime);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        String DataEnd = note.getDataWeek();
        String DataNow = sdfNow.format(calendar.getTime());

        if (!note.isChecked()) {

            holder.TimeOutput.setText(note.getDataWeek());
            holder.TextOutput.setText(note.getTextWeek());

        } else {
            holder.TimeOutput.setText("Завершено преждевременно");
            holder.Check.setChecked(true);
            holder.Check.setEnabled(false);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.GRAY);
            spannableString.setSpan(strikethroughSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(foregroundColorSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.TextOutput.setText(spannableString);

        }

        try {
            if((sdfNow.parse(DataNow)).compareTo(sdfNow.parse(DataEnd))>0)
            {
                holder.TimeOutput.setText("Не успел лох");
                holder.Check.setChecked(true);
                holder.Check.setEnabled(false);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.GRAY);
                spannableString.setSpan(strikethroughSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(foregroundColorSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.TextOutput.setText(spannableString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        holder.Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realmDay = Realm.getDefaultInstance();
                realmDay.beginTransaction();

                if(!note.isChecked())
                {
                    note.setChecked(true);
                }
                else
                {
                    note.setChecked(false);
                }

                realmDay.commitTransaction();
                realmDay.close();

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {//dailyWeek deletion function
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
        CheckBox Check;
        LinearLayout line;

        public DailyViewHolderWeek(@NonNull View itemView) {
            super(itemView);
            TextOutput = itemView.findViewById(R.id.textoutput);
            TimeOutput = itemView.findViewById(R.id.timeoutput);
            Check = itemView.findViewById(R.id.checkBox);
            line = itemView.findViewById(R.id.item);
        }
    }
}