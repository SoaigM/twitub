package com.ubo.tp.twitub.ihm.controller;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import com.ubo.tp.twitub.datamodel.Twit;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.ihm.model.HomePageModel;
import com.ubo.tp.twitub.observer.IHomePageModelObserver;
import com.ubo.tp.twitub.observer.ILoginObserver;
import com.ubo.tp.twitub.observer.ITwitNotificationObserver;
import com.ubo.tp.twitub.observer.ITwitObserver;

import java.util.HashSet;
import java.util.Set;

public class HomePageController implements IDatabaseObserver, IHomePageModelObserver {

    protected final Set<ILoginObserver> mLoginObservers;

    protected final Set<ITwitObserver> mTwitObservers;
    protected final Set<ITwitNotificationObserver> mTwitNotificationObservers;
    private final HomePageModel model;
    private final EntityManager entityManager;
    protected boolean disconnected = false;
    protected Twit twit = null;
    protected int nbFollowedTwits;
    protected int nbReadedTwits;

    public HomePageController(HomePageModel model, EntityManager entityManager, int nbReadedTwits) {
        this.model = model;
        this.mLoginObservers = new HashSet<>();
        this.mTwitObservers = new HashSet<>();
        this.mTwitNotificationObservers = new HashSet<>();
        this.nbFollowedTwits = 0;
        this.entityManager = entityManager;
        this.nbReadedTwits = nbReadedTwits;
    }

    public void disconnect() {
        disconnected = true;
        mLoginObservers.forEach(ILoginObserver::notifyBackToConnectionPage);
    }


    public void createTwit(String twitText) {
        if (twitText.equals("")) {
            mTwitObservers.forEach(e -> e.notifyErrorTwit(""));
            return;
        }
        if (twitText.length() > 250) {
            mTwitObservers.forEach(e -> e.notifyErrorTwit("Longueur du twit = " + twitText.length() + ". max 250"));
            return;
        }
        this.twit = new Twit(model.getSession(), twitText);
        entityManager.sendTwit(this.twit);
        mTwitObservers.forEach(e -> e.notifyNewTwit(this.twit));
    }


    public void addObserver(ILoginObserver observer) {
        this.mLoginObservers.add(observer);

        // Notification pour le nouvel observateur
        if (disconnected) {
            observer.notifyBackToConnectionPage();
        }
    }

    public void addObserver(ITwitObserver observer) {
        this.mTwitObservers.add(observer);

        // Notification pour le nouvel observateur
        if (twit != null) {
            observer.notifyNewTwit(twit);
        }
    }

    public void addObserver(ITwitNotificationObserver observer) {
        this.mTwitNotificationObservers.add(observer);
    }

    public User getSession() {
        return model.getSession();
    }

    public void askListTwits() {
        this.mTwitObservers.forEach(ITwitObserver::notifyAskTwitsList);
    }

    public void askListUsers() {
        this.mTwitObservers.forEach(ITwitObserver::notifyAskUsersList);
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        String twitUserTag = addedTwit.getTwiter().getUserTag();
        if (model.getSession().getFollows().contains(twitUserTag)) {
            mTwitNotificationObservers.forEach(ITwitNotificationObserver::notifyNewFollowedTwit);
        }
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        /*if(modifiedUser.equals(this.model.getSession())){
            Set<String> newSet = modifiedUser.getFollows();
            Set<String> oldSet = this.model.getSession().getFollows();
            newSet.removeAll(oldSet);
            newSet.forEach(twit -> );
        }*/
    }

    @Override
    public void notifyAddNotify() {
        this.nbFollowedTwits++;
        if((nbFollowedTwits-nbReadedTwits)>0){
            mTwitObservers.forEach(e->e.notifyNotification(nbFollowedTwits-nbReadedTwits));
        }
    }
}
