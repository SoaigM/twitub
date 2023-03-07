package main.java.com.ubo.tp.twitub.ihm.model;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.observer.ITwitModelObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTwitModel {

    private final List<ITwitModelObserver> mObservers;
    private Set<Twit> twits;

    public ListTwitModel() {
        mObservers = new ArrayList<>();
        twits = new HashSet<>();
    }

    public Set<Twit> getTwits() {
        return twits;
    }

    public void setTwits(Set<Twit> twits) {
        this.twits = twits;
        mObservers.forEach(e -> e.notifyTwitsChanged(twits));
    }

    public void addObserver(ITwitModelObserver observer) {
        this.mObservers.add(observer);
    }
}
