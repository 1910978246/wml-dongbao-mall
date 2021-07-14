package com.wml.wmldongbaoums.mapper;

import com.wml.wmldongbaoums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/14 - 07 - 14 - 14:21
 * @Description: com.wml.wmldongbaoums.mapper
 * @version: 1.0
 */
@SpringBootTest
public class UserMemberTest {
    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Test
    void testInsert(){
        UmsMember t = new UmsMember();
        t.setUsername("cpf");
        t.setStatus(0);
        t.setPassword("1");
        t.setNote("note");
        t.setNickName("nick");
        t.setEmail("email");
        umsMemberMapper.insert(t);
    }


}
