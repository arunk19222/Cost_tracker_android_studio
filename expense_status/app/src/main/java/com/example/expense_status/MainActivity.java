package com.example.expense_status;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

import soup.neumorphism.NeumorphButton;

public class MainActivity extends AppCompatActivity {
    NeumorphButton ADD,FILTER,Filter_product;
    EditText Product_name,Product_value;
    EditText Date,Month,Year,product_search;
    OpenHelperDb openHelperDb;

    String dd,mm,yyyy;
    void ui()
    {
        ADD=findViewById(R.id.Add_button);
        FILTER = findViewById(R.id.Filter_button);
        Filter_product=findViewById(R.id.Filter_product);
        product_search=findViewById(R.id.Product_search);
        Product_name=findViewById(R.id.Product_name);
        Product_value=findViewById(R.id.Product_value);
        Date=findViewById(R.id.Date);
        Month=findViewById(R.id.Month);
        Year =findViewById(R.id.Year);
        }
        void sound_effect(){
            MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.multimedia_button_click_012);
            mp.start();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui();
        openHelperDb = new OpenHelperDb(this);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        String sample = df.format(d);
        dd=sample.substring(0,2);
        mm=sample.substring(3,5);
        yyyy=sample.substring(6,10);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Product_name.length()>0 && Product_value.length()>0)
                {
                    openHelperDb.add(Integer.parseInt(dd),Integer.parseInt(mm),Integer.parseInt(yyyy),Product_name.getText().toString().toLowerCase()
                            ,Integer.parseInt(Product_value.getText().toString()));
                }
                else
                {
                    if(Product_name.length()==0){
                        Toast.makeText(MainActivity.this, "Enter Product Name", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Enter Product Value", Toast.LENGTH_SHORT).show();
                    }
                }
                sound_effect();
                Product_name.setText("");
                Product_value.setText("");
            }
        });
        Filter_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_effect();
                if(product_search.length()>0)
                {
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(i);

                        }
                    },2500);
                    i.putExtra("b",2);
                    i.putExtra("Product_search",product_search.getText().toString().toLowerCase());
                    startActivity(new Intent(MainActivity.this,MainActivity3.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Enter Product Name", Toast.LENGTH_SHORT).show();
                }
                product_search.setText("");
            }
        });
        FILTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_effect();
                if(Date.length()>0 && Month.length()>0 && Year.length()>0) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(i);
                        }
                    },2500);
                    i.putExtra("c", 3);
                    i.putExtra("dd", Date.getText().toString());
                    i.putExtra("mm", Month.getText().toString());
                    i.putExtra("yyyy", Year.getText().toString());
                    startActivity(new Intent(MainActivity.this,MainActivity3.class));

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Enter All Values", Toast.LENGTH_SHORT).show();
                }
                Date.setText("");
                Month.setText("");
                Year.setText("");
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.show_database,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Database_menu:
            {
                sound_effect();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this,MainActivity2.class);
                        i.putExtra("a",1);
                        startActivity(i);
                    }
                },2500);
                startActivity(new Intent(MainActivity.this,MainActivity3.class));

            }
        }
        return true;
    }
}