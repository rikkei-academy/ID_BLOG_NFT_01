package ProjectBlogOJT.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagID")
    private int tagID;

    @Column(name = "tagName")
    private String tagName;

    @Column(name = "tagStatus")
    private Boolean tagStatus;

    @Override
    public String toString(){
        return this.getTagName();
    }

}
