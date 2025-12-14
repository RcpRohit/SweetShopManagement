package com.project.SweetShopManagement.repository;

import com.project.SweetShopManagement.model.Sweet;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SweetRepository extends JpaRepository<Sweet, Integer>
{
    Optional<Sweet> findById(Long id);
    List<Sweet> findByNameContainingIgnoreCase(String name);
    List<Sweet> findByCategoryContainingIgnoreCase(String category);
    List<Sweet> findByPriceBetween(double minPrice, double maxPrice);
}
