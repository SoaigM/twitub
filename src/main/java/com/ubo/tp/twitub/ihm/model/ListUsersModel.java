package main.java.com.ubo.tp.twitub.ihm.model;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.observer.IUserModelObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUsersModel implements IDatabaseObserver {

    private final List<IUserModelObserver> mObservers;
    private Set<User> users;
    private User session;


    public ListUsersModel(User user) {
        mObservers = new ArrayList<>();
        users = new HashSet<>();
        this.session = user;
    }

    public User getSession() {
        return session;
    }

    public Set<User> getUsers() {
        this.users.remove(session);
        return users;
    }

    public void setListUsers(Set<User> users) {
        this.users = users;
        this.users.remove(session);
        mObservers.forEach(e -> e.notifyUsersChanged(users));
    }

    public void addObserver(IUserModelObserver observer) {
        this.mObservers.add(observer);
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
        if (modifiedUser.equals(this.session)) {
            this.session = modifiedUser;
            mObservers.forEach(e -> e.notifySessionChanged());
        }
    }

}
