package main.java.com.ubo.tp.twitub.ihm.view;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.model.ListUsersModel;
import main.java.com.ubo.tp.twitub.ihm.view.components.JListUser;
import main.java.com.ubo.tp.twitub.observer.IListUserObserver;
import main.java.com.ubo.tp.twitub.observer.IUserModelObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListUsersView implements ITwitubView, IUserModelObserver {

    private final JPanel panel;
    private final ListUsersModel model;
    private final List<IListUserObserver> mObservers;
    private JPanel searchPanel;

    public ListUsersView(ListUsersModel model) {
        mObservers = new ArrayList<>();
        this.panel = new JPanel();
        this.panel.setBackground(Color.lightGray);
        this.panel.setLayout(new GridBagLayout());
        this.model = model;
    }

    @Override
    public void initComponent() {
        Set<User> listUser = model.getUsers();

        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        JButton back = new JButton("retour");
        JButton search = new JButton("chercher");
        JTextField recherche = new JTextField();

        back.addActionListener(l -> mObservers.forEach(IListUserObserver::notifyListUserBack));
        search.addActionListener(l -> mObservers.forEach(e -> e.notifySearch(recherche.getText())));

        searchPanel.add(back, BorderLayout.WEST);
        searchPanel.add(recherche, BorderLayout.CENTER);
        searchPanel.add(search, BorderLayout.EAST);
        paintListUsers(listUser);
    }

    public void paintListUsers(Set<User> users) {
        panel.removeAll();
        this.panel.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        JListUser jListUser = new JListUser(users, model.getSession());
        jListUser.addClickOnUser(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mObservers.forEach(l -> l.notifyFollow(jListUser.getUserClicked()));
            }
        });
        panel.add(jListUser, new GridBagConstraints(0, 1, 5, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel.setVisible(false);
        panel.setVisible(true);
    }

    @Override
    public Component getPrintableComponent() {
        return panel;
    }

    @Override
    public void notifyUsersChanged(Set<User> users) {
        paintListUsers(users);
    }

    @Override
    public void notifySessionChanged() {
        paintListUsers(model.getUsers());
    }


    public void addObserver(IListUserObserver observer) {
        this.mObservers.add(observer);
    }
}
