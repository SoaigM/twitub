package com.ubo.tp.twitub.ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Fenetre principal
     */
    protected JFrame mFrame;

    public void showGUI() {
        // Init auto de l'IHM au cas ou ;)
        if (mFrame == null) {
            this.initGUI();
        }

        // Affichage dans l'EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Custom de l'affichage
                JFrame frame = TwitubMainView.this.mFrame;
                frame.setLocation((screenSize.width - frame.getWidth()) / 2,
                        (screenSize.height - frame.getHeight()) / 3);

                // Affichage
                TwitubMainView.this.mFrame.setVisible(true);
            }
        });
    }


    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        // Création de la fenetre principale
        mFrame = new JFrame("twItUb");
        mFrame.setLayout(new BorderLayout());
        mFrame.setSize(new Dimension((int) (screenSize.width / 1.5), (int) (screenSize.height / 1.5)));
        try {
            mFrame.setIconImage(ImageIO.read(new File("src/main/resources/images/logo_20.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Création de la barre de menu
        JMenuBar mMenuBar = new JMenuBar();

        // Ajout de la fenêtre "fichier" a la barre de menu
        JMenu mMenuFichier = new JMenu("fichier");
        JMenuItem mMenuItemQuit = new JMenuItem("QUITTER");
        mMenuItemQuit.setToolTipText("Quitter l'application");
        mMenuItemQuit.setIcon(new ImageIcon("src/main/resources/images/exitIcon_20.png"));
        mMenuItemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        mMenuFichier.add(mMenuItemQuit);

        // Ajout de la fenêtre "?" a la barre de menu
        JMenu mMenuHelp = new JMenu("?");
        JMenuItem mMenuAbout = new JMenuItem("A propos");
        mMenuAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mFrame, "UBO M1 TIIL\nDépartmement Informatique", "A propos", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/main/resources/images/logo_50.jpg"));
            }
        });
        mMenuHelp.add(mMenuAbout);

        mMenuBar.add(mMenuFichier);
        mMenuBar.add(mMenuHelp);

        mFrame.setJMenuBar(mMenuBar);
    }


    public void setComponent(Component component) {
        mFrame.getContentPane().removeAll();
        mFrame.getContentPane().add(component);
        mFrame.validate();
        mFrame.repaint();
    }
}
