package sn.eath.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.eath.eshop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNomContainingIgnoreCaseOrCategoryNomContainingIgnoreCase(String nom, String category);
}
