package com.example.expense_status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerClass recyclerClass;
    TextView total_cost;
    ArrayList<Integer> D,M,Y,V;
    ArrayList<String> N;
    OpenHelperDb openHelperDb;
    String Product_search;
    int a,b,c,total_sum;
    String dd,mm,yyyy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recycler_view);
        total_cost =findViewById(R.id.total_cost);
        openHelperDb = new OpenHelperDb(MainActivity2.this);
        D= new ArrayList<>();
        M=new ArrayList<>();
        Y=new ArrayList<>();
        V=new ArrayList<>();
        N=new ArrayList<>();
        Intent i = getIntent();
        a=i.getIntExtra("a",0);
        b=i.getIntExtra("b",0);
        c=i.getIntExtra("c",0);
        Product_search=i.getStringExtra("Product_search");
        dd=i.getStringExtra("dd");
        mm=i.getStringExtra("mm");
        yyyy=i.getStringExtra("yyyy");
        total_sum=0;


        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
        if(a==1)
        {
            Cursor cursor  = openHelperDb.fetch_all();
            D.clear();M.clear();Y.clear();N.clear();V.clear();
            if(cursor.getCount()==0)
            {
                Toast.makeText(this,"No datas" , Toast.LENGTH_SHORT).show();
            }
            else
            {
                while(cursor.moveToNext())
                {
                    int sample_sum= 0;
                    D.add(cursor.getInt(0));
                    M.add(cursor.getInt(1));
                    Y.add(cursor.getInt(2));
                    N.add(cursor.getString(3));
                    sample_sum = cursor.getInt(4);
                    V.add(sample_sum);
                    total_sum+=sample_sum;


                }
                recyclerClass = new RecyclerClass(MainActivity2.this,D,M,Y,N,V);
                recyclerClass.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerClass);
            }
        }
        else if(b==2)
        {
            Cursor cursor = openHelperDb.fetch_specific(Product_search);
            D.clear();M.clear();Y.clear();N.clear();V.clear();
            if(cursor.getCount()==0)
            {
                Toast.makeText(MainActivity2.this,"No datas" , Toast.LENGTH_SHORT).show();
            }
            else
            {
                while(cursor.moveToNext())
                {
                    int sample_sum= 0;
                    D.add(cursor.getInt(0));
                    M.add(cursor.getInt(1));
                    Y.add(cursor.getInt(2));
                    N.add(cursor.getString(3));
                    sample_sum = cursor.getInt(4);
                    V.add(sample_sum);
                    total_sum+=sample_sum;

                }
                recyclerClass = new RecyclerClass(MainActivity2.this,D,M,Y,N,V);
                recyclerClass.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerClass);
            }
        }
        else if(c==3)
        {
            Cursor cursor = openHelperDb.fetch_period(dd,mm,yyyy);
            D.clear();M.clear();Y.clear();N.clear();V.clear();
            if(cursor.getCount()==0)
            {
                Toast.makeText(MainActivity2.this,"No datas" , Toast.LENGTH_SHORT).show();
            }
            else
            {
                while(cursor.moveToNext())
                {
                    int sample_sum= 0;
                    D.add(cursor.getInt(0));
                    M.add(cursor.getInt(1));
                    Y.add(cursor.getInt(2));
                    N.add(cursor.getString(3));
                    sample_sum = cursor.getInt(4);
                    V.add(sample_sum);
                    total_sum+=sample_sum;

                }
                recyclerClass = new RecyclerClass(MainActivity2.this,D,M,Y,N,V);
                recyclerClass.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerClass);
            }

        }
        total_cost.setText(String.valueOf(Float.valueOf(total_sum)));



    }
}