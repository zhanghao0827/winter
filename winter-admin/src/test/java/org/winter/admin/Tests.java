package org.winter.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.winter.system.repository.MenuRepository;

@SpringBootTest
public class Tests {

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void test1() {
        Long b = menuRepository.countChildren(99L);
        System.out.println("b = " + b);
    }
}
