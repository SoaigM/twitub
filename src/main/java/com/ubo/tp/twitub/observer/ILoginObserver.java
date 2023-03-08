package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

public interface ILoginObserver {


    default void notifyLoggedUser(User user){}

    default void notifyBackToConnectionPage(){}


    default void notifyAskSubscription(){}


    default void notifyErrorLogin(String error){
    }

}
