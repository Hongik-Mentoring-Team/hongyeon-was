package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor @Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;


    //Create
    @Transactional
    public void save(MemberSaveDto memberSaveDto) {
        memberRepository.save(memberSaveDto.toEntity());
    }

    //Read
    public MemberResponseDto findById(Long id) {
        Member findMember = memberRepository.findById(id);

        return new MemberResponseDto(findMember);
    }

    public List<MemberResponseDto> findAll() {

        List<MemberResponseDto> collect = memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());

        return collect;
    }
    //Update

    //Delete
    @Transactional
    public void delete(Long id) {
        memberRepository.delete(id);
    }

}
