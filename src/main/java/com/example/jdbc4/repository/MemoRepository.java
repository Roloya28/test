package com.example.jdbc4.repository;

import com.example.jdbc4.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo); // Create
    Optional<Memo> findById(Long id); // Read 단건 조회
    List<Memo> findAll(); // Read 다건 조회
    Memo updateContent(Long id, String content); // Update
    void deleteById(Long id); // Delete
}
