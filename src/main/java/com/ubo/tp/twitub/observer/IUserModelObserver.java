package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

import java.util.Set;

public interface IUserModelObserver {
    void notifyUsersChanged(Set<User> user);

    void notifySessionChanged();
}
