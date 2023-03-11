package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    Page<History> findHistoriesByHistoryDataTime (LocalDate from, LocalDate to, Pageable pageable);

}
