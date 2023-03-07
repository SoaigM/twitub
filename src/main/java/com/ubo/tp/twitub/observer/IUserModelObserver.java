package main.java.com.ubo.tp.twitub.observer;

import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.Set;

public interface IUserModelObserver {
    void notifyUsersChanged(Set<User> user);

    void notifySessionChanged();
}
