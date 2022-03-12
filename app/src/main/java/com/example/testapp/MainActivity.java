package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int count = 0;
    public static int restartCounter = 0;
    private Handler mHandler = new Handler();

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = findViewById(R.id.threadCountView);
        startRepeating(view);

        Button showDialog = findViewById(R.id.btnShowDialog);
        builder = new AlertDialog.Builder(this);

        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert = builder.create();
                alert.setTitle("Simple Dialog");
                alert.show();
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Toast.makeText(getApplicationContext(),"Dialog box closed",
                                Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    public void startRepeating(View v) {
        mToastRunnable.run();
    }

    public void stopRepeating(View v) {
        mHandler.removeCallbacks(mToastRunnable);
    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            TextView view = findViewById(R.id.threadCountView);
            count++;
            view.setText("Thread Count : " + count);
            Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT);
            mHandler.postDelayed(this, 500);
        }
    };
    public void goToSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void closeApp(View view) {
        stopRepeating(view);
      finish();
    }

    @Override
    protected void onRestart() {
        restartCounter ++;
        TextView rview = findViewById(R.id.restartView);
        rview.setText("Restart Count : " + restartCounter);
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}