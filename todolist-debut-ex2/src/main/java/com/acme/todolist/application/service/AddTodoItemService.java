package com.acme.todolist.application.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.acme.todolist.application.port.in.AddTodoItem;
import com.acme.todolist.application.port.out.UpdateTodoItem;
import com.acme.todolist.domain.TodoItem;

@Component
@RestController
@RequestMapping("/todos")
public class AddTodoItemService implements AddTodoItem {

	private final UpdateTodoItem updateTodoItem;

	@Autowired
	public AddTodoItemService(UpdateTodoItem updateTodoItem) {
		this.updateTodoItem = updateTodoItem;
	}

	@Override
	public void addTodoItem(TodoItem item) {
		if (item == null) {
			throw new IllegalArgumentException("TodoItem ne doit pas être null");
		}
		updateTodoItem.storeNewTodoItem(item);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTodoItem(@RequestBody TodoItem item) {
		addTodoItem(item);
	}
}