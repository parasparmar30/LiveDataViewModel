package com.livedata.viewmodel.example;

import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerViewModel extends ViewModel {
    private static final int ONE_SECOND = 1000;

    private MutableLiveData<Long> time = new MutableLiveData<>();
    private long initTime;
    public TimerViewModel(){
        initTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - initTime) / 1000;
                // setValue() cannot be called from a background thread so post to main thread.
                time.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);
    }
    public LiveData<Long> getElapsedTime() {
        return time;
    }

}
