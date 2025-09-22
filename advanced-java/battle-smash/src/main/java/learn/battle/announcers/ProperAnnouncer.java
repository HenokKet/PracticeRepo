package learn.battle.announcers;

public class ProperAnnouncer implements Announcer {

    @Override
    public void announce(String message) {
        System.out.println(message);
    }
}
