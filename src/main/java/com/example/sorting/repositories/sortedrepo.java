package com.example.sorting.repositories;

import com.example.sorting.Entity.SortedObj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface sortedrepo extends JpaRepository<SortedObj, Long> {
}
