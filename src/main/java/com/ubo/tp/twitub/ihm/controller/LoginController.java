package com.ubo.tp.twitub.ihm.controller;

import com.ubo.tp.twitub.datamodel.IDatabase;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.ihm.TwitubModel;
import com.ubo.tp.twitub.observer.ILoginObserver;

import java.util.HashSet;
import java.util.Set;

public class LoginController {


    protected final Set<ILoginObserver> mObservers;
    private final IDatabase database;

    private User user = null;

    public LoginController(TwitubModel model) {
        this.database = model.getDatabase();
        this.mObservers = new HashSet<>();
    }

    public void doLogin(String login, String password) {
        Set<User> users = getUsers();
        for (User userLog : users) {
            if (userLog.getName().equals(login) && userLog.getUserPassword().equals(password)) {
                this.user = userLog;
                mObservers.forEach(e -> e.notifyLoggedUser(userLog));
            }
        }
        mObservers.forEach(e -> e.notifyErrorLogin("login ou mot de passe incorrect"));
    }

    public void addObserver(ILoginObserver observer) {
        this.mObservers.add(observer);

        // Notification pour le nouvel observateur
        if (user != null) {
            observer.notifyLoggedUser(user);
        }
    }

    private Set<User> getUsers() {
        return database.getUsers();
    }

    public void subscribe() {
        mObservers.forEach(ILoginObserver::notifyAskSubscription);
    }
}
