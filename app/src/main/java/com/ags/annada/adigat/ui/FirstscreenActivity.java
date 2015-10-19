package com.ags.annada.adigat.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ags.annada.adigat.R;
import com.todddavies.components.progressbar.ProgressWheel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Annada on 18/09/2015.
 */
public class FirstscreenActivity extends AppCompatActivity {
    private static final String TAG = FirstscreenActivity.class.getSimpleName();

    private ProgressWheel mProgressWheel;
    Timer mTimer;
    TimerTask mTimerTask;
    int delayms = 3000; //3 Seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);
        mProgressWheel = (ProgressWheel) findViewById(R.id.pw_spinner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressWheel.startSpinning();

        //TODO replace timer technique with handler, as timer runs in main thread, but handler runs in different thread.

        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mProgressWheel.stopSpinning();
                startActivity(new Intent(FirstscreenActivity.this, LoginScreenActivity.class));
                finish();
                //overridePendingTransition(R.anim.snackbar_in,R.anim.snackbar_out);
            }
        };

        mTimer.schedule(mTimerTask,delayms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_firstscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
