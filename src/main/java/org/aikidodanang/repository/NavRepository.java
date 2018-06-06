package org.aikidodanang.repository;

import org.aikidodanang.model.Nav;
import org.aikidodanang.model.NavItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NavRepository extends MongoRepository<Nav, String> {
    Nav findByPage(String page);
    @Query("{ $and: [ { 'page': ?#{[0]} }, { 'navItem.link': ?#{[1]} } ] }")
    NavItem retrieveByPageAndLink(String page, String link);
}
