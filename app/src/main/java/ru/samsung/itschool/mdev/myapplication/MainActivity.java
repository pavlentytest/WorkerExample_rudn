package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private OneTimeWorkRequest workRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data myData = new Data.Builder()
                        .putString("keyA", "Hello from UI!!!")
                        .build();
                workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setInputData(myData)
                        .build();
                WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);

                WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(workRequest.getId()).observe(
                        MainActivity.this, new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                Log.d("RRR",workInfo.toString());
                                if(workInfo != null) {
                                    Log.d("RRR", "Data from worker: " + workInfo.getOutputData().getInt("keyB", 0));
                                }
                            }
                        }
                );
            }
        });
    }
}