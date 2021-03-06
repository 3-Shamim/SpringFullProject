package com.learningstuff.librarymanagentsystem.Model.Dao;

import com.learningstuff.librarymanagentsystem.Model.IssueBookHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IssuedBookHistoryDao extends CrudRepository<IssueBookHistory, Integer> {
}
