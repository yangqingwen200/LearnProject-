package com.atguigu.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepsotory extends JpaRepository<Person, Integer>, PersonDao {

}
 