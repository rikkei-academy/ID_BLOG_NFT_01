package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.*;
import ProjectBlogOJT.model.service.ExhibitionService;
import ProjectBlogOJT.model.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/exhibition")
public class ExhibitionController {
    @Autowired
    ExhibitionService exhibitionService;
    @Autowired
    TagService tagService;
    @GetMapping()
    public List<Exhibition> exhibitionsList() {
        List<Exhibition> exhibitions = exhibitionService.findAll();
        return exhibitions;
    }
    @PostMapping("/delete/{exhibitionID}")
    public Exhibition delete(@PathVariable("exhibitionID") int exhibitionID) {
        Exhibition exhibition = exhibitionService.findByID(exhibitionID);
        if (exhibition.isExhibitionStatus()) {
            exhibition.setExhibitionStatus(false);
        } else {
            exhibition.setExhibitionStatus(true);
        }
        return  exhibitionService.saveOrUpdate(exhibition);
    }
    @PostMapping
    public Exhibition create(@RequestBody Exhibition exhibition) {
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < exhibition.getListTag().size(); i++) {
            if(tagService.findByTagName(String.valueOf(exhibition.getListTag().get(i)))!=null){
                Tag tagFind = tagService.findByTagName(String.valueOf(exhibition.getListTag().get(i)));
                tagList.add(tagFind);
            }else {
                Tag tag = new Tag();
                tag.setTagName(String.valueOf(exhibition.getListTag().get(i)));
                tag.setTagStatus(true);
                tagList.add(tagService.save(tag));
            }
        }
        exhibition.setListTag(tagList);
        return exhibitionService.saveOrUpdate(exhibition);
    }
    @PutMapping("/update/{exhibitionID}")
    public Exhibition updateComment(@PathVariable("exhibitionID") int exhibitionID, @RequestBody Exhibition exhibition) {
        Exhibition exhibitionUpdate = exhibitionService.findByID(exhibitionID);
        exhibitionUpdate.setExhibitionDescription(exhibition.getExhibitionDescription());
        exhibitionUpdate.setExhibitionTitle(exhibition.getExhibitionTitle());
        exhibitionUpdate.setExhibitionExpiredDate(exhibition.getExhibitionExpiredDate());
        exhibitionUpdate.setListTag(exhibition.getListTag());
        return exhibitionService.saveOrUpdate(exhibitionUpdate);
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
            order=new Sort.Order(Sort.Direction.ASC,"exhibitionCreatedDate");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"exhibitionCreatedDate");
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
    @GetMapping("sort")
    public List<Exhibition> exhibitionListSort(@RequestParam String direction){
        return exhibitionService.sortByDateCreate(direction);
    }
}
