package main.java.com.ubo.tp.twitub.ihm.controller;

import main.java.com.ubo.tp.twitub.common.TwitComparator;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.model.ListTwitModel;
import main.java.com.ubo.tp.twitub.ihm.view.ListTwitView;
import main.java.com.ubo.tp.twitub.observer.IListTwitObserver;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ListTwitController implements IDatabaseObserver, IListTwitObserver {


    protected final ListTwitView view;

    protected final ListTwitModel model;
    private final IDatabase database;

    public ListTwitController(IDatabase database, ListTwitModel model, ListTwitView view) {
        this.database = database;
        this.view = view;
        this.model = model;
    }

    public void searchTwits() {
        Set<Twit> twits = new TreeSet<>(new TwitComparator());
        twits.addAll(database.getTwits());
        model.setTwits(twits);
    }


    @Override
    public void notifySearch(String text) {
        Set<Twit> res = new TreeSet<>(new TwitComparator());

        if (text.startsWith("@")) {
            res.addAll(searchUsers(text));
        } else if (text.startsWith("#")) {
            res.addAll(searchTag(text));
        } else {
            res.addAll(searchUsers(text));
            res.addAll(searchTag(text));
        }
        model.setTwits(res);
    }

    private Set<Twit> searchUsers(String text) {
        String tag = text.replace("@", "");
        Set<Twit> res = new HashSet<>();
        Set<Twit> allTwits = database.getTwits();
        for (Twit twit : allTwits) {
            if (twit.getTwiter().getUserTag().equals(tag) || twit.containsUserTag(tag)) {
                res.add(twit);
            }
        }
        return res;
    }

    private Set<Twit> searchTag(String text) {
        String tag = text.replace("#", "");
        Set<Twit> res = new HashSet<>();
        Set<Twit> allTwits = database.getTwits();
        for (Twit twit : allTwits) {
            if (twit.containsTag(tag)) {
                res.add(twit);
            }
        }
        return res;
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        this.searchTwits();
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        this.searchTwits();
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        this.searchTwits();
    }

    @Override
    public void notifyUserAdded(User addedUser) {

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }

    @Override
    public void notifyListTwitBack() {

    }

    /*public void addObserver(ILoginObserver observer) {
        this.mObservers.add(observer);

        // Notification pour le nouvel observateur
        if (user != null) {
            observer.notifyLoggedUser(user);
        }
    }*/
}
