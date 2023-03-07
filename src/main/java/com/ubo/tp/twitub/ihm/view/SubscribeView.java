package main.java.com.ubo.tp.twitub.ihm.view;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controller.SubscribeController;
import main.java.com.ubo.tp.twitub.observer.ISubscribeObserver;

import javax.swing.*;
import java.awt.*;

public class SubscribeView implements ITwitubView, ISubscribeObserver {

    private final JPanel panel;

    private final SubscribeController controller;
    private JLabel errorLabel;


    public SubscribeView(SubscribeController controller) {
        this.controller = controller;
        this.panel = new JPanel();
        this.panel.setBackground(Color.LIGHT_GRAY);
        this.panel.setLayout(new GridBagLayout());
    }

    @Override
    public void initComponent() {
        JButton subscriptionButton = new JButton("inscription");
        JButton backButton = new JButton("retour");
        JTextField loginField = new JTextField();
        JLabel loginLabel = new JLabel("login");
        JTextField tagField = new JTextField();
        JLabel tagLabel = new JLabel("tag");
        JPasswordField passwordField = new JPasswordField();
        JLabel passwordLabel = new JLabel("password");
        JPasswordField passwordConfirmationField = new JPasswordField();
        JLabel passwordConfirmationLabel = new JLabel("confirmation");
        errorLabel = new JLabel("");

        JPanel buttonPanel = new JPanel(new BorderLayout());
        subscriptionButton.addActionListener(e -> controller.createNewUser(loginField.getText(), tagField.getText(), String.valueOf(passwordField.getPassword()), String.valueOf(passwordConfirmationField.getPassword())));
        backButton.addActionListener(e -> controller.goBack());
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(subscriptionButton, BorderLayout.CENTER);

        GridBagConstraints constraints;
        constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(loginLabel, constraints);
        constraints = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(loginField, constraints);
        constraints = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(tagLabel, constraints);
        constraints = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(tagField, constraints);
        constraints = new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(passwordLabel, constraints);
        constraints = new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(passwordField, constraints);
        constraints = new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(passwordConfirmationLabel, constraints);
        constraints = new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(passwordConfirmationField, constraints);
        constraints = new GridBagConstraints(0, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(buttonPanel, constraints);
        constraints = new GridBagConstraints(0, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 64, 0, 64), 0, 0);
        this.panel.add(errorLabel, constraints);

    }

    @Override
    public Component getPrintableComponent() {
        return panel;
    }

    @Override
    public void notifySubscribed(User subscribedUser) {
    }

    @Override
    public void notifyErrorSubscription(String error) {
        errorLabel.setText(error);
    }

    @Override
    public void notifySubscribeBack() {

    }
}
