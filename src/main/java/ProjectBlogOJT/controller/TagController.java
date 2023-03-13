package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Tag;
import ProjectBlogOJT.model.repository.TagRepository;
import ProjectBlogOJT.model.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    TagService tagService;

//   @PostMapping()
//    public Tag createTag(@RequestBody Tag tag){
//null;
//   }


}
