package ProjectBlogOJT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> feature/#670/blog-manager

@Entity
@Table(name = "Exhibition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitionID")
    private int exhibitionID;

    @Column(name = "exhibitionTitle")
    private String exhibitionTitle;

    @Column(name = "exhibitionDescription")
    private String exhibitionDescription;

    @Column(name = "exhibitionCreatedDate")
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private Date exhibitionCreatedDate;

    @Column(name = "exhibitionExpiredDate")
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private Date exhibitionExpiredDate;

    @Column(name = "exhibitionStatus")
<<<<<<< HEAD
    private Boolean exhibitionStatus;
=======
    private boolean exhibitionStatus;
    @ManyToOne
    @JoinColumn(name = "Product")
    private Product product;
>>>>>>> feature/#670/blog-manager

    @ManyToMany
    @JoinTable(name= "Exhibition_Tag", joinColumns = @JoinColumn(name = "exhibitionID"), inverseJoinColumns = @JoinColumn(name="tagID"))
    private List<Tag> listTag;

}
