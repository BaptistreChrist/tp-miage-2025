package com.acme.todolist.adapters.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.acme.todolist.application.port.out.LoadTodoItem;
import com.acme.todolist.application.port.out.UpdateTodoItem;
import com.acme.todolist.domain.TodoItem;

/**
 * Implémentation JPA des ports out de persistence
 *
 * @author bflorat
 */
@Component
public class TodoItemPersistenceAdapter implements LoadTodoItem, UpdateTodoItem {

	private final TodoItemRepository todoItemRepository;
	private final TodoItemMapper mapper;

	@Inject
	public TodoItemPersistenceAdapter(TodoItemRepository todoItemRepository, TodoItemMapper mapper) {
		this.todoItemRepository = todoItemRepository;
		this.mapper = mapper;
	}

	@Override
	public List<TodoItem> loadAllTodoItems() {
		return this.todoItemRepository.findAll().stream()
				.map(mapper::mapToTodoItem)
				.collect(Collectors.toList());
	}

	@Override
	public void storeNewTodoItem(TodoItem item) {
		TodoItemJpaEntity entity = mapper.mapToTodoItemJpaEntity(item);
		todoItemRepository.save(entity);
	}
}