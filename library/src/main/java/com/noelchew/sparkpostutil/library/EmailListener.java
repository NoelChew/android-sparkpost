package com.noelchew.sparkpostutil.library;

/**
 * Created by noelchew on 7/22/16.
 */
public interface EmailListener {
    void onSuccess();
    void onError(Throwable e);
}
