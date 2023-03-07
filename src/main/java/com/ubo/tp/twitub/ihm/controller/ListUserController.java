package com.ubo.tp.twitub.ihm.controller;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.IDatabase;
import com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import com.ubo.tp.twitub.datamodel.Twit;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.ihm.model.ListUsersModel;
import com.ubo.tp.twitub.ihm.view.ListUsersView;
import com.ubo.tp.twitub.observer.IListUserObserver;

import java.util.HashSet;
import java.util.Set;

public class ListUserController implements IDatabaseObserver, IListUserObserver {


    protected final ListUsersView view;

    protected final ListUsersModel model;
    private final IDatabase database;
    private final EntityManager entityManager;

    public ListUserController(IDatabase database, ListUsersModel model, ListUsersView view, EntityManager entityManager) {
        this.database = database;
        this.view = view;
        this.model = model;
        this.entityManager = entityManager;
    }

    public void searchUsers() {
        model.setListUsers(database.getUsers());
    }


    @Override
    public void notifyListUserBack() {

    }

    @Override
    public void notifySearch(String text) {
        Set<User> allUsers = database.getUsers();
        Set<User> res = new HashSet<>();
        for (User user : allUsers) {
            if (user.getName().contains(text)) {
                res.add(user);
            }
        }
        model.setListUsers(res);
    }

    @Override
    public void notifyFollow(User userClicked) {
        Set<String> follows = model.getSession().getFollows();
        if (follows.contains(userClicked.getUserTag())) {
            model.getSession().removeFollowing(userClicked.getUserTag());
        } else {
            model.getSession().addFollowing(userClicked.getUserTag());
        }
        entityManager.sendUser(model.getSession());
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        this.searchUsers();

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        this.searchUsers();
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        this.searchUsers();
    }


    /*public void addObserver(ILoginObserver observer) {
        this.mObservers.add(observer);

        // Notification pour le nouvel observateur
        if (user != null) {
            observer.notifyLoggedUser(user);
        }
    }*/
}
