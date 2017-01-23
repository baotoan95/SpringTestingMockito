package com.btit95.sample.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.btit95.sample.entities.ToDo;
import com.btit95.sample.repositories.ToDoRepository;

@Controller
@RequestMapping("todo")
public class ToDoController {
	@Autowired
	private ToDoRepository toDoRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String todoList(ModelMap model) {
		model.addAttribute("todoList", toDoRepository.findAll());
		return "todo_list";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addToDo(ModelMap model) {
		model.addAttribute("todo", new ToDo());
		return "edit_todo";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addToDo(@ModelAttribute("todo") ToDo toDo, ModelMap model) {
		toDo.setCreationTime(new Date());
		toDoRepository.save(toDo);
		return "redirect:/todo";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateToDo(@PathVariable("id") int id, ModelMap model) {
		ToDo todo = toDoRepository.findOne(id);
		model.addAttribute("todo", todo);
		return "edit_todo";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateToDo(@ModelAttribute("todo") ToDo toDo, ModelMap model) {
		ToDo oldVersion = toDoRepository.findOne(toDo.getId());
		toDo.setCreationTime(oldVersion.getCreationTime());
		toDoRepository.save(toDo);
		return "redirect:/todo";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteToDo(@PathVariable("id") int id, ModelMap model) {
		toDoRepository.delete(id);
		return "redirect:/todo";
	}
}
