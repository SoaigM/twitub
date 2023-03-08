package com.ubo.tp.twitub.observer;

public interface IListTwitObserver {
    default void notifyListTwitBack(){}

    default void notifySearch(String text){}
}
