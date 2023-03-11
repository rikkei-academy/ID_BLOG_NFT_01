package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.ExhibitionService;
import ProjectBlogOJT.payload.request.CommentCreate;
import ProjectBlogOJT.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/exhibition")
public class ExhibitionController {
    @Autowired
    ExhibitionService exhibitionService;
    @GetMapping()
    public List<Exhibition> exhibitionsList() {
        List<Exhibition> exhibitions = exhibitionService.findAll();
        return exhibitions;
    }
    @PostMapping("/delete/{exhibitionID}")
    public Exhibition delete(@PathVariable("exhibitionID") int exhibitionID) {
        Exhibition exhibition = exhibitionService.findByID(exhibitionID);
        if (exhibition.isExhibitionStatus() == true) {
            exhibition.setExhibitionStatus(false);
        } else {
            exhibition.setExhibitionStatus(true);
        }
        return  exhibitionService.saveOrUpdate(exhibition);
    }
    @PostMapping
    public Exhibition createComment(@RequestBody Exhibition exhibition) {

        return exhibitionService.saveOrUpdate(exhibition);
    }

    @PutMapping("/update/{exhibitionID}")
    public Exhibition updateComment(@PathVariable("exhibitionID") int exhibitionID, @RequestBody Exhibition exhibition) {
        Exhibition exhibition1 = exhibitionService.findByID(exhibitionID);
        exhibition1.setExhibitionDescription(exhibition.getExhibitionDescription());
        exhibition1.setExhibitionTitle(exhibition1.getExhibitionTitle());
        return exhibitionService.saveOrUpdate(exhibition1);
    }
    @GetMapping("/sortBetween")
    public ResponseEntity<Map<String, Object>> listExhibition(@RequestParam String form,
                                                           @RequestParam String to,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam String direction){
        LocalDate fromDate = LocalDate.parse(form);
        LocalDate toDate = LocalDate.parse(to);
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"exhibitionDate");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"exhibitionDate");
        }
        Pageable pageable = PageRequest.of(page, 2,Sort.by(order));
        Page<Exhibition> pageComment = exhibitionService.sortBetween(fromDate,toDate,pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("comment", pageComment.getContent());
        data.put("total", pageComment.getSize());
        data.put("totalItems", pageComment.getTotalElements());
        data.put("totalPages", pageComment.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }



}
