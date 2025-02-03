package com.example.jdbc4.controller;

import com.example.jdbc4.dto.MemoRequestDto;
import com.example.jdbc4.dto.MemoResponseDto;
import com.example.jdbc4.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // Create API
    @PostMapping("/memos")
    public MemoResponseDto save(@RequestBody MemoRequestDto dto) {
        return memoService.save(dto);
    }

    // Read All API
    @GetMapping("/memos")
    public List<MemoResponseDto> findAll() {
        return memoService.findAll();
    }

    // Read One API
    @GetMapping("/memos/{id}")
    public MemoResponseDto findById (@PathVariable Long memoId) {
        return memoService.findById(memoId);
    }

    @PutMapping("/memos/{id}")
    public MemoResponseDto update(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto dto
    ) {
        return memoService.update(memoId, dto);
    }

    // delete API
    @DeleteMapping("/memos/{id}")
    public void delete(@PathVariable Long memoId) {
        memoService.delete(memoId);
    }
}