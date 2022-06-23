package com.newspublisher.springboot;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contexLoads(){
        List<user> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
