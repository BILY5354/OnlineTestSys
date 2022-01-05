/*
package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;
import com.itheima.service.store.impl.QuestionItemServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class QuestionItemServiceTest {

    private static QuestionItemService questionItemService = null;
    @BeforeClass
    public static void init(){
        questionItemService = new QuestionItemServiceImpl();
    }

    @Test
    public void testFindAll(){
        PageInfo all = questionItemService.findAll(1, 100);
        System.out.println(all);
    }

    @Test
    public void testFindAll2() {
        List<QuestionItem> all = questionItemService.findAll('1',1,1);
        for(int i = 0;i < all.size(); i++) {
            System.out.println(all.get(i));
        }
    }

    @AfterClass
    public static void destory(){
        questionItemService = null;
    }
}
*/
