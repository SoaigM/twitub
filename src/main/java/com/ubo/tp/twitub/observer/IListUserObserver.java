package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

public interface IListUserObserver {
    void notifyListUserBack();

    void notifySearch(String text);

    void notifyFollow(User userClicked);
}
