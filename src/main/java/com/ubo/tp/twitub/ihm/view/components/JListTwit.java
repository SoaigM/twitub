package com.ubo.tp.twitub.ihm.view.components;

import com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class JListTwit extends JPanel {


    private final JScrollPane twitScroll;
    private final JPanel twitsPanel;


    public JListTwit(Set<Twit> twits) {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
        twitsPanel = new JPanel();
        twitsPanel.setBackground(Color.LIGHT_GRAY);
        twitsPanel.setLayout(new GridBagLayout());


        GridBagConstraints constraints;
        constraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0);
        for (Twit twit : twits) {
            twitsPanel.add(new JTwit(twit), constraints);
            constraints.gridy++;
        }

        twitScroll = new JScrollPane(twitsPanel);
        this.add(twitScroll, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    }

}
