package com.ubo.tp.twitub.ihm.view;

import com.ubo.tp.twitub.datamodel.Twit;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.ihm.view.components.JListTwit;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestView implements ITwitubView {

    private final JPanel panel;

    public TestView() {
        this.panel = new JPanel();
        this.panel.setBackground(Color.GRAY);
        this.panel.setLayout(new GridBagLayout());
    }

    @Override
    public void initComponent() {


        User test = new User(UUID.randomUUID(), "GOULVEN22", "osqidk", "TOM_20", new HashSet<>(), "avatar");
        Twit twit = new Twit(test, "hello ");
        Twit twit1 = new Twit(test, " wordl");
        Twit twit2 = new Twit(test, "hello wordl");
        Twit twit3 = new Twit(test, "damng");
        Twit twit4 = new Twit(test, "a");
        Twit twit5 = new Twit(test, "osjdfmlqsdjf");
        Twit twit6 = new Twit(test, "aqsd");
        Twit twit7 = new Twit(test, "bq");
        Set<Twit> listTwit = new HashSet<>();
        listTwit.add(twit);
        listTwit.add(twit1);
        listTwit.add(twit2);


        panel.add(new JListTwit(listTwit), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    }

    @Override
    public Component getPrintableComponent() {
        return panel;
    }

}
