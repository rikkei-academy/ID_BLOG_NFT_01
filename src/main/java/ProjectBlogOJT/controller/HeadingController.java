package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Heading;
import ProjectBlogOJT.model.service.HeadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/heading")
public class HeadingController {
    @Autowired
    HeadingService headingService;

    @GetMapping
    public Heading getHeading(){
        return headingService.getHeading(1);
    }
    @PostMapping
    public Heading createHeading(@RequestBody Heading heading){
        heading.setHeadStatus(true);
        return headingService.createHeading(heading);
    }
    @PostMapping("update")
    public Heading updateHeading( @RequestBody Heading heading){
        Heading headingUpdate = headingService.findByID(1);
        headingUpdate.setHeadTitle(heading.getHeadTitle());
        headingUpdate.setHeadContent(heading.getHeadContent());
        headingUpdate.setHeadStatus(true);
        return headingService.updateHeading(headingUpdate);
    }

}
