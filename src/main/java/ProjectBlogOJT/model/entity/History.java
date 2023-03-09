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
    @JsonFormat(pattern = "YYYY-MM-DDTHH:mm:ss.sssZ")
    private Date historyDataTime;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    private Product product;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;
}
