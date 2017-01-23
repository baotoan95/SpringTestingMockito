package com.btit95.sample.repositories;

import org.springframework.data.repository.CrudRepository;

import com.btit95.sample.entities.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, Integer>{

}
