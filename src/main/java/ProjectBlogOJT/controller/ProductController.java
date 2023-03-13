package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.entity.Tag;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.ProductSevice;
import ProjectBlogOJT.model.service.TagService;
import ProjectBlogOJT.model.service.UserService;
import ProjectBlogOJT.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductSevice productSevice;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;

    @GetMapping()
    public List<Product> productList() {
        List<Product> productList = productSevice.findAll();
        return productList;
    }

    @GetMapping("/searchProduct/{productName}")
    public List<Product> searchByProductName(@PathVariable("productName") String productName,
                                             @PathVariable("productName") Integer min,
                                             @PathVariable("productName") Integer max) {
        List<Product> productCreat = productSevice.searchByName(productName, min, max);
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
    public Product creatProduct(@RequestBody Product productCreate) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByID(userDetails.getUserId());
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < productCreate.getListTag().size(); i++) {
            if (tagService.findByTagName(String.valueOf(productCreate.getListTag().get(i))) != null) {
                Tag tagFind = tagService.findByTagName(String.valueOf(productCreate.getListTag().get(i)));
                tagList.add(tagFind);
            } else {
                Tag tag = new Tag();
                tag.setTagName(String.valueOf(productCreate.getListTag().get(i)));
                tag.setTagStatus(true);
                tagList.add(tagService.save(tag));
            }
        }
        productCreate.setProductAuthor(user.getUserFullName());
        productCreate.setListTag(tagList);
        return productSevice.save(productCreate);
    }

    @PostMapping("buyProduct/{productID}")
    public Product buyProduct(@PathVariable("productID") int productID) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByID(userDetails.getUserId());
        Product productBuy = productSevice.findByID(productID);
        productBuy.setUser(user);
        return productSevice.saveOrUpdate(productBuy);
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

    @GetMapping("/getByTagName/{tagName}")
    public ResponseEntity<Map<String, Object>> getByTagName(@PathVariable("tagName") String tagName,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam String direction) {
        Tag tag = tagService.findByTagName(tagName);
        Sort.Order order;
        if (direction.equals("asc")) {
            order = new Sort.Order(Sort.Direction.ASC, "productPrice");
        } else {
            order = new Sort.Order(Sort.Direction.DESC, "productPrice");
        }

        Pageable pageable = PageRequest.of(page, 2, Sort.by(order));
        Page<Product> pageProduct = productSevice.getProductByTagName(tag, pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("blogs", pageProduct.getContent());
        data.put("total", pageProduct.getSize());
        data.put("totalItems", pageProduct.getTotalElements());
        data.put("totalPages", pageProduct.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
