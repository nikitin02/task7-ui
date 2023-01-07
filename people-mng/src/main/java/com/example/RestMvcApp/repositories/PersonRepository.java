package com.example.RestMvcApp.repositories;

import com.example.RestMvcApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findPersonByPhoneContainsIgnoreCase(String phone);
    @Query("select p from Person p where lower(p.last_name) like lower(concat('%', :name,'%'))")
    List<Person> findPersonByLast_nameContainsIgnoreCase(@Param("name") String name);
}
