package com.example.exbeginner.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbeginner.model.Item;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ex06")
public class ShoppingCartController {
    @Autowired
    private ServletContext application;
    @Autowired
    private HttpSession session;

    /**
     * 商品一覧、カート一覧を表示する.
     * @param model
     * @return item-and-cart.html
     */
    @GetMapping("/cart")
    public String index(Model model) {
        
        // アプリケーションスコープが空だった場合にitemsをセット
        if(application.getAttribute("items") == null) {
            LinkedList<Item> items = new LinkedList<>();
            items.add(new Item("⼿帳ノート", 1000));
            items.add(new Item("⽂房具セット", 1500));
            items.add(new Item("ファイル", 2000));
    
            application.setAttribute("items", items);
        }

        // セッションスコープが空だった場合に、空の配列をセット
		if (session.getAttribute("cartItemList") == null) {
			session.setAttribute("cartItemList", new LinkedList<>());
		}

        // 金額を計算.
        @SuppressWarnings("unchecked") 
        Integer totalPrice = calcPrice((List<Item>) session.getAttribute("cartItemList"));
        model.addAttribute("totalPrice", totalPrice);
        
        return "Ex06/item-and-cart";
    }

    /**
     * ショッピングカートに商品を追加する.
     * @param items
     * @return indexメソッド
     */
    @PostMapping({"/inCart","/inCart/"})
    public String inCart(Integer index, Model model) {
        // インデックス番号からカートに入れたい情報を取得.
        @SuppressWarnings("unchecked") 
        List<Item> items = (List<Item>) application.getAttribute("items");
        Item item = items.get(index);

        // カートにアイテムを格納.
        @SuppressWarnings("unchecked")
		List<Item> cartItemList = (List<Item>) session.getAttribute("cartItemList");
		cartItemList.add(item);
        
        // 金額を計算.
        @SuppressWarnings("unchecked") 
        Integer totalPrice = calcPrice((List<Item>) session.getAttribute("cartItemList"));
        model.addAttribute("totalPrice", totalPrice);
        
        return "redirect:/ex06/cart";
    }

    /**
     * カートの中身を削除する.
     * @param index
     * @return インデックスメソッド
     */
    @PostMapping({"/deleteCart", "/deleteCart/"})
    public String deleteCart(Integer index) {
        @SuppressWarnings("unchecked")
        List<Item> cartItemList = (List<Item>) session.getAttribute("cartItemList");
        Item item = cartItemList.get(index);
        cartItemList.remove(item);
        return "redirect:/ex06/cart";
    }

    /**
     * カート内の合計金額を計算する.
     * @param items
     * @return カート内の合計金額
     */
    public Integer calcPrice(List<Item> items) {
        Integer totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
