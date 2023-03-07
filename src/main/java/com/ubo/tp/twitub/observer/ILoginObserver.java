package com.ubo.tp.twitub.observer;

import com.ubo.tp.twitub.datamodel.User;

public interface ILoginObserver {


    void notifyLoggedUser(User user);

    void notifyBackToConnectionPage();


    void notifyAskSubscription();


    void notifyErrorLogin(String error);

}
