package com.btit95.sample.test.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.btit95.sample.entities.ToDo;
import com.btit95.sample.repositories.ToDoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:dispatcher-servlet.xml" })
public class ToDoTest {
	@Autowired
	private ToDoRepository toDoRepository;

	@Test
	public void testAddToDo() {
		Date d1 = new Date();

		// Add
		ToDo toDo1 = toDoRepository.save(new ToDo(0, "Learn English", "Listening, Writing", d1, 0));
		ToDo toDo2 = toDoRepository.save(new ToDo(0, "Write document", "Writing document for ToDo Kalban", d1, 0));
		ToDo toDo3 = toDoRepository.save(new ToDo(0, "Exercise", "Nunchaku", d1, 0));
		ToDo toDo4 = toDoRepository.save(new ToDo(0, "Coding EightPuzzle game", "Complete change between boxes", d1, 0));
		assertNotNull(toDo1);
		assertNotNull(toDo2);
		assertNotNull(toDo3);
		assertNotNull(toDo4);

		// Update
		ToDo expect = new ToDo(toDo1.getId(), "Learn English", "Reading", d1, 1);
		toDo1.setDescription("Reading");
		ToDo toDoUpdated = toDoRepository.save(toDo1);
		assertEquals(expect, toDoUpdated);

		// Delete
		toDoRepository.delete(toDo1.getId());
		ToDo td = toDoRepository.findOne(toDo1.getId());
		assertNull(td);

		// GetAll
		List<ToDo> all = (List<ToDo>) toDoRepository.findAll();
		assertEquals(3, all.size());
	}
}
