package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Tag;
import ProjectBlogOJT.model.repository.TagRepository;
import ProjectBlogOJT.model.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TagServiceImp implements TagService {
    @Autowired
    TagRepository tagRepository;


    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findByID(int tagID) {
        return tagRepository.findById(tagID).get();
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(int tagID) {
        tagRepository.deleteById(tagID);
    }

    @Override
    public Tag findByTagName(String tagName) {
        return tagRepository.findTagByTagName(tagName);
    }


}
