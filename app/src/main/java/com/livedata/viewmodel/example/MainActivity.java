package com.livedata.viewmodel.example;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private TimerViewModel mTimerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
    }
    private void initViewModel(){
        mTimerViewModel =  ViewModelProviders.of(this).get(TimerViewModel.class);
        observerViewModel();
    }

    private void observerViewModel() {
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long aLong) {
                ((TextView) findViewById(R.id.txtTime)).setText(aLong +" seconds elapsed");

            }
        };

        mTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }
}
