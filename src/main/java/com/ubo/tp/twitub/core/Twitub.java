package com.ubo.tp.twitub.core;

import com.ubo.tp.twitub.datamodel.DataBaseObserverImpl;
import com.ubo.tp.twitub.datamodel.Database;
import com.ubo.tp.twitub.datamodel.IDatabase;
import com.ubo.tp.twitub.events.file.IWatchableDirectory;
import com.ubo.tp.twitub.events.file.WatchableDirectory;
import com.ubo.tp.twitub.ihm.ChangePageController;
import com.ubo.tp.twitub.ihm.TwitubMainView;
import com.ubo.tp.twitub.ihm.TwitubMock;
import com.ubo.tp.twitub.ihm.TwitubModel;
import com.ubo.tp.twitub.ihm.controller.LoginController;
import com.ubo.tp.twitub.ihm.view.LoginView;
import com.ubo.tp.twitub.ihm.view.TestView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitub {
    /**
     * Base de données.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire des entités contenu de la base de données.
     */
    protected EntityManager mEntityManager;

    /**
     * Vue principale de l'application.
     */
    protected TwitubMainView mMainView;

    /**
     * Classe de surveillance de répertoire
     */
    protected IWatchableDirectory mWatchableDirectory;

    /**
     * Répertoire d'échange de l'application.
     */
    protected String mExchangeDirectoryPath;

    /**
     * Idnique si le mode bouchoné est activé.
     */
    protected boolean mIsMockEnabled = true;

    protected boolean test = false;

    /**
     * Nom de la classe de l'UI.
     */
    protected String mUiClassName;

    /**
     * Constructeur.
     */
    public Twitub() {
        // Init du look and feel de l'application
        this.initLookAndFeel();

        // Initialisation de la base de données
        this.initDatabase();

        if (this.mIsMockEnabled) {
            // Initialisation du bouchon de travail
            this.initMock();
        }

        // Initialisation du répertoire d'échange
        this.initDirectory();

        // Initialisation de l'IHM
        this.initGui();

        if (test) {

            TestView testView = new TestView();
            testView.initComponent();
            mMainView.setComponent(testView.getPrintableComponent());
        } else {

            TwitubModel model = new TwitubModel();
            model.setUser(null);
            model.setDatabase(mDatabase);
            LoginController loginController = new LoginController(model);
            loginController.addObserver(new ChangePageController(mMainView, model, mEntityManager));

            LoginView loginView = new LoginView(loginController);
            loginController.addObserver(loginView);
            loginView.initComponent();
            mMainView.setComponent(loginView.getPrintableComponent());
        }

    }

    /**
     * Initialisation du look and feel de l'application.
     */
    protected void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Initialisation de l'interface graphique.
     */
    protected void initGui() {
        this.mMainView = new TwitubMainView();
        this.mMainView.showGUI();
    }

    /**
     * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
     * chooser). <br/>
     * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
     * pouvoir utiliser l'application</b>
     */
    protected void initDirectory() {
        JFileChooser choose = new JFileChooser(
                FileSystemView
                        .getFileSystemView()
                        .getHomeDirectory()
        );
        choose.setDialogTitle("Choisir le repertoir d'échange");
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = choose.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            if (isValideExchangeDirectory(choose.getSelectedFile().getAbsoluteFile())) {
                initDirectory(choose.getSelectedFile().getPath());
            } else {
                JOptionPane.showMessageDialog(null, "Répertoire invalide", "Erreure", JOptionPane.PLAIN_MESSAGE);
                initDirectory();
            }
        }
        if (res == JFileChooser.CANCEL_OPTION) {
            int i = JOptionPane.showOptionDialog(null, "Quitter", "Quitter ?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, Arrays.asList("OUI", "Annuler").toArray(), 0);
            if (i == 0) {
                System.exit(1);
            } else {
                initDirectory();
            }
        }
    }

    /**
     * Indique si le fichier donné est valide pour servire de répertoire
     * d'échange
     *
     * @param directory , Répertoire à tester.
     */
    protected boolean isValideExchangeDirectory(File directory) {
        // Valide si répertoire disponible en lecture et écriture
        return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
                && directory.canWrite();
    }

    /**
     * Initialisation du mode bouchoné de l'application
     */
    protected void initMock() {
        TwitubMock mock = new TwitubMock(this.mDatabase, this.mEntityManager);
        mock.showGUI();
    }

    /**
     * Initialisation de la base de données
     */
    protected void initDatabase() {
        mDatabase = new Database();
        mDatabase.addObserver(new DataBaseObserverImpl());
        mEntityManager = new EntityManager(mDatabase);
    }

    /**
     * Initialisation du répertoire d'échange.
     *
     * @param directoryPath
     */
    public void initDirectory(String directoryPath) {
        mExchangeDirectoryPath = directoryPath;
        mWatchableDirectory = new WatchableDirectory(directoryPath);
        mEntityManager.setExchangeDirectory(directoryPath);

        mWatchableDirectory.initWatching();
        mWatchableDirectory.addObserver(mEntityManager);
    }

    public void show() {
        // ... setVisible?
    }
}
