package com.ubo.tp.twitub.ihm;

import com.ubo.tp.twitub.datamodel.IDatabase;
import com.ubo.tp.twitub.datamodel.User;

public class TwitubModel  {

    private User user;
    private IDatabase database;

    private int nbFollowedTwits;

    public TwitubModel() {
        this.nbFollowedTwits = 0;
    }

    public int getNbFollowedTwits() {
        return nbFollowedTwits;
    }

    public void setNbFollowedTwits(int nbFollowedTwits) {
        this.nbFollowedTwits = nbFollowedTwits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IDatabase getDatabase() {
        return database;
    }

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

}
