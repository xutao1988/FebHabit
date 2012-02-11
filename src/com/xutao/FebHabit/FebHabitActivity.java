package com.xutao.FebHabit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FebHabitActivity extends Activity {
	Button viewButton = null;
	Button addButton = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        viewButton = (Button) findViewById(R.id.view_btn);
        addButton = (Button) findViewById(R.id.add_btn);
        
        viewButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FebHabitActivity.this, ViewRecord.class);
				startActivity(intent);
				
			}
		});
        
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FebHabitActivity.this, AddRecord.class);
				startActivity(intent);
			}
		});
    }
}