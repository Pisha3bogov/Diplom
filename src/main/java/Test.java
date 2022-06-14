import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.*;
import ru.xgame.pavlovo.service.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        UserService userService = new UserService(factory);

        AdminService adminService = new AdminService(factory);

        SaleService saleService = new SaleService(factory);

        HardwareService hardwareService = new HardwareService(factory);

        OrderService orderService = new OrderService(factory);

        ShopService shopService = new ShopService(factory);

        ReportsOrderService reportsOrderService = new ReportsOrderService(factory);

        ReportsService reportsService = new ReportsService(factory);

        List<Reports> users = reportsService.findAll();

        for(Reports user : users){
            System.out.println(user);
        }

//        Shop shop = shopService.findById("adrenaline");
//
//        Order order = new Order("shop",160,shop);
//
//        orderService.save(order);



    }
}
