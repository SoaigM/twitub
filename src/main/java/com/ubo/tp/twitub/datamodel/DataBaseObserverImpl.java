package main.java.com.ubo.tp.twitub.datamodel;

public class DataBaseObserverImpl implements IDatabaseObserver {
    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        System.out.println("twit added: " + addedTwit.getUuid() + " -> \"" + addedTwit.getText() + "\" from @" + addedTwit.getTwiter().getUserTag());
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        System.out.println("twit deleted: " + deletedTwit.getUuid() + " -> \"" + deletedTwit.getText() + "\" from @" + deletedTwit.getTwiter().getUserTag());
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        System.out.println("twit modified: " + modifiedTwit.getUuid() + " -> \"" + modifiedTwit.getText() + "\" from @" + modifiedTwit.getTwiter().getUserTag());
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        System.out.println("user added: @" + addedUser.getUserTag() + " / " + addedUser.getName());

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("user deleted: @" + deletedUser.getUserTag() + " / " + deletedUser.getName());
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("user modified: @" + modifiedUser.getUserTag() + " / " + modifiedUser.getName() + " follows: " + modifiedUser.getFollows());
    }
}
