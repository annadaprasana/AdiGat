package com.ags.annada.adigat.events;

/**
 * Created by Annada on 18/10/2015.
 */
public class JsonOperationFinished {
    private static String mStatus;
    private static String mResult;

    public String getStatus(){
        return mStatus;
    }

    public void setStatus(String status){
        this.mStatus = status;
    }

    public String getResult(){
        return mResult;
    }

    public void setResult(String result){
        this.mResult = result;
    }
}
