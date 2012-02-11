package com.xutao.FebHabit;


import com.xutao.FebHabit.entity.Record;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RecordAdapter extends CursorAdapter {
	

	public RecordAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView dayView = (TextView) view.findViewById(R.id.day_view);
		TextView infoView = (TextView) view.findViewById(R.id.info_view);
		TextView idView = (TextView) view.findViewById(R.id.key_view);
		TextView amView = (TextView) view.findViewById(R.id.am_view);
		TextView pmView = (TextView) view.findViewById(R.id.pm_view);
		
		dayView.setText(cursor.getString(cursor.getColumnIndex(Record.KEY_DAY)));
		infoView.setText("备注：" + cursor.getString(cursor.getColumnIndex(Record.KEY_INFO)));
		
		idView.setText(cursor.getString(cursor.getColumnIndex(Record.KEY_ID)));
		amView.setText("上午任务：" + cursor.getString(cursor.getColumnIndex(Record.KEY_AM)));
		pmView.setText("下午任务：" + cursor.getString(cursor.getColumnIndex(Record.KEY_PM)));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = li.inflate(R.layout.recordview, null);
		return view;		
		
	}

}
