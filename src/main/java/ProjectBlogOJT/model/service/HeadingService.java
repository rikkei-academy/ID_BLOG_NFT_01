package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Heading;

import java.util.logging.Handler;

public interface HeadingService {
    Heading getHeading (int headingID);
    Heading createHeading(Heading heading);
    Heading updateHeading(Heading heading);
    Heading findByID(int headingID);
}
