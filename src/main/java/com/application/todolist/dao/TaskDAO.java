package com.application.todolist.dao;

import java.lang.reflect.Parameter;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.application.todolist.vo.Task;

@Repository
public class TaskDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TaskDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> listAll(){
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, taskRowMapper);
    }
    public int addTask(Task task) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("description", task.getDescription());
        params.addValue("completed", task.isCompleted());
        params.addValue("createdDate", task.getCreatedDate());

        String sql = " INSERT INTO tasks (description, completed, created_date) " +
                    " VALUES (:description, :completed, :createdDate) RETURNING id ";

        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    public int updateTask(Task task) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", task.getId());
        params.addValue("description", task.getDescription());
        params.addValue("completed", task.isCompleted());
        params.addValue("createdDate", task.getCreatedDate());

        String sql = " UPDATE tasks SET description = :description, completed = :completed, created_date = :createdDate " +
                    " WHERE id = :id ";

        return jdbcTemplate.update(sql, params);
    }

    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setDescription(rs.getString("description"));
        task.setCompleted(rs.getBoolean("completed"));
        task.setCreatedDate(rs.getDate("created_date"));
        return task;
    };
}
