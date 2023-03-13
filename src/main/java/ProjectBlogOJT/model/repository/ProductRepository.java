package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findProductByProductNameContainingAndProductPriceBetween(String productName,int min, int max);
  List<Product> findProductByProductPrice(Integer price);
  Page<Product> findProductByListTagContaining(Tag tag, Pageable pageable);

}
