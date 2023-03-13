package ProjectBlogOJT.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Heading")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Heading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "headingID")
    private int headingID;
    @Column(name = "headTitle")
    private String headTitle;
    @Column(name = "headContent")
    private String headContent;
    @Column(name = "headStatus")
    private boolean headStatus;
}
