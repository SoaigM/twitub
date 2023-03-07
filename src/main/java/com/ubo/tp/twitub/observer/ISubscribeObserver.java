package main.java.com.ubo.tp.twitub.observer;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface ISubscribeObserver {

    void notifySubscribed(User subscribedUser);


    void notifyErrorSubscription(String error);

    void notifySubscribeBack();
}
