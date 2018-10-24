package com.example.pushbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LevelDBHelper extends SQLiteOpenHelper{
    private final static String DATABASE_NAME = "levelDB";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "level";
    private final static String Column_name1 = "id";
    private final static String Column_name2 = "lv";
    private final static String SQL_CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME
            +" ( " + Column_name1 + " TEXT, " + Column_name2 + " TEXT" + " );";
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public LevelDBHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DATABASE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        this.onCreate(db);
    }
    public void insert(String id,String lv){
        ContentValues cv = new ContentValues();
        cv.put(Column_name1,id);
        cv.put(Column_name2,lv);
        this.getWritableDatabase().insert(TABLE_NAME,null,cv);
        this.getWritableDatabase().close();
    }
    public void update(String id,String newLv){
        ContentValues cv = new ContentValues();
        cv.put(Column_name2,newLv);
        String where = Column_name1 + " = ?";
        String[] whereValues = {id};
        this.getWritableDatabase().update(TABLE_NAME,cv,where,whereValues);
        this.getWritableDatabase().close();
    }
    public Cursor query(String id,LevelDBHelper levelDBHelper){
        String[] columns = new String[]{Column_name2};
        String selection = Column_name1 + " = ?";
        String[] selectionArgs = new String[]{id};

        SQLiteDatabase db = levelDBHelper.getReadableDatabase();
        return db.query(TABLE_NAME,columns,selection,selectionArgs,
                null,null,null);

    }

    public void clean(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(this.getWritableDatabase());
        this.getWritableDatabase().close();
    }

}
