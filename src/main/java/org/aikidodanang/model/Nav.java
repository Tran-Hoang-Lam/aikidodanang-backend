package org.aikidodanang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "navs")
public class Nav {
    @Id
    private String id;
    @Field("page")
    private String page;
    @Field("navItem")
    private List<NavItem> navItem;
}
