package com.ags.annada.adigat.events;

/**
 * Created by Annada on 17/10/2015.
 */
public class DBOperationFinished {
    private static String mStatus;

    public String getStatus(){
        return mStatus;
    }

    public void setStatus(String status){
        this.mStatus = status;
    }
}
