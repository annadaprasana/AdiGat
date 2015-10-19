package com.ags.annada.adigat.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.ags.annada.adigat.events.DBOperationFinished;
import com.ags.annada.adigat.events.JsonOperationFinished;
import com.ags.annada.adigat.receiver.ParseJsonResultReceiver;

import java.io.IOException;
import java.io.InputStream;

import de.greenrobot.event.EventBus;

/**
 * Created by Annada on 09/10/2015.
 */
public class ParseJsonService extends IntentService{
    private static final String TAG = ParseJsonService.class.getSimpleName();

    public static final int JSON_STATUS_FINISHED = 1;
    public static final int JSON_STATUS_ERROR = 2;

    private static final String JSON_FILE_NAME = "foods.json";

    public ParseJsonService() {
        super(ParseJsonService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        //Bundle bundle = new Bundle();

        try {
            InputStream inputStream = getApplicationContext().getAssets().open(JSON_FILE_NAME);

            if(inputStream != null) {
                int fileSize = inputStream.available();
                byte[] buffer = new byte[fileSize];
                inputStream.read(buffer);
                inputStream.close();
                String jsonStr = new String(buffer,"UTF-8");

                //bundle.putString("json", jsonStr);
                //receiver.send(JSON_STATUS_FINISHED, bundle);

                JsonOperationFinished jsonStatus = new JsonOperationFinished();
                jsonStatus.setStatus("success");
                jsonStatus.setResult(jsonStr);

                EventBus.getDefault().post(jsonStatus);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            //bundle.putString(Intent.EXTRA_TEXT, e.toString());
            //receiver.send(JSON_STATUS_ERROR,bundle);

            JsonOperationFinished jsonStatus = new JsonOperationFinished();
            jsonStatus.setStatus("failed");

            EventBus.getDefault().post(jsonStatus);
        }

        this.stopSelf();
    }
}
