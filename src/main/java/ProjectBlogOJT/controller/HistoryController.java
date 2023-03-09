package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;


}
