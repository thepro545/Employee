package pro.sky.Employee.Service.Imp;

import org.springframework.stereotype.Service;
import pro.sky.Employee.Data.Cart;
import pro.sky.Employee.Service.ShopService;


import java.util.Set;

@Service
public class ShopServiceImp implements ShopService {

    private final Cart cart;

    public ShopServiceImp(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Set<Integer> addItems(Set<Integer> id){
        return cart.addItems(id);
    }

    @Override
    public Set<Integer> getItems(){
        return cart.getItems();
    }
}
