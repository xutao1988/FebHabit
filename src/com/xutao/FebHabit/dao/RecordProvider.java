package com.xutao.FebHabit.dao;


import com.xutao.FebHabit.entity.Record;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class RecordProvider extends ContentProvider{
	
	private static final UriMatcher uriMatcher;
    private SQLiteDatabase sd = null;
    
    private static final int MULTIPLE_USER = 1;
    private static final int SINGLE_USER = 2;

    static{
        
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
          
        //查找一条记录
        //CONTENT:// AUTHORITY/多条记录操作方式
       uriMatcher.addURI(Record.AUTHORITY,Record.PATH_MULTIPLE,MULTIPLE_USER);//1
        //查找多条记录
        uriMatcher.addURI(Record.AUTHORITY,Record.PATH_SINGLE,SINGLE_USER);//2      
   }


	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
        
        switch(uriMatcher.match(uri)){
              case MULTIPLE_USER:
                 count = sd.delete("Record", selection, selectionArgs);
                 break;
              case SINGLE_USER:
                 String segment = uri.getPathSegments().get(1);                 
                 count = sd.delete("Record", Record.KEY_ID + "=" + segment, selectionArgs);
                 break;
                     
              default:
                     throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        
        getContext().getContentResolver().notifyChange(uri, null);
        
        return count;

	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri)){
        case MULTIPLE_USER://1
               return Record.MINE_TYPE_MULTIPLE;
        case SINGLE_USER:
               return Record.MINE_TYPE_SINGLE;
        default:
               throw new IllegalArgumentException("Unkown uri:"+uri);
        }
	}
	
	class MyDataHelper extends SQLiteOpenHelper{
        
        String sql = "create table record (_id integer primary key autoincrement, " +
                     "day text not null, am text not null, pm text not null, info text);";

        public MyDataHelper(Context context, String name, CursorFactory factory, int version) {
               super(context, name, factory, version);
               // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
               // TODO Auto-generated method stub
               db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               db.execSQL("drop table if exists user");
               onCreate(db);
        }        
 }


	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long id = sd.insert("record", null, values);

        if (id > 0) {
           Uri newUserUri =ContentUris.withAppendedId(Record.CONTENT_URI, id);

           RecordProvider.this.getContext().getContentResolver().notifyChange(newUserUri, null);
           return newUserUri;
        } else {
          throw new SQLException("Failed to insert row into " + uri);              
        
        }

	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context ctxt = RecordProvider.this.getContext();
          
		MyDataHelper mdh = new MyDataHelper(ctxt, "record.db", null, 1);//如果修改了数字1,onUpgrade

		sd = mdh.getWritableDatabase();
  
  
		if (sd == null) {
         return false;
		}else
		{
			return true;
		}

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
			SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
	        
	        sqb.setTables("record");
	              
	        switch(uriMatcher.match(uri)){
	              //查询指定编号记录
	              case SINGLE_USER:
	                     String segment =uri.getPathSegments().get(1);//3
	                     sqb.appendWhere(Record.KEY_ID + "=" + segment);
	                     break;
	              default:
	                     break;
                 }
	        
            Cursor cursor = sqb.query(sd, projection, selection, selectionArgs,
	                           null,
	                           null,
	                           sortOrder);
	        cursor.setNotificationUri(this.getContext().getContentResolver(),uri);
	      
	        return cursor;
	   

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;

        switch (uriMatcher.match(uri)) {
        case MULTIPLE_USER:
        	sd.update("Record", values, selection, selectionArgs);
              
              break;

        case SINGLE_USER:
        	String segment = uri.getPathSegments().get(1);              
        	count = sd.update("Record", values, Record.KEY_ID+"=" + segment, selectionArgs);
              
              break;
        }
        RecordProvider.this.getContext().getContentResolver().notifyChange(uri, null);
        
        return count;

	}

}
