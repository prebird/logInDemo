package com.example.logindemo.web.item;

import com.example.logindemo.constant.CookieConst;
import com.example.logindemo.domain.item.DeliveryCode;
import com.example.logindemo.domain.item.Item;
import com.example.logindemo.domain.item.ItemRepository;
import com.example.logindemo.domain.item.ItemType;
import com.example.logindemo.web.commonUtil.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린배송"));
        return deliveryCodes;
    }

    @PreAuthorize("hasRole('USER')")    // apply spring-security login filter
    @GetMapping()
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model,
                       HttpServletRequest request, HttpServletResponse response) {
        Item item = itemRepository.findById(itemId);

        // 최근 조회한 상품 기능
        Cookie viewItmesCookie = CookieUtils.findCookieOrCreateNew(request, CookieConst.VIEW_ITEMS); // 1-2-
        String viewItemsStr = viewItmesCookie.getValue();
        boolean exists = false;

        if (viewItemsStr != null && viewItemsStr.indexOf(itemId + "-") > 0) {
            exists = true;
        }

        if (exists == false) {
            viewItemsStr += (itemId + "-");
            viewItmesCookie.setValue(viewItemsStr);
            viewItmesCookie.setMaxAge(60*60*24);
            viewItmesCookie.setPath("/");
            response.addCookie(viewItmesCookie);
        }

        model.addAttribute("item", item);
        return "item/item";
    }



    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item" , new Item());
        /*
        Map<String, String> regions = new LinkedHashMap();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        model.addAttribute("region", regions);
        */
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/items/{itemId}";
    }

}

