package sn.eath.eshop.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sn.eath.eshop.entity.Product;
import sn.eath.eshop.exception.product.ProductGetException;
import sn.eath.eshop.exception.product.ProductNotFoundException;
import sn.eath.eshop.repository.ProductRepository;
import sn.eath.eshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product produit) {
        return productRepository.save(produit);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        try {
            return Optional.of(productRepository.findAll(pageable))
                    .orElseThrow(() -> new ProductNotFoundException("No Product found"));
        } catch (Exception e) {
            throw new ProductGetException("Error while getting Products", e);
        }
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product non trouvé"));
    }

    @Override
    public List<Product> searchProducts(String nom, String categorie) {
        return productRepository.findByNomContainingIgnoreCaseOrCategoryNomContainingIgnoreCase(nom, categorie);
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
