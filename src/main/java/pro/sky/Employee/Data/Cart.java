package pro.sky.Employee.Data;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class Cart {
    private final Set<Integer> items;

    public Cart(){
        this.items = new HashSet<>();
    }

    public Set<Integer> addItems(Set<Integer> id){
        items.addAll(id);
        return id;
    }
    public Set<Integer> getItems() {
        return Collections.unmodifiableSet(items);
    }
}
