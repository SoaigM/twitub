package com.ubo.tp.twitub.ihm.view.components;

import javax.swing.*;
import java.awt.*;

public class JTwitCreation extends JPanel {


    private final JLabel sizeLabel;
    private final String sizeText = "taile du text: ";
    private final JLabel twitLabel;
    private final JTextArea twit;
    private final JScrollPane twitScrollPane;
    private String sizeValue;


    public JTwitCreation() {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
        twitLabel = new JLabel("twit:");
        twit = new JTextArea(10, 15);
        sizeLabel = new JLabel(sizeText + "0");
        twitScrollPane = new JScrollPane(twit);


        twit.addCaretListener(e -> {
            sizeValue = String.valueOf(twit.getText().length());
            sizeLabel.setText(sizeText + sizeValue);
        });


        GridBagConstraints constraints;
        constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
        this.add(twitLabel, constraints);
        constraints = new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
        this.add(sizeLabel, constraints);
        constraints = new GridBagConstraints(0, 3, 0, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(twitScrollPane, constraints);
    }

    public String getText() {
        return twit.getText();

    }


    public void setText(String text) {
        twit.setText("");
    }
}
