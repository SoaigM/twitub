package com.ubo.tp.twitub.ihm.view.components;

import com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JTwit extends JPanel {


    private final JLabel twiterName;
    private final JLabel twitLabel;
    private final JLabel twitDate;


    public JTwit(Twit twit) {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());

        twiterName = new JLabel();
        twiterName.setText("@" + twit.getTwiter().getUserTag() + " | " + twit.getTwiter().getName());

        twitDate = new JLabel();
        twitDate.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(twit.getEmissionDate())));

        JPanel twitPLane = new JPanel();
        twitPLane.setLayout(new GridBagLayout());
        twitPLane.setBackground(Color.white);
        twitPLane.setBorder(new LineBorder(Color.cyan));
        twitLabel = new JLabel();
        twitLabel.setBackground(Color.white);
        twitLabel.setText(twit.getText());
        twitPLane.add(twitLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));


        GridBagConstraints constraints;
        constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0);
        this.add(twiterName, constraints);
        constraints = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0);
        this.add(twitDate, constraints);
        constraints = new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0);
        this.add(twitPLane, constraints);
    }

}
