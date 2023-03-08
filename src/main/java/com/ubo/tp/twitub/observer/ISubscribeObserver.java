package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

public interface ISubscribeObserver {

    void notifySubscribed(User subscribedUser);


    void notifyErrorSubscription(String error);

    void notifySubscribeBack();
}
