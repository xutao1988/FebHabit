package com.xutao.FebHabit;


import com.xutao.FebHabit.entity.Record;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewRecord extends ListActivity {
	
	Cursor cursor = null;
	ContentResolver contentResolver = null;
	String ids = null;
	Dialog dialog = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewrecord);
		
		contentResolver = getContentResolver();
		
		cursor = contentResolver.query(Record.CONTENT_URI,
		        new String[] { Record.KEY_ID, Record.KEY_DAY,
							Record.KEY_AM, Record.KEY_PM, Record.KEY_INFO},
							null, null, null);
		CursorAdapter adapter = new RecordAdapter(this, cursor);
		        
		setListAdapter(adapter);
	}
	
	
	
	
	@Override
	protected void onListItemClick(ListView l, final View v, int position, long id) {
		
		
		final CharSequence[] items ={"修改记录", "删除记录"};

		AlertDialog.Builder builder = new AlertDialog.Builder(ViewRecord.this);
		
		TextView idView = (TextView) v.findViewById(R.id.key_view);
		ids = idView.getText().toString();
		                           
		builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
		                     
		   @Override
		   public void onClick(DialogInterface dialog, int which) {
		       // TODO Auto-generated method stub
		       switch (which) {
			case 0:
				Intent intent = new Intent(ViewRecord.this, UpdateRecord.class);
				intent.putExtra("id", ids);
				startActivity(intent);
				

				dialog.dismiss();
				
				break;
			case 1:				
				Uri uri = Uri.parse(Record.CONTENT_URI_STRING + "/" + ids);
			    contentResolver.delete(uri, null, null);	
			    dialog.dismiss();
				break;

			default:
				break;
			}                        
		   }
		});
		
		dialog = builder.create();
		dialog.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuItem item = menu.add(0,Menu.FIRST, 0, "删除所有纪录");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case Menu.FIRST:
        	
        	AlertDialog.Builder builder = new AlertDialog.Builder(ViewRecord.this);
    		
    		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    			
    			@Override
    			public void onClick(DialogInterface dialog,int which) {
    				contentResolver.delete(Record.CONTENT_URI, null, null);
    			}
    		});
    		
    		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog,int which) {
                      dialog.dismiss();
               }
            });

    		AlertDialog ad = builder.create();
    	
    		ad.setMessage("确定删除所有记录?");
    		ad.show();
        	
        	break;
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	
	


	

}
