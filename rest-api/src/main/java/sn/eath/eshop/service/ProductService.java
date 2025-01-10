package sn.eath.eshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.eath.eshop.entity.Product;

import java.util.List;

@Service
public interface ProductService {
    Product saveProduct(Product produit);

    Page<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long id);

    List<Product> searchProducts(String nom, String categorie);

    void deleteProduct(Long id);
}
