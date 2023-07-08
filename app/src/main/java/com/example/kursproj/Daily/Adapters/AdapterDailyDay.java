package com.example.kursproj.Daily.Adapters;

import android.content.Context;
import android.graphics.Color;
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

import com.example.kursproj.Daily.DailyNoteDay;
import com.example.kursproj.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        String ans = DailyOne.getTextDay();

        SpannableString spannableString = new SpannableString(ans);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Calendar calendar = Calendar.getInstance();
        long createdTime = System.currentTimeMillis();
        calendar.setTimeInMillis(createdTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String DataEnd = DailyOne.getDataDay();
        String DataNow = sdfNow.format(calendar.getTime());


        if (!DailyOne.isChecked()) {

            holder.TextDayOutput.setText(DailyOne.getTextDay());
            holder.TimeDayOutput.setText(DailyOne.getDataDay());
        }

        else {
            holder.TimeDayOutput.setText("Завершено преждевременно");
            holder.Check.setChecked(true);
            holder.Check.setEnabled(false);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.GRAY);
            spannableString.setSpan(strikethroughSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(foregroundColorSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.TextDayOutput.setText(spannableString);

        }
        try {
            if((sdfNow.parse(DataNow)).compareTo(sdfNow.parse(DataEnd))>0)
            {
                holder.TimeDayOutput.setText("Не успел лох");
                holder.Check.setChecked(true);
                holder.Check.setEnabled(false);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.GRAY);
                spannableString.setSpan(strikethroughSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(foregroundColorSpan, 0, ans.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.TextDayOutput.setText(spannableString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        holder.Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realmDay = Realm.getDefaultInstance();
                realmDay.beginTransaction();

                if(!DailyOne.isChecked())
                {
                    DailyOne.setChecked(true);
                }
                else
                {
                    DailyOne.setChecked(false);
                }

                realmDay.commitTransaction();
                realmDay.close();
            }
        });

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
                            realm.close();

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
        CheckBox Check;
        LinearLayout line;

        public DailyViewHolderDay(@NonNull View itemView) {
            super(itemView);
            TextDayOutput = itemView.findViewById(R.id.textoutput);
            TimeDayOutput = itemView.findViewById(R.id.timeoutput);
            Check = itemView.findViewById(R.id.checkBox);
            line = itemView.findViewById(R.id.item);
        }
    }
}
