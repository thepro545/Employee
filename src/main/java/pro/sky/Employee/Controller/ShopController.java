package pro.sky.Employee.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Employee.Service.ShopService;

import java.util.Set;

@RestController
@RequestMapping("/store")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping(path = "/order/add")
    public Set<Integer> addItems(@RequestParam Set<Integer> id){
        return shopService.addItems(id);
    }

    @GetMapping(path = "/order/get")
    public Set<Integer> getItems(){
        return shopService.getItems();
    }
}
