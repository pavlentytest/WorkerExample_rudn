package ru.samsung.itschool.mdev.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("RRR","Start working...");
        String getdata = getInputData().getString("keyA");
        Log.d("RRR","Get data from UI thread: \""+getdata+"\"");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //  if(a == 100) return Worker.Result.failure();
        Data sendfromworker = new Data.Builder().putInt("keyB",1000000).build();
        return Worker.Result.success(sendfromworker);
    }
}
