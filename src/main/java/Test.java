import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Hardware;
import ru.xgame.pavlovo.model.Sale;
import ru.xgame.pavlovo.model.User;
import ru.xgame.pavlovo.service.AdminService;
import ru.xgame.pavlovo.service.HardwareService;
import ru.xgame.pavlovo.service.SaleService;
import ru.xgame.pavlovo.service.UserService;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        UserService userService = new UserService(factory);

        AdminService adminService = new AdminService(factory);

        SaleService saleService = new SaleService(factory);

        HardwareService hardwareService = new HardwareService(factory);

        List<Hardware> users = hardwareService.findAll();

        for(Hardware user : users){
            System.out.println(user);
        }
    }
}
