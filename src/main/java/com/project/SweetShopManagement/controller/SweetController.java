package com.project.SweetShopManagement.controller;


import com.project.SweetShopManagement.model.Sweet;
import com.project.SweetShopManagement.service.SweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController
{
    private final SweetService sweetService;

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSweet")
    public Sweet addSweet(@RequestBody Sweet sweet)
    {
        return sweetService.addSweet(sweet);
    }

    // 👥 USER + ADMIN
    @GetMapping("/getSweet")
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }

    // 👥 USER + ADMIN
    @GetMapping("/{id}")
    public Sweet getSweet(@PathVariable Long id) {
        return sweetService.getSweetById(id);
    }

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatedSweet/{id}")
    public Sweet updateSweet(@PathVariable Long id, @RequestBody Sweet sweet) {
        return sweetService.updateSweet(id, sweet);
    }

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteSweet/{id}")
    public String deleteSweet(@PathVariable Long id) {
        sweetService.deleteSweet(id);
        return "Sweet deleted successfully";
    }

    // 👥 USER + ADMIN
    @GetMapping("/search")
    public List<Sweet> searchSweets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return sweetService.searchSweets(name, category, minPrice, maxPrice);
    }

    // 👥 USER + ADMIN
    @PostMapping("/{id}/purchase")
    public Sweet purchaseSweet(@PathVariable Long id, @RequestParam int quantity)
    {
        return sweetService.purchaseSweet(id, quantity);
    }

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/restock")
    public Sweet restockSweet(@PathVariable Long id, @RequestParam int quantity) {
        return sweetService.restockSweet(id, quantity);
    }
}
