import controller.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.Services;

public class StartClientSpring  extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        Services services = (Services) factory.getBean("farmacieSpitalService");

        LoginView loginView = new LoginView(primaryStage,services);
        loginView.showView();

    }
}
