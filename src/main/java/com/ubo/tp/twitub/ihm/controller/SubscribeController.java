package com.ubo.tp.twitub.ihm.controller;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.IDatabase;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.ihm.TwitubModel;
import com.ubo.tp.twitub.observer.ISubscribeObserver;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SubscribeController {


    protected final Set<ISubscribeObserver> mObservers;
    private final IDatabase database;
    private final EntityManager entityManager;
    private User subscribedUser;

    public SubscribeController(TwitubModel model, EntityManager entityManager) {
        this.database = model.getDatabase();
        this.mObservers = new HashSet<>();
        this.entityManager = entityManager;
    }

    public void createNewUser(String name, String tag, String password, String confirmation) {
        if (tag.equals("") ||  name.equals("")  ) {
            mObservers.forEach(e -> e.notifyErrorSubscription("Tous les champs doivents être rempli"));
            return;
        }
        if (!password.equals(confirmation)) {
            mObservers.forEach(e -> e.notifyErrorSubscription("Le mot de passe et la confirmation sont differente"));
            return;
        }


        Set<User> existedUsers = database.getUsers();

        for (User user : existedUsers) {
            if (user.getUserTag().equals(tag)) {
                mObservers.forEach(e -> e.notifyErrorSubscription("L'utilisateur avec le tag @" + tag + " existe déja"));
                return;
            }
        }

        subscribedUser = new User(UUID.randomUUID(), tag, password, name, new HashSet<>(), null);
        entityManager.sendUser(subscribedUser);
        mObservers.forEach(e -> e.notifySubscribed(subscribedUser));
    }


    public void addObserver(ISubscribeObserver observer) {
        this.mObservers.add(observer);

        // Notification pour le nouvel observateur
        if (subscribedUser != null) {
            observer.notifySubscribed(subscribedUser);
        }
    }

    public void goBack() {
        mObservers.forEach(ISubscribeObserver::notifySubscribeBack);
    }
}
