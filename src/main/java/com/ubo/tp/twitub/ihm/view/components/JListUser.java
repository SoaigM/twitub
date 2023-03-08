package com.ubo.tp.twitub.ihm.view.components;

import com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class JListUser extends JPanel {


    private final Set<User> users;
    private final User session;
    MouseListener clickUser = new MouseAdapter() {
    };
    private JScrollPane userScroll;
    private User clickedUser = null;


    public JListUser(Set<User> users, User session) {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
        this.users = users;
        this.session = session;
        initComponent();

    }

    public void initComponent() {
        JPanel usersPanel = new JPanel();
        usersPanel.setBackground(Color.LIGHT_GRAY);
        usersPanel.setLayout(new GridBagLayout());


        GridBagConstraints constraints;
        constraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0);
        for (User user : users) {
            JUser jUser = new JUser(user);
            if (session.getFollows().contains(user.getUserTag()))
                jUser.setBackground(Color.CYAN);
            jUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickedUser = user;
                    clickUser.mouseClicked(e);
                }
            });
            usersPanel.add(jUser, constraints);
            constraints.gridy++;
        }

        userScroll = new JScrollPane(usersPanel);
        this.add(userScroll, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    }

    public void addClickOnUser(MouseAdapter mouseAdapter) {
        this.clickUser = mouseAdapter;
        this.removeAll();
        initComponent();
    }

    public User getUserClicked() {
        return clickedUser;
    }
}
