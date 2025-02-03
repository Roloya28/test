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
    public MemoResponseDto save(MemoRequestDto dto) {

        Memo memo = new Memo(dto.getContent()); // 저장되기 전의 Memo
        Memo saveMemo = memoRepository.save(memo); // 저장된 Memo
        return new MemoResponseDto(saveMemo.getId()), savedMemo.getContent());
    }

    public List<MemoResponseDto> findAll() {

        List<Memo> memos = memoRepository/findAll();

        // 리턴 타입을 맞추기 위한 dto 리스트 그릇
        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(), memo.getContent())
        }
    }

    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    public MemoResponseDto update(Long memoId, MemoRequestDto dto) {
        Memo memo = memoRepository.updateContent(id, dto.getContent());
        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    public void deleteById(Long id) {
        // 삭제하기 전에 있나 없나 한 번 확인하고 싶어
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        memoRepository.deleteById(id);
    }
}
