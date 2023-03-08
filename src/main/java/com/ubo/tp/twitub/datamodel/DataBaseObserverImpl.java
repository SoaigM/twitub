package com.ubo.tp.twitub.datamodel;

import static com.sun.activation.registries.LogSupport.log;

public class DataBaseObserverImpl implements IDatabaseObserver {

    private static final String ARROW = " -> \"";
    private static final String FROM = "\" from @";

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        log("twit added: " + addedTwit.getUuid() + ARROW + addedTwit.getText() + FROM + addedTwit.getTwiter().getUserTag());
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        log("twit deleted: " + deletedTwit.getUuid() + ARROW + deletedTwit.getText() + FROM + deletedTwit.getTwiter().getUserTag());
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        log("twit modified: " + modifiedTwit.getUuid() + ARROW + modifiedTwit.getText() + FROM + modifiedTwit.getTwiter().getUserTag());
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        log("user added: @" + addedUser.getUserTag() + " / " + addedUser.getName());

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        log("user deleted: @" + deletedUser.getUserTag() + " / " + deletedUser.getName());
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        log("user modified: @" + modifiedUser.getUserTag() + " / " + modifiedUser.getName() + " follows: " + modifiedUser.getFollows());
    }
}
