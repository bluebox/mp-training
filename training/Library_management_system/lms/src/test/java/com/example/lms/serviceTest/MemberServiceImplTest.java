 package com.example.lms.serviceTest;

import com.example.lms.model.Member;
import com.example.lms.repository.MemberRepository;
import com.example.lms.service.impl.MemberServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;
    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .memberId(memberId)
                .name("John Doe")
                .email("john@example.com")
                .mobile("1234567890")
                .gender("Male")
                .address("123 Main Street")
                .build();
    }

    @Test
    void addMember_shouldSaveMember() {
        given(memberRepository.save(member)).willReturn(1);

        int result = memberService.addMember(member);

        assertThat(result).isEqualTo(1);
        verify(memberRepository).save(member);
    }

    @Test
    void updateMember_shouldUpdateMember() {
        given(memberRepository.update(member)).willReturn(1);

        int result = memberService.updateMember(member);

        assertThat(result).isEqualTo(1);
        verify(memberRepository).update(member);
    }

    @Test
    void deleteMember_shouldDeleteMemberById() {
        given(memberRepository.deleteById(memberId)).willReturn(1);

        int result = memberService.deleteMember(memberId);

        assertThat(result).isEqualTo(1);
        verify(memberRepository).deleteById(memberId);
    }

    @Test
    void getMemberById_shouldReturnMember() {
        given(memberRepository.findById(memberId)).willReturn(member);

        Member result = memberService.getMemberById(memberId);

        assertThat(result).isNotNull();
        assertThat(result.getMemberId()).isEqualTo(memberId);
        verify(memberRepository).findById(memberId);
    }

    @Test
    void getAllMembers_shouldReturnAllMembers() {
        Member member2 = Member.builder()
                .memberId(2L)
                .name("Jane Doe")
                .email("jane@example.com")
                .mobile("0987654321")
                .gender("Female")
                .address("456 Another Street")
                .build();

        given(memberRepository.findAll()).willReturn(List.of(member, member2));

        List<Member> result = memberService.getAllMembers();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(memberRepository).findAll();
    }

    private Member createSampleMember(Long id) {
        return Member.builder()
                .memberId(id)
                .name("John Doe")
                .email("john@example.com")
                .mobile("1234567890")
                .gender("Male")
                .address("123 Main Street")
                .build();
    }
}

