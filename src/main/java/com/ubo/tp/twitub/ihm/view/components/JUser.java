package com.ubo.tp.twitub.ihm.view.components;

import com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JUser extends JPanel {


    private final JLabel name;
    private final JLabel tag;
    private final ImageIcon avatar;

    public JUser(User user) {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(new LineBorder(Color.cyan));
        name = new JLabel(user.getName());
        tag = new JLabel("@" + user.getUserTag());
        avatar = new ImageIcon("src/main/resources/images/téléchargement.png");
        JLabel avatarLabel = new JLabel(avatar);


        this.add(avatarLabel);
        this.add(name);
        this.add(tag);
    }
}
