package ProjectBlogOJT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private int productID;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productAuthor")
    private String productAuthor;

    @Column(name = "productPrice")
    private int productPrice;

    @Column(name = "ProductImage")
    private String productImage;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productCreateDate")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:SS")
    private Date productCreateDate;

    @Column(name= "productExpiredAuctionDate")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:SS")
    private Date productExpiredAuctionDate;

    @Column(name = "productStatus")
    private boolean productStatus;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToMany
    @JoinTable(name= "Product_Tag", joinColumns = @JoinColumn(name = "productID"), inverseJoinColumns = @JoinColumn(name="tagID"))
    private List<Tag> listTag;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "exhibitionID")
    private Exhibition exhibition;


}
