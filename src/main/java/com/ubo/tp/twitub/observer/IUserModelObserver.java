package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

import java.util.Set;

public interface IUserModelObserver {
    default void notifyUsersChanged(Set<User> user){}

    default void notifySessionChanged(){}
}
