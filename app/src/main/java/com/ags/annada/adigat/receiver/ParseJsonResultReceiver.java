package com.ags.annada.adigat.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Annada on 09/10/2015.
 */
public class ParseJsonResultReceiver extends ResultReceiver{
    private static final String TAG = ParseJsonResultReceiver.class.getSimpleName();

    private Receiver mReceiver;

    public ParseJsonResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver){
        mReceiver = receiver;
    }


    public interface Receiver{
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(mReceiver != null){
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
