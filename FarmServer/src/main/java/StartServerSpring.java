import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.FarmacieSpitalService;

public class StartServerSpring {


    public static void main(String[] args) {
        ApplicationContext factory  = new ClassPathXmlApplicationContext("classpath:FarmacieSpitalConfig-SpringServer.xml");
        System.out.println("waiting for clients ... ");

//        FarmacieSpitalService service = factory.getBean(FarmacieSpitalService.class);

    }
}
