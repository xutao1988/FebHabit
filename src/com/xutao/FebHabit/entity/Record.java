package com.xutao.FebHabit.entity;

import android.net.Uri;

public class Record {
	//一、指定ContentProvider操作数据类型
    //查找ContentProvider数据源的固定路径
           //多条记录
           public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
           //单条记录
           public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
                         
           public static final String MINE_ITEM = "vnd.xutao.record";
    
    
    //二、提供用户（另一个应用程序）使用的URI
    //必须在主配置文件AndroidManifest.xml中注册
           //vnd.android.cursor.item/vnd.xutao.record
           public static final String MINE_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MINE_ITEM;
                        
           public static final String MINE_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MINE_ITEM;
                        
           public static final String AUTHORITY = "com.xutao.recordprovider";
           public static final String PATH_SINGLE = "record/#";
           public static final String PATH_MULTIPLE = "record";
                        
           //Uri
           //协议://路径
           //newer:// AUTHORITY/多条记录操作方式
           public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
                        
           public static final Uri  CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
           
           
    //三、字段名称（String）
     //字段
           public static final String KEY_ID = "_id";
           public static final String KEY_DAY = "day";
           public static final String KEY_AM = "am";
           public static final String KEY_PM = "pm";
           public static final String KEY_INFO = "info";


}
