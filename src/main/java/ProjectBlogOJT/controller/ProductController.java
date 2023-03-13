package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductSevice productSevice;

    @GetMapping()
    public List<Product> productList() {
        List<Product> productList = productSevice.findAll();
        return productList;
    }

    @GetMapping("/searchProduct/{productName}")
    public List<Product> searchByProductName(@PathVariable("productName") String productName,
                                             @PathVariable("productName") Integer min,
                                             @PathVariable("productName") Integer max) {
        List<Product> productCreat = productSevice.searchByName(productName,min,max);
        return productCreat;
    }

    @PostMapping("/delete/{productID}")
    public Product delete(@PathVariable("productID") int productID) {
        Product product = productSevice.findByID(productID);
        if (product.isProductStatus() == true) {
            product.setProductStatus(false);
        } else {
            product.setProductStatus(true);
        }
        return productSevice.saveOrUpdate(product);
    }

    @PostMapping
    public Product creatProduct(@RequestBody Product productCreata) {
        Product product = productSevice.save(productCreata);
        return product;
    }

    @PutMapping("/update/{productID}")
    public Product updateProdutc(@PathVariable("productID") int productID, @RequestBody Product product) {
        Product productUpdate = productSevice.findByID(productID);
        productUpdate.setProductName(product.getProductName());
        productUpdate.setProductAuthor(product.getProductAuthor());
        productUpdate.setProductDescription(product.getProductDescription());
        productUpdate.setProductCreateDate(product.getProductCreateDate());
        productUpdate.setProductPrice(product.getProductPrice());
        productUpdate.setProductImage(product.getProductImage());
        return productSevice.saveOrUpdate(productUpdate);
    }
    @GetMapping("/getPagging")
    public ResponseEntity<Map<String, Object>> getPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> pageBook = productSevice.getPagging(pageable);


        Map<String, Object> data = new HashMap<>();
        data.put("product", pageBook.getContent());
        data.put("total", pageBook.getSize());
        data.put("totalItems", pageBook.getTotalElements());
        data.put("totalPages", pageBook.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/sortByPrice")
    public ResponseEntity<List<Product>> sortBookByNameAndPrice(@RequestParam("directionPrice") String directionPrice) {
        List<Product> productList = productSevice.sortByPrice(directionPrice);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/sortByCreateDate")
    public ResponseEntity<List<Product>> sortByProductCreateDate(@RequestParam String direction){
        List<Product> listProduct = productSevice.sortByCreateDate(direction);
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }

}
