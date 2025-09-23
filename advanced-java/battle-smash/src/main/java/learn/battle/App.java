package learn.battle;

import learn.battle.environment.Battle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("battle-config.xml");
        System.out.println("How many players would you like (2 or 4)?");
        int player = Integer.parseInt(new Scanner(System.in).nextLine());
        Battle battle;
        if (player == 2){
            battle = context.getBean("twoPlayer",Battle.class);
        } else if (player == 4) {
            battle = context.getBean("fourPlayer",Battle.class);
        }else {
            battle = context.getBean("threePlayer",Battle.class);
        }
        battle.run();
    }
}
