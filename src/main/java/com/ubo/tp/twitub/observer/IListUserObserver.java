package main.java.com.ubo.tp.twitub.observer;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IListUserObserver {
    void notifyListUserBack();

    void notifySearch(String text);

    void notifyFollow(User userClicked);
}
