package main.java.com.ubo.tp.twitub.ihm.model;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.observer.IHomePageModelObserver;
import main.java.com.ubo.tp.twitub.observer.ITwitNotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class HomePageModel implements ITwitNotificationObserver, IDatabaseObserver {

    protected User session;
    protected IDatabase database;
    private List<IHomePageModelObserver> mObservers;
    private int nbFollowedTwits;
    private int notification;

    public HomePageModel(User session, IDatabase database, int nbFollowedTwits) {
        this.session = session;
        this.database = database;
        this.nbFollowedTwits = nbFollowedTwits;
        this.mObservers=new ArrayList<>();
    }

    public User getSession() {
        return session;
    }

    public void setSession(User session) {
        this.session = session;
    }

    public IDatabase getDatabase() {
        return database;
    }

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    public int getNbFollowedTwits() {
        return nbFollowedTwits;
    }


    public void addObserver(IHomePageModelObserver observer) {
        this.mObservers.add(observer);
    }

    @Override
    public void notifyNewFollowedTwit() {
        this.nbFollowedTwits++;
        this.mObservers.forEach(IHomePageModelObserver::notifyAddNotify);

    }

    public void setNotification(int notification) {
        this.notification=notification;
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

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        if(modifiedUser.equals(this.session)){
            this.session=modifiedUser;
        }
    }
}
