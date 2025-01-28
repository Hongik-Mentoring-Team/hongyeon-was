package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.FollowRequestDTO;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.domain.Follow;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import com.hongik.mentor.hongik_mentor.exception.CustomMentorException;
import com.hongik.mentor.hongik_mentor.exception.ErrorCode;
import com.hongik.mentor.hongik_mentor.repository.FollowRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor @Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;


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

    @Transactional
    public Long followMember(FollowRequestDTO followRequestDTO){ // followerId : 팔로우를 하려는 회원, followingId : 팔로우를 당하는 회원
        Member follower = memberRepository.findById(followRequestDTO.getFollowerId());

        Member following = memberRepository.findById(followRequestDTO.getFolloweeId());

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);

        return follow.getId();
    }

    @Transactional
    public void unfollowMember(FollowRequestDTO followRequestDTO){
        Follow follow = followRepository.findByFollowerIdWithFollowingId(followRequestDTO.getFollowerId(),
                        followRequestDTO.getFolloweeId())
                .orElseThrow(() -> new CustomMentorException(ErrorCode.FOLLOW_RELATIONSHIP_DOES_NOT_EXIST));

        followRepository.delete(follow);
    }
}
