package com.ubo.tp.twitub.ihm;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.Twit;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.ihm.controller.HomePageController;
import com.ubo.tp.twitub.ihm.controller.ListTwitController;
import com.ubo.tp.twitub.ihm.controller.ListUserController;
import com.ubo.tp.twitub.ihm.controller.LoginController;
import com.ubo.tp.twitub.ihm.controller.SubscribeController;
import com.ubo.tp.twitub.ihm.model.HomePageModel;
import com.ubo.tp.twitub.ihm.model.ListTwitModel;
import com.ubo.tp.twitub.ihm.model.ListUsersModel;
import com.ubo.tp.twitub.ihm.view.HomePageView;
import com.ubo.tp.twitub.ihm.view.ListTwitView;
import com.ubo.tp.twitub.ihm.view.ListUsersView;
import com.ubo.tp.twitub.ihm.view.LoginView;
import com.ubo.tp.twitub.ihm.view.SubscribeView;
import com.ubo.tp.twitub.observer.IListTwitObserver;
import com.ubo.tp.twitub.observer.IListUserObserver;
import com.ubo.tp.twitub.observer.ILoginObserver;
import com.ubo.tp.twitub.observer.ISubscribeObserver;
import com.ubo.tp.twitub.observer.ITwitNotificationObserver;
import com.ubo.tp.twitub.observer.ITwitObserver;

import java.util.Set;

import static com.sun.activation.registries.LogSupport.log;

public class ChangePageController implements ILoginObserver, ISubscribeObserver, ITwitObserver, IListTwitObserver, IListUserObserver {

    private final TwitubModel mainModel;
    private final EntityManager entityManager;
    TwitubMainView mainView;

    public ChangePageController(TwitubMainView mainView, TwitubModel model, EntityManager entityManager) {
        this.mainModel = model;
        this.mainView = mainView;
        this.entityManager = entityManager;
    }

    @Override
    public void notifyLoggedUser(User user) {
        mainModel.setUser(user);
        mainModel.setNbFollowedTwits(getNbFollowedTwits());
        homePage();
    }

    private void homePage(){
        HomePageModel model = new HomePageModel(mainModel.getUser(), mainModel.getDatabase(), this.mainModel.getNbFollowedTwits());
        HomePageController homePageController = new HomePageController(model, entityManager,this.mainModel.getNbFollowedTwits());
        HomePageView homePageView = new HomePageView(homePageController);
        homePageView.initComponent();
        model.addObserver(homePageController);
        homePageController.addObserver((ILoginObserver) this);
        homePageController.addObserver((ITwitObserver) this);
        homePageController.addObserver(homePageView);
        homePageController.addObserver(model);
        this.mainModel.getDatabase().addObserver(homePageController);
        mainView.setComponent(homePageView.getPrintableComponent());
    }

    private int getNbFollowedTwits(){
        Set<Twit> listTwitts = mainModel.getDatabase().getTwits();
        User session = this.mainModel.getUser();
        int res = 0;
        for(Twit twit : listTwitts){
            if (session.getFollows().contains(twit.getTwiter().getUserTag())){
                res++;
            }
        }
        return res;
    }

    @Override
    public void notifyBackToConnectionPage() {
        LoginController loginController = new LoginController(mainModel);
        loginController.addObserver(this);
        LoginView loginView = new LoginView(loginController);
        loginController.addObserver(loginView);
        loginView.initComponent();
        mainView.setComponent(loginView.getPrintableComponent());
    }

    @Override
    public void notifyAskSubscription() {
        SubscribeController subscriptionController = new SubscribeController(mainModel, entityManager);
        subscriptionController.addObserver(this);
        SubscribeView subscribeView = new SubscribeView(subscriptionController);
        subscriptionController.addObserver(subscribeView);
        subscribeView.initComponent();
        mainView.setComponent(subscribeView.getPrintableComponent());
    }

    @Override
    public void notifyErrorLogin(String error) {
        ILoginObserver.super.notifyErrorLogin(error);
    }

    @Override
    public void notifySubscribed(User subscribedUser) {
        log("user created: " + subscribedUser);
        mainModel.setUser(subscribedUser);
        LoginController loginController = new LoginController(mainModel);
        loginController.addObserver(this);
        LoginView loginView = new LoginView(loginController);
        loginController.addObserver(loginView);
        loginView.initComponent();
        mainView.setComponent(loginView.getPrintableComponent());
    }

    @Override
    public void notifyErrorSubscription(String error) {
        ISubscribeObserver.super.notifyErrorSubscription(error);
    }

    @Override
    public void notifySubscribeBack() {
        notifyBackToConnectionPage();
    }


    @Override
    public void notifyNewTwit(Twit twit) {
        ITwitObserver.super.notifyNewTwit(twit);
    }

    @Override
    public void notifyErrorTwit(String error) {
        ITwitObserver.super.notifyErrorTwit(error);
    }

    @Override
    public void notifyAskTwitsList() {
        ListTwitModel model = new ListTwitModel();
        ListTwitView view = new ListTwitView(model);
        ListTwitController controller = new ListTwitController(this.mainModel.getDatabase(), model, view);
        this.mainModel.getDatabase().addObserver(controller);
        model.addObserver(view);
        view.initComponent();
        view.addObserver(this);
        view.addObserver(controller);
        mainView.setComponent(view.getPrintableComponent());

    }

    @Override
    public void notifyAskUsersList() {
        ListUsersModel model = new ListUsersModel(this.mainModel.getUser());
        ListUsersView view = new ListUsersView(model);
        ListUserController controller = new ListUserController(this.mainModel.getDatabase(), model, view, entityManager);
        this.mainModel.getDatabase().addObserver(controller);
        model.addObserver(view);
        view.initComponent();
        view.addObserver(this);
        view.addObserver(controller);
        this.mainModel.getDatabase().addObserver(model);
        mainView.setComponent(view.getPrintableComponent());

    }

    @Override
    public void notifyNotification(int i) {
        ITwitObserver.super.notifyNotification(i);
    }

    @Override
    public void notifyListTwitBack() {
        mainModel.setNbFollowedTwits(getNbFollowedTwits());
        homePage();
    }

    @Override
    public void notifySearch(String text) {
        IListTwitObserver.super.notifySearch(text);
    }

    @Override
    public void notifyFollow(User userClicked) {
        IListUserObserver.super.notifyFollow(userClicked);
    }

    @Override
    public void notifyListUserBack() {
        homePage();

    }
}
