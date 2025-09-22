package learn.battle;

import learn.battle.environment.Battle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("battle-config.xml");
        Battle battle = context.getBean(Battle.class);
        battle.run();
    }
}
