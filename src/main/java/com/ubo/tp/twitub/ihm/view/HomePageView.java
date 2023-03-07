package main.java.com.ubo.tp.twitub.ihm.view;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.controller.HomePageController;
import main.java.com.ubo.tp.twitub.ihm.view.components.JTwitCreation;
import main.java.com.ubo.tp.twitub.ihm.view.components.JUser;
import main.java.com.ubo.tp.twitub.observer.ITwitObserver;

import javax.swing.*;
import java.awt.*;

public class HomePageView implements ITwitubView, ITwitObserver {

    private final JPanel panel;
    private final HomePageController controller;
    private JLabel errorLabel;
    private JTwitCreation twitText;
    private JLabel notification;

    public HomePageView(HomePageController controller) {
        this.controller = controller;
        this.panel = new JPanel();
        this.panel.setBackground(Color.LIGHT_GRAY);
        this.panel.setLayout(new GridBagLayout());
    }

    @Override
    public void initComponent() {
        JButton sendButton = new JButton("envoyer");
        JButton disconnectButton = new JButton("Deconnexion");
        JButton listTwitsButton = new JButton("Twits");
        JButton listUsersButton = new JButton("Twiters");
        twitText = new JTwitCreation();
        errorLabel = new JLabel("");
        notification = new JLabel("Twits non lu: 0");
        JUser userPanel = new JUser(controller.getSession());

        sendButton.addActionListener(e -> controller.createTwit(twitText.getText()));
        disconnectButton.addActionListener(e -> controller.disconnect());
        listTwitsButton.addActionListener(e -> controller.askListTwits());
        listUsersButton.addActionListener(e -> controller.askListUsers());


        GridBagConstraints constraints;
        constraints = new GridBagConstraints(0, 0, 1, 1, 5, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(userPanel, constraints);
        constraints = new GridBagConstraints(10, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(disconnectButton, constraints);
        constraints = new GridBagConstraints(9, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(listTwitsButton, constraints);
        constraints = new GridBagConstraints(8, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(listUsersButton, constraints);
        constraints = new GridBagConstraints(0, 1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(twitText, constraints);
        constraints = new GridBagConstraints(0, 4, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(sendButton, constraints);
        constraints = new GridBagConstraints(0, 6, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(errorLabel, constraints);
        constraints = new GridBagConstraints(0, 7, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(notification, constraints);

    }

    @Override
    public Component getPrintableComponent() {
        return panel;
    }


    @Override
    public void notifyNewTwit(Twit twit) {
        twitText.setText("");
        errorLabel.setText("");

    }

    @Override
    public void notifyErrorTwit(String error) {
        errorLabel.setText(error);
    }

    @Override
    public void notifyAskTwitsList() {

    }

    @Override
    public void notifyAskUsersList() {

    }

    @Override
    public void notifyNotification(int i) {
        notification.setText("Twits non lu: "+i);
    }
}
