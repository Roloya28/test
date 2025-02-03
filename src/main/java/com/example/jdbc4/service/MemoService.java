package com.example.jdbc4.service;

import com.example.jdbc4.dto.MemoRequestDto;
import com.example.jdbc4.dto.MemoResponseDto;
import com.example.jdbc4.entity.Memo;
import com.example.jdbc4.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> dtoList = new ArrayList<>();
        for (Memo memo : memos) {
            dtoList.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }

        return dtoList;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto updateContent(Long id, MemoRequestDto dto) {

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        Memo updatedMemo = memoRepository.updateContent(memo.getId(), dto.getContent());

        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        memoRepository.deleteById(memo.getId());
    }
}