package com.example.jdbc4.controller;

import com.example.jdbc4.dto.MemoRequestDto;
import com.example.jdbc4.dto.MemoResponseDto;
import com.example.jdbc4.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoRequestDto save (@RequestBody MemoRequestDto dto) {
        return memoService.save(dto);
    }

    // Read All API
    @GetMapping("/memos")
    public List<MemoResponseDto> findAll() {
        return memoService.findAll();
    }

    // Read One API
    @GetMapping("/memos/{memoId}")
    public MemoResponseDto findByID(@PathVariable Long memoId) {
        return memoService.findById(memoId);
    }

    @PutMapping("/memos/{memoId}")
    public MemoResponseDto update(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto dto
    ) {
        return memoService.update(memoId, dto);
    }

    // Delete API
    @DeleteMapping("/memos/{memoId}")
    public void deleteById(@PathVariable Long memoId) {
        memoService.deleteById(memoId);
    }
}
