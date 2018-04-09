package com.learningstuff.librarymanagentsystem.Model.Dao;

import com.learningstuff.librarymanagentsystem.Model.IssueBookHistory;
import com.learningstuff.librarymanagentsystem.Model.ReturnBookHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReturnBookHistoryDao extends CrudRepository<ReturnBookHistory, Integer> {
}
