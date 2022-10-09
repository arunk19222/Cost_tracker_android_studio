package com.example.expense_status;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class OpenHelperDb extends SQLiteOpenHelper {
    static final private String db_name = "op_database";
    static final private String Table_name = "expense_stats_table";
    static final private String Date = "Date";
    static final private String Month = "Month";
    static final private String Year = "Year";
    static final private String Product_name = "Name";
    static final private String Product_value = "Cost";
    Context context;

    public OpenHelperDb(@Nullable Context context) {
        super(context, db_name, null, 10);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists "+Table_name+ "(" + Date +" integer , "+ Month + " integer ," + Year +" integer, "
                +Product_name + " varchar(20) ," +  Product_value  + " integer  );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + Table_name);
        onCreate(sqLiteDatabase);
    }
    public boolean add(int date , int month , int year ,String p_n,int p_v)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Date,date);
        contentValues.put(Month,month);
        contentValues.put(Year,year);
        contentValues.put(Product_name,p_n);
        contentValues.put(Product_value,p_v);
        long sample = db.insert(OpenHelperDb.Table_name,null,contentValues);
        if(sample!=-1)
        {
            Toast.makeText(context,"added successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"added Unsuccessfully",Toast.LENGTH_LONG).show();
        }

        return sample!=-1;
    }
    public Cursor fetch_all(){
        SQLiteDatabase db = this.getWritableDatabase();
        String s = "select * from " + Table_name;
        Cursor cursor = db.rawQuery("select * from " + Table_name,null);
        return cursor;
    }
    public Cursor fetch_specific(String p_n){
        SQLiteDatabase db = this.getWritableDatabase();
        String s ="select * from "+ Table_name + " where " + Product_name + " =? ";
        Cursor cursor = db.rawQuery(s,new String[]{p_n});
        return cursor;
    }
    public Cursor fetch_period(String date,String month, String year)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String s ="select * from "+ Table_name + " where " + Date + " =? and "+ Month + " =? and "+ Year + " =? ";
        Cursor cursor = db.rawQuery(s,new String[]{date,month,year});
        return cursor;
    }
    public void remove(String date,String month, String year,String name,String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long sample = db.delete(Table_name,Date + " =? and " +
                Month + " =? and " + Year+ " =? and " + Product_name+ " =? and "+ Product_value + " =? "
                ,new String[]{date,month,year,name,value});
        if(sample==1)
        {
            Toast.makeText(context,"Item Deleted,Go Back To See The Changes",Toast.LENGTH_SHORT).show();
        }
    }


}

