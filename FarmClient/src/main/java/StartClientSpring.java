import controller.LoginView;
import controller.MainViewFarmacist;
import controller.MainViewPersonalM;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.Services;

public class StartClientSpring  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        Services services = (Services) factory.getBean("farmacieSpitalService");

    try{
        MainViewFarmacist mainViewFarmacist = new MainViewFarmacist(services);
        MainViewPersonalM mainViewPersonalM = new MainViewPersonalM(services);
        LoginView loginView = new LoginView(primaryStage,services,mainViewFarmacist.getMainFarmacistController(),mainViewPersonalM.getMainPersonalMController());

        loginView.showView();
    } catch (Exception e) {
        e.printStackTrace();
    }


    }
    public static void main(String[] args) {
        launch(args);
    }

}
