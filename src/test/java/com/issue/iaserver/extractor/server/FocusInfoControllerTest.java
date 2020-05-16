package com.issue.iaserver.extractor.server;

import com.issue.iaserver.Main;
import com.issue.iaserver.extractor.focus.Focus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/16 4:01 下午
 */
@SpringBootTest(classes = {Main.class})
@RunWith(SpringJUnit4ClassRunner.class)
class FocusInfoControllerTest {

    @Resource(name = "focusInfoService")
    private FocusInfoService focusInfoService;

    @Test
    void getAllFocus() {
        List<Focus> focusList = focusInfoService.getAllFocus();

    }

    @Test
    void addFocus() {
    }

    @Test
    void updateFocus() {
    }
}