package com.lambdaschool.sprint4challenge_mymovies;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    Activity context;
    String[] titles;
    boolean[] watched;

    ListViewUpdated listener;

    public CustomAdapter(Activity context, String[] titles, boolean[] watched) {
        super(context, R.layout.custom_listview_row, titles);

        this.context = context;
        this.titles = titles;
        this.watched = watched;
}

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_listview_row, parent, false);

        TextView textView = (TextView)v.findViewById(R.id.text_list_view_title);

        textView.setText(titles[position]);

        Button buttonDelete = v.findViewById(R.id.button_list_view_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onListItemDeleted(position);

                SQLiteDatabase db = context.openOrCreateDatabase("app_db", context.MODE_PRIVATE, null);
                db.execSQL("DELETE FROM FAVS WHERE TITLE = '" + titles[position] + "';");
                db.close();

            }
        });


        Button buttonWatch = v.findViewById(R.id.button_list_view_is_watched);
        if(watched[position]) {

            buttonWatch.setText("Unwatch");

        } else {

            buttonWatch.setText("To Watch");

        }

        buttonWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = context.openOrCreateDatabase("app_db", context.MODE_PRIVATE, null);

                boolean booleanChecker = !watched[position];

                db.execSQL("UPDATE FAVS SET watched='" + booleanChecker + "' where title='" + titles[position] + "'");

                db.close();     // need to remember to close

                listener.onListItemUpdated(position, titles[position], booleanChecker);

            }
        });

        return v;

    }
}