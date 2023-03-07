package main.java.com.ubo.tp.twitub.observer;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface ILoginObserver {


    void notifyLoggedUser(User user);

    void notifyBackToConnectionPage();


    void notifyAskSubscription();


    void notifyErrorLogin(String error);

}
