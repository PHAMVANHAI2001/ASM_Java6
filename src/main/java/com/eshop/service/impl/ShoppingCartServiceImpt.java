//package com.eshop.service.impl;
//
//import com.eshop.entities.ShoppingCart;
//import com.eshop.service.ShoppingCartService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.annotation.SessionScope;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//@SessionScope
//@Service
//public class ShoppingCartServiceImpt implements ShoppingCartService {
//    // Dùng HashMap để chứa danh sách món hàng
//    Map<Integer, ShoppingCart> map = new HashMap<>();
//
//    // Hàm thêm item vào giỏ hàng dựa vào id
//    @Override
//    public ShoppingCart add(Integer id) {
//        // Kiểm tra item này có trong giỏ hàng chưa
//        ShoppingCart item = map.get(id);
//        // Nếu chưa có thì item = null
//        if(item == null) {
//            // lấy món hàng từ danh sách dựa vào id
//            item = DB.items.get(id);
//            // set số lượng mặc định là 1
//            item.setQty(1);
//            // Thêm món hàng vào giỏ hàng
//            map.put(id, item);
//        }else {
//            item.setQty(item.getQty() + 1);
//        }
////		System.out.println(map.size());
//        return item;
//    }
//
//    @Override
//    public void remove(Integer id) {
//        map.remove(id);
//    }
//
//    @Override
//    public ShoppingCart update(Integer id, int qty) {
//        ShoppingCart item = map.get(id);
//        item.setQty(qty);
//        return item;
//    }
//
//    @Override
//    public void clear() {
//        map.clear();
//    }
//
//    @Override
//    public Collection<ShoppingCart> getItems() {
//        return map.values();
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public double getAmount() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//}
