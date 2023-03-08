package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

public interface IListUserObserver {
    default void notifyListUserBack(){}

    default void notifySearch(String text){}

    default void notifyFollow(User userClicked){}
}
