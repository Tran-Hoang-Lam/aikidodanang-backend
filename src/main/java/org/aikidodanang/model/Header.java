package org.aikidodanang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header {
    private static final String SPACE = " ";
    public static final Header DEFAULT_HEADER;
    static{
        DEFAULT_HEADER = new Header();
        DEFAULT_HEADER.setMeta(SPACE);
        DEFAULT_HEADER.setSubHeading(SPACE);
        DEFAULT_HEADER.setHeading(SPACE);
        DEFAULT_HEADER.setClassHeading("site-heading");
        DEFAULT_HEADER.setImage("https://res.cloudinary.com/dqbdhavya/image/upload/v1527360946/new-aikido-cover.jpg");
    }

    private String page;
    private String classHeading;
    private String image;
    private String heading;
    private String subHeading;
    private String meta;
}
