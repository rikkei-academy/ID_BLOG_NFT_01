package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductSevice {
    List<Product> findAll();
    Product findByID(int productID);
    Product save(Product product);
    Product saveOrUpdate(Product product);
    void delete(int productID);
    List<Product> searchByName(String productName, Integer min, Integer max);
    List<Product> searchByPrice(int price);

    Page<Product> getPagging(Pageable pageable);

    List<Product> sortByPrice(String directionPrice);
    Page<Product> getProductByTagName(Tag tag , Pageable pageable);


}
