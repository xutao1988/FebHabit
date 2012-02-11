package com.xutao.FebHabit.entity;

import android.net.Uri;

public class Record {
	//һ��ָ��ContentProvider������������
    //����ContentProvider����Դ�Ĺ̶�·��
           //������¼
           public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
           //������¼
           public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
                         
           public static final String MINE_ITEM = "vnd.xutao.record";
    
    
    //�����ṩ�û�����һ��Ӧ�ó���ʹ�õ�URI
    //�������������ļ�AndroidManifest.xml��ע��
           //vnd.android.cursor.item/vnd.xutao.record
           public static final String MINE_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MINE_ITEM;
                        
           public static final String MINE_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MINE_ITEM;
                        
           public static final String AUTHORITY = "com.xutao.recordprovider";
           public static final String PATH_SINGLE = "record/#";
           public static final String PATH_MULTIPLE = "record";
                        
           //Uri
           //Э��://·��
           //newer:// AUTHORITY/������¼������ʽ
           public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
                        
           public static final Uri  CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
           
           
    //�����ֶ����ƣ�String��
     //�ֶ�
           public static final String KEY_ID = "_id";
           public static final String KEY_DAY = "day";
           public static final String KEY_AM = "am";
           public static final String KEY_PM = "pm";
           public static final String KEY_INFO = "info";


}
