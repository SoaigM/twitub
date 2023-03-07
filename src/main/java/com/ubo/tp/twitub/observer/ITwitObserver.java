package main.java.com.ubo.tp.twitub.observer;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

public interface ITwitObserver {


    void notifyNewTwit(Twit twit);

    void notifyErrorTwit(String error);

    void notifyAskTwitsList();

    void notifyAskUsersList();

    void notifyNotification(int i);
}
