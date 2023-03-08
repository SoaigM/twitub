package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

public interface ISubscribeObserver {

    default void notifySubscribed(User subscribedUser){}


    default void notifyErrorSubscription(String error) {

    }

    default void notifySubscribeBack(){}
}
