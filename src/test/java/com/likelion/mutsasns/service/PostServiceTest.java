//package com.likelion.mutsasns.service;
//
//import com.likelion.mutsasns.domain.Post;
//import com.likelion.mutsasns.domain.User;
//import com.likelion.mutsasns.repository.PostRepository;
//import com.likelion.mutsasns.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//public class PostServiceTest {
//
//    PostService postService;
//    PostRepository postRepository = mock(PostRepository.class);
//    UserRepository userRepository = mock(UserRepository.class);
//    /*
//    Like, Comment 기능 구현 후 해당 리포지터리 추가
//     */
//
//    @BeforeEach
//    void setUp() {
//        postService = new PostService(postRepository, userRepository);
//    }
//
//    @Test
//    @DisplayName("포스트 등록 성공")
//    void post_success() {
//        TestInfoFixture.TestInfo fixture = TestInfoFixture.get();
//
//        Post mockPostEntity =mock(Post.class);
//        User mockUserEntity =mock(User.class);
//
//        when(userRepository.findByUserName(fixture.getUserName()))
//                .thenReturn(Optional.of(mockUserEntity));
//        when(postRepository.save(any()))
//                .thenReturn(mockPostEntity);
//
//        Assertions.assertDoesNotThrow(
//                () -> postService.write(fixture.getTitle(), fixture.getBody(), fixture.getUserName()));
//    }
//
//    @Test
//    @DisplayName("포스트 등록 실패")
//    void post_fail() {
//        TestInfoFixture.TestInfo fixture = TestInfoFixture.get();
//
//        Post mockPostEntity =mock(Post.class);
//        User mockUserEntity =mock(User.class);
//
//        when(userRepository.findByUserName(fixture.getUserName()))
//                .thenReturn(Optional.of(mockUserEntity));
//        when(postRepository.save(any()))
//                .thenReturn(mockPostEntity);
//
//        Assertions.assertDoesNotThrow(
//                () -> postService.write(fixture.getTitle(), fixture.getBody(), fixture.getUserName()));
//    }
//}