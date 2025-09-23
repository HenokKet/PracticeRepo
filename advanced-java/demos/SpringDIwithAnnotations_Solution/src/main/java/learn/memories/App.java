package learn.memories;


import learn.memories.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
@PropertySource("classpath:data.properties")
public class App {
    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}
