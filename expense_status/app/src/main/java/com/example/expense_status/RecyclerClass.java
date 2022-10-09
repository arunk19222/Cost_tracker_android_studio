package com.example.expense_status;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerClass extends RecyclerView.Adapter<RecyclerClass.ViewHolder> {
    Context context;
    ArrayList<Integer> D,M,Y,V;
    ArrayList<String> N;
    OpenHelperDb openHelperDb;
    public RecyclerClass(Context context,ArrayList<Integer> D ,ArrayList<Integer> M ,ArrayList<Integer> Y ,
                         ArrayList<String> N, ArrayList<Integer> V  )
    {
        this.context= context;
        this.D=D;
        this.M=M;
        this.Y=Y;
        this.N =N;
        this.V=V;
        openHelperDb = new OpenHelperDb(context);


    }
    void vibrator_function()
    {
        Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }
    }
    void sound_effect()
    {
        MediaPlayer mp = MediaPlayer.create(context,R.raw.zapsplat_multimedia_ui_chime_alert_notification_simple_ping_2_tone_medium_pitched_88738);
        mp.start();
    }
    @NonNull
    @Override
    public RecyclerClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new RecyclerClass.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerClass.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.date.setText(D.get(position).toString());
        holder.month.setText(M.get(position).toString());
        holder.year.setText(Y.get(position).toString());
        holder.p_n.setText(N.get(position));
        holder.p_v.setText(String.valueOf(V.get(position)));
        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("From System");
                String sample =  holder.p_n.getText().toString();
                alertDialog.setMessage("Are You Sure To Delete That Item: "+holder.p_n.getText().toString()+" ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String dd=holder.date.getText().toString();
                        String mm=holder.month.getText().toString();
                        String yy=holder.year.getText().toString();
                        String pn=holder.p_n.getText().toString();
                        String pv=holder.p_v.getText().toString();
                        openHelperDb.remove(dd,mm,yy,pn,pv);
                        D.remove(position);
                        M.remove(position);
                        Y.remove(position);
                        V.remove(position);
                        N.remove(position);
                        notifyDataSetChanged();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                sound_effect();
                alertDialog.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return D.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date,month,year,p_n,p_v;
        public ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item);
            month = itemView.findViewById(R.id.month_item);
            year = itemView.findViewById(R.id.year_item);
            p_n = itemView.findViewById(R.id.name_item);
            p_v = itemView.findViewById(R.id.value_item);
            constraintLayout=itemView.findViewById(R.id.whole_item);
        }
    }
}
