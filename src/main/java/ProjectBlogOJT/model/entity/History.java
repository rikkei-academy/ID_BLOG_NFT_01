package ProjectBlogOJT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Historys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historyID")
    private int historyID;

    @Column(name = "historyDataTime")
    private Date historyDataTime;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
}
