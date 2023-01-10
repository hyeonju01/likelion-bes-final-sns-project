package com.likelion.mutsasns.service;

import com.likelion.mutsasns.domain.dto.PostAddRequest;
import com.likelion.mutsasns.domain.dto.PostAddResponse;
import com.likelion.mutsasns.domain.dto.PostResponse;
import com.likelion.mutsasns.domain.entity.Post;
import com.likelion.mutsasns.domain.entity.User;
import com.likelion.mutsasns.exception.ErrorCode;
import com.likelion.mutsasns.exception.SnsException;
import com.likelion.mutsasns.repository.PostRepository;
import com.likelion.mutsasns.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PostServiceTest {

    private final PostRepository postRepository = mock(PostRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private PostService postService;

    //mockUserOne
    private final Long userOneId = 1L;
    private final String userOneName = "hyeonju";
    private final String userOnePassword = "1234";
    private final User userOne = User.builder()
            .id(userOneId)
            .userName(userOneName)
            .password(userOnePassword)
            .build();

    //mockUserTwo
    private final Long userTwoId = 2L;
    private final String userTwoName = "hyeongju";
    private final String userTwoPassword = "4321";
    private final  User userTwo = User.builder()
            .id(userTwoId)
            .userName(userTwoName)
            .password(userTwoPassword)
            .build();

    //mockPost
    private final Long mockPostId = 1L;
    private final String mockPostTitle = "title";
    private final String mockPostBody = "body";
    private final Post mockPost = Post.builder()
            .id(mockPostId)
            .title(mockPostTitle)
            .body(mockPostBody).build();

    @BeforeEach
    void setUp() {
        //PostService에 UserRepository 주입해줘야 여기서 주입됨
        postService = new PostService(postRepository, userRepository);

    }

    //given - when - then
    @Test
    @DisplayName("포스트 등록 성공")
    void post_add_success() {
        //* given
        // 사전준비
        PostAddRequest dto = new PostAddRequest(mockPost.getTitle(), mockPost.getBody());
        given(userRepository.findByUserName(userOneName)).willReturn(Optional.of(userOne));
        given(postRepository.save(any())).willReturn(mockPost);

        //* when
        // 행동
        PostAddResponse postResponse = postService.createPost(dto, userOne.getUserName());

        //* then
        // 검증
        assertDoesNotThrow( //error가 발생하지 않으면 True
                () -> postService.createPost(dto, userOne.getUserName()));
        assertEquals(postResponse.getMessage(), "포스트 등록 완료");
    }

    @Test
    @DisplayName("포스트 등록 실패 - login X")
    void post_add_fail() {
        //* given
        // 사전준비
        PostAddRequest postAddRequest = new PostAddRequest(mockPost.getTitle(), mockPost.getBody());
        given(userRepository.findByUserName(userOneName)).willReturn(Optional.empty()); // login X
        given(postRepository.save(any())).willReturn(mockPost);

        //* when
        // 행동
        PostAddResponse postAddResponse = postService.createPost(postAddRequest, userOne.getUserName());
//        when(userRepository.findByUserName(fixture.getUserName()))
//                .thenReturn(Optional.of(mockUserEntity));
//        when(postRepository.save(any()))
//                .thenReturn(mockPostEntity);

        //* then
        // 검증
        SnsException e = Assertions.assertThrows(SnsException.class, ()
                        -> postService.createPost(postAddRequest, userOne.getUserName()));
        assertEquals(ErrorCode.INVALID_PERMISSION, e.getErrorCode());
    }
}