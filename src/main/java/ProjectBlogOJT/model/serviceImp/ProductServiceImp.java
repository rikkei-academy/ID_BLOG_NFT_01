package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.repository.ProductRepository;
import ProjectBlogOJT.model.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductSevice {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByID(int productID) {
        return productRepository.findById(productID).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(int productID) {
      productRepository.deleteById(productID);
    }

    @Override
    public List<Product> searchByName(String productName, Integer min, Integer max) {
        return productRepository.findProductByProductNameContainingAndProductPriceBetween(productName,min,max);
    }
    @Override
    public List<Product> searchByPrice(int price) {
        return productRepository.findProductByProductPrice(price);
    }
    @Override
    public Page<Product> getPagging(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> sortByPrice(String directionPrice) {
        if (directionPrice.equals("asc")) {
            return productRepository.findAll(Sort.by("productPrice"));
        } else {
            return productRepository.findAll(Sort.by("productPrice").descending());
        }
    }
}
