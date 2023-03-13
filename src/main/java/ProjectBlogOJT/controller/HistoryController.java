package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.entity.History;
import ProjectBlogOJT.model.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping()
    public List<History> exhibitionsList() {
        List<History> histories = historyService.findAll();
        return histories;
    }
    @PostMapping("/deleteHistory/{historyID}")
    public ResponseEntity<String> delete(@PathVariable("historyID") int historyID) {
        historyService.delete(historyID);
        return  ResponseEntity.ok("Delete complete !");
    }
    @PostMapping
    public History create(@RequestBody History history) {
        return historyService.saveOrUpdate(history);
    }

    @GetMapping("sort")
    public List<History> historiesListSort(@RequestParam String direction){
        return historyService.sortByDateCreate(direction);
    }

}
