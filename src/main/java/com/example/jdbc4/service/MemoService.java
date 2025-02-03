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

    // save 메서드
    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent()); // 저장되기 전의 Memo
        Memo savedMemo = memoRepository.save(memo); // 저장된 Memo
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    // findAll 메서드
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        // 리턴 타입을 맞추기 위한 dto 리스트 그릇
        List<MemoResponseDto> dtoList = new ArrayList<>();
        for (Memo memo : memos) {
            dtoList.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }

        return dtoList;
    }

    // findById 메서드
    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto dto) {

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        Memo updatedMemo = memoRepository.updateContent(memo.getId(), dto.getContent());

        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    // delete 메서드
    @Transactional
    public void delete(Long id) {
        // 삭제하기 전에 있나 없나 한 번 찾아보고 싶을때
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        memoRepository.deleteById(id);
    }
}