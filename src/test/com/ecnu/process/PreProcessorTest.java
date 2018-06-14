package com.ecnu.process;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static com.ecnu.process.PreProcessor.preProcess;

/** 
* PreProcessor Tester. 
* 
* @author <Authors name> 
* @since <process>May 30, 2018</process>
* @version 1.0 
*/ 
public class PreProcessorTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* ���մ���Ԥ�������Ƿ�����
* 
*/ 
@Test
public void if_the_result_of_empty_string_is_right() throws Exception {
    Assert.assertEquals(preProcess("").isEmpty(), true);
}

/**
 *
 * ��������ַ�����Ԥ�������Ƿ�����
 *
 */
@Test
public void if_the_result_of_normal_string_is_right() throws Exception {
    ArrayList result = preProcess("abcdef");
    Assert.assertEquals(result.get(0), "abcdef");
}

/**
 *
 * ���scheme�����Ԥ�������Ƿ�����
 *
 */
@Test
public void if_the_result_of_scheme_code_is_right() throws Exception {
    ArrayList result = preProcess("(+ 4 3)");
    Assert.assertEquals(result.get(0), "(");
    Assert.assertEquals(result.get(1), "+");
    Assert.assertEquals(result.get(2), "4");
    Assert.assertEquals(result.get(3), "3");
    Assert.assertEquals(result.get(4), ")");
}

/**
 *
 * ��鸴��scheme�����Ԥ�������Ƿ�����
 *
 */
@Test
public void if_the_result_of_complex_scheme_code_is_right() throws Exception {
    ArrayList result = preProcess("(define-syntax def\n" +
            "(syntax-rules ()\n" +
            "((def f (p ...) body)\n" +
            "(define (f p ...)\n" +
            "body))))");
    Assert.assertEquals(result.get(1), "define-syntax");
    Assert.assertEquals(result.get(4), "syntax-rules");
    Assert.assertEquals(result.get(10), "f");
    Assert.assertEquals(result.get(18), "define");
    Assert.assertEquals(result.get(28), ")");
}

/**
 *
 * ������ע�͵�scheme�����Ԥ�������Ƿ�����
 *
 */
@Test
public void if_the_result_of_scheme_code_with_comment_is_right() throws Exception {
    ArrayList result1 = preProcess(";aaa");
    ArrayList result2 = preProcess("(+ 4 3);aaa");
    Assert.assertEquals(result1.isEmpty(), true);
    Assert.assertEquals(result2.get(2), "4");
}

} 
