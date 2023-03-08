package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.Twit;

import java.util.Set;

public interface ITwitModelObserver {
    default void notifyTwitsChanged(Set<Twit> twits){}
}
