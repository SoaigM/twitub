package main.java.com.ubo.tp.twitub.common;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import java.util.Comparator;
import java.util.Date;

public  class TwitComparator implements Comparator<Twit> {
    @Override
    public int compare(Twit t1, Twit t2) {
        return new Date(t2.getEmissionDate()).compareTo(new Date(t1.getEmissionDate()));
    }
}
