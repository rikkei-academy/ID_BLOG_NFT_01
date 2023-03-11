package ProjectBlogOJT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


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
    private LocalDate exhibitionCreatedDate;

    @Column(name = "exhibitionExpiredDate")
    private LocalDate exhibitionExpiredDate;

    @Column(name = "exhibitionStatus")
    private boolean exhibitionStatus;
    @ManyToOne
    @JoinColumn(name = "Product")
    private Product product;

    @ManyToMany
    @JoinTable(name= "Exhibition_Tag", joinColumns = @JoinColumn(name = "exhibitionID"), inverseJoinColumns = @JoinColumn(name="tagID"))
    private List<Tag> listTag;

}
