package com.likelion.mutsasns.fixture;

import lombok.Getter;
import lombok.Setter;

public class TestUserFixture {

    public static TestUserInfo get() {
        TestUserInfo testUserInfo = new TestUserInfo();

        testUserInfo.setPostId(1L);
        testUserInfo.setUserId(1L);
        testUserInfo.setUserName("hyeonju");
        testUserInfo.setPassword("1q2w3e4r");
        testUserInfo.setTitle("postTitle");
        testUserInfo.setBody("postBody");

        return testUserInfo;
    }

    @Getter
    @Setter
    public static class TestUserInfo {
        private Long postId;
        private Long userId;
        private String userName;
        private String password;
        private String title;
        private String body;
    }

}
