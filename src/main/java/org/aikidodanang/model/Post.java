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
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    @Field("title")
    private String title;
    @Field("content")
    private String content;
    @Field("userName")
    private String userName;
    @Field("heading")
    private String heading;
    @Field("subHeading")
    private String subHeading;
}
