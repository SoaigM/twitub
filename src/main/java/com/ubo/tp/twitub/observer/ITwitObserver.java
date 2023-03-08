package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.Twit;

public interface ITwitObserver {


    default void notifyNewTwit(Twit twit){}

    default void notifyErrorTwit(String error){}

    default void notifyAskTwitsList(){}

    default void notifyAskUsersList(){}

    default void notifyNotification(int i){}
}
