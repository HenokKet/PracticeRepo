package learn.battle.announcers;

import java.util.Random;

public class Mumbler implements Announcer {

    private static final String MUMBLE_LETTERS = "MmUuBbLl";
    private final Random random = new Random();

    @Override
    public void announce(String message) {
        StringBuilder buffer = new StringBuilder(100);

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                buffer.append(MUMBLE_LETTERS.charAt(random.nextInt(MUMBLE_LETTERS.length())));
            } else {
                buffer.append(c);

            }
        }

        System.out.println(buffer);
    }
}
