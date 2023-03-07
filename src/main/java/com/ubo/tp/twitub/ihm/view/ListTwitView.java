package main.java.com.ubo.tp.twitub.ihm.view;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.model.ListTwitModel;
import main.java.com.ubo.tp.twitub.ihm.view.components.JListTwit;
import main.java.com.ubo.tp.twitub.observer.IListTwitObserver;
import main.java.com.ubo.tp.twitub.observer.ITwitModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListTwitView implements ITwitubView, ITwitModelObserver {

    private final JPanel panel;
    private final ListTwitModel model;
    private JPanel searchPanel;
    private final List<IListTwitObserver> mObservers;

    public ListTwitView(ListTwitModel model) {
        mObservers = new ArrayList<>();
        this.panel = new JPanel();
        this.panel.setBackground(Color.lightGray);
        this.panel.setLayout(new GridBagLayout());
        this.model = model;
    }

    @Override
    public void initComponent() {
        Set<Twit> listTwit = model.getTwits();

        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        JButton back = new JButton("retour");
        JButton search = new JButton("chercher");
        JTextField recherche = new JTextField();


        back.addActionListener(l -> mObservers.forEach(IListTwitObserver::notifyListTwitBack));
        search.addActionListener(l -> mObservers.forEach(e -> e.notifySearch(recherche.getText())));

        searchPanel.add(back, BorderLayout.WEST);
        searchPanel.add(recherche, BorderLayout.CENTER);
        searchPanel.add(search, BorderLayout.EAST);
        paintListTwits(listTwit);


    }

    public void paintListTwits(Set<Twit> twits) {
        panel.removeAll();
        this.panel.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(new JListTwit(twits), new GridBagConstraints(0, 1, 5, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel.setVisible(false);
        panel.setVisible(true);
    }

    @Override
    public Component getPrintableComponent() {
        return panel;
    }

    @Override
    public void notifyTwitsChanged(Set<Twit> twits) {
        paintListTwits(twits);
    }


    public void addObserver(IListTwitObserver observer) {
        this.mObservers.add(observer);
    }
}
