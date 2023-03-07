package main.java.com.ubo.tp.twitub.ihm.view;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controller.LoginController;
import main.java.com.ubo.tp.twitub.observer.ILoginObserver;

import javax.swing.*;
import java.awt.*;

public class LoginView implements ITwitubView, ILoginObserver {

    private final JPanel panel;
    private final LoginController controller;
    private JLabel errorLabel;

    public LoginView(LoginController controller) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.GRAY);
        this.panel.setLayout(new GridBagLayout());
        this.controller = controller;
    }

    @Override
    public void initComponent() {
        JButton connectionButton = new JButton("connection");
        JButton subscriptionButton = new JButton("inscription");
        JTextField loginField = new JTextField();
        JLabel loginLabel = new JLabel("login");
        JPasswordField passwordField = new JPasswordField();
        JLabel passwordLabel = new JLabel("password");
        errorLabel = new JLabel("");

        connectionButton.addActionListener(e -> controller.doLogin(loginField.getText(), String.valueOf(passwordField.getPassword())));
        subscriptionButton.addActionListener(e -> controller.subscribe());

        GridBagConstraints constraints;
        constraints = new GridBagConstraints(2, 0, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(loginLabel, constraints);
        constraints = new GridBagConstraints(2, 1, 0, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(loginField, constraints);
        constraints = new GridBagConstraints(2, 2, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(passwordLabel, constraints);
        constraints = new GridBagConstraints(2, 3, 0, 1, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(passwordField, constraints);
        constraints = new GridBagConstraints(2, 4, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 0, 64), 0, 0);
        this.panel.add(connectionButton, constraints);
        constraints = new GridBagConstraints(2, 5, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 64, 64), 0, 0);
        this.panel.add(subscriptionButton, constraints);
        constraints = new GridBagConstraints(2, 6, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 64, 64), 0, 0);
        this.panel.add(subscriptionButton, constraints);
        constraints = new GridBagConstraints(2, 7, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 64, 64, 64), 0, 0);
        this.panel.add(errorLabel, constraints);

    }

    @Override
    public Component getPrintableComponent() {
        return panel;
    }

    @Override
    public void notifyLoggedUser(User user) {

    }

    @Override
    public void notifyBackToConnectionPage() {

    }

    @Override
    public void notifyAskSubscription() {

    }

    @Override
    public void notifyErrorLogin(String error) {
        errorLabel.setText(error);
    }
}
