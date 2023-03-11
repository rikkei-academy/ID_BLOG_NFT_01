package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {
    Page<Exhibition> findExhibitionByExhibitionCreatedDate(LocalDate from, LocalDate to, Pageable pageable);

}
