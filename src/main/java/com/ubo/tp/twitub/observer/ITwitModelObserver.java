package main.java.com.ubo.tp.twitub.observer;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import java.util.Set;

public interface ITwitModelObserver {
    void notifyTwitsChanged(Set<Twit> twits);
}
