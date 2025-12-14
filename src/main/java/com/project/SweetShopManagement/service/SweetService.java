package com.project.SweetShopManagement.service;

import com.project.SweetShopManagement.model.Sweet;
import com.project.SweetShopManagement.repository.SweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetService
{
    private final SweetRepository sweetRepository;

    public Sweet addSweet(Sweet sweet)
    {
        int quantity = sweet.getQuantity();
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        return sweetRepository.save(sweet);
    }

    public List<Sweet> getAllSweets()
    {
        return sweetRepository.findAll();
    }

    public Sweet getSweetById(Long id)
    {
        return sweetRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + id));
    }

    public Sweet updateSweet(Long id, Sweet sweetDetails)
    {
        Sweet sweet = getSweetById(id);
        sweet.setName(sweetDetails.getName());
        sweet.setCategory(sweetDetails.getCategory());
        sweet.setPrice(sweetDetails.getPrice());
        sweet.setQuantity(sweetDetails.getQuantity());
        return sweetRepository.save(sweet);
    }

    public void deleteSweet(Long id)
    {
        Sweet sweet = getSweetById(id);
        sweetRepository.delete(sweet);
    }

    public List<Sweet> searchSweets(String name, String category, Double minPrice, Double maxPrice)
    {
        if (name != null)
        {
            return sweetRepository.findByNameContainingIgnoreCase(name);
        }
        if (category != null)
        {
            return sweetRepository.findByCategoryContainingIgnoreCase(category);
        }
        if (minPrice != null && maxPrice != null)
        {
            return sweetRepository.findByPriceBetween(minPrice, maxPrice);
        }
        return getAllSweets();
    }

    public Sweet purchaseSweet(Long id, int quantity)
    {
            Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        // ❌ WRONG CODE (no negative check)
        if (sweet.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        sweet.setQuantity(sweet.getQuantity() - quantity);
        return sweetRepository.save(sweet);
    }

    public Sweet restockSweet(Long id, int quantity)
    {
        Sweet sweet = getSweetById(id);
        sweet.setQuantity(sweet.getQuantity() + quantity);
        return sweetRepository.save(sweet);
    }
}


