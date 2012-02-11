package com.xutao.FebHabit;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xutao.FebHabit.entity.Record;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddRecord extends Activity {
	
	EditText dayText = null;
	Button amButton = null;
	Button pmButton = null;
	EditText infoText = null;
	Button addButton = null;
	Button clearButton = null;
	ContentResolver contentResolver = null;
	
	Dialog dialog = null;
	final CharSequence[] items ={"已完成", "未完成"};
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord);
        
        contentResolver = this.getContentResolver();
        
        dayText = (EditText) findViewById(R.id.day);
        amButton = (Button) findViewById(R.id.am_btn);
        pmButton = (Button) findViewById(R.id.pm_btn);
        infoText = (EditText) findViewById(R.id.info);
        addButton = (Button) findViewById(R.id.addthis_btn);
        clearButton = (Button) findViewById(R.id.clearthis_btn);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String time = sdf.format(curDate);
        dayText.setText(time);
        
        amButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);
				
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                      
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                             // TODO Auto-generated method stub
                             String str = items[which] + "";
                             amButton.setText(str);                            
                      }
                });               
                
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                   }
               });
                
               dialog = builder.create();
               dialog.show();
	
			}
		});
        
        pmButton.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);
				
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                      
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                             // TODO Auto-generated method stub
                             String str1 = items[which] + "";
                             pmButton.setText(str1);                            
                      }
                });               
                
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                   }
               });
                
               dialog = builder.create();
               dialog.show();
	
			}
		});
        
        
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dayText.getText().equals("")) {
					return;					
				}
				
				ContentValues values = new ContentValues();
                
                values.put(Record.KEY_DAY, dayText.getText().toString());
                values.put(Record.KEY_AM, amButton.getText().toString());
                values.put(Record.KEY_PM, pmButton.getText().toString());
                values.put(Record.KEY_INFO, infoText.getText().toString());
                
                Uri newUri = contentResolver.insert(Record.CONTENT_URI, values);
                
                Toast.makeText(getApplication(), "添加数据成功", Toast.LENGTH_SHORT).show();
			}
		});
        
        clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				infoText.setText("");
				amButton.setText(getApplicationContext().getResources().getText(R.string.choose));
				pmButton.setText(getApplicationContext().getResources().getText(R.string.choose));				
			}
		});
        
        
	}
	

}
