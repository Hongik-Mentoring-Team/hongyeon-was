package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Badge;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import com.hongik.mentor.hongik_mentor.repository.BadgeRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor @Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public void setMainBadge(Long badgeId, Long memberId) {
        Badge findBadge = badgeRepository.findById(badgeId);
        Member findMember = memberRepository.findById(memberId);

        findMember.setMainBadgeUrl(findBadge.getImageUrl());
    }

    /*재학생 인증*/
    //1. 메일로 인증코드  전송
    public boolean requestUnivEmailVerification(String userEmail){
        try {
            Map<String, Object> resp = UnivCert.certify("a718a7f1-5c62-4c2e-b007-bc8411926fe3", userEmail, "홍익대학교", true);
            return (Boolean) resp.get("success");
        } catch (IOException e) {
            log.warn("인증메일 발송 실패 - userEmail:{}", userEmail, e);
            throw new RuntimeException("대학교 인증메일 발송 과정에서 오류가 발생하였음", e);
        }

    }

    //2. 인증코드 검증
    public boolean confirmUnivEmailVerification(String userEmail, int verificationCode) {
        try {
            Map<String, Object> resp = UnivCert.certifyCode("a718a7f1-5c62-4c2e-b007-bc8411926fe3", userEmail, "홍익대학교", verificationCode);
            return (Boolean) resp.get("success");
        } catch (IOException e) {
            log.warn("인증코드 검증 시도 실패", e);
            throw new RuntimeException("인증코드를 검증하는 과정에서 오류가 발생하였습니다", e);
        }
    }


    /*crud*/
    //Create
    @Transactional
    public Long save(MemberSaveDto memberSaveDto) {
        return memberRepository.save(memberSaveDto.toEntity()).getId();
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

    @Transactional
    public Long update(Long id, String name, String major, Integer graduationYear, MemberType memberType) {
        Member findMember = memberRepository.findById(id);
        return findMember.update(name, major, graduationYear, memberType);
    }

    //Delete
    @Transactional
    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public Optional<MemberResponseDto> findBySocialId(String userNameAttributeName) {
        try {
            MemberResponseDto memberResponseDto = new MemberResponseDto(memberRepository.findBySocialId(userNameAttributeName).get());
            return Optional.of(memberResponseDto);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
