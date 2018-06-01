package com.ecnu.analyze;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static com.ecnu.analyze.PreProcessor.preProcess;

/** 
* PreProcessor Tester. 
* 
* @author <Authors name> 
* @since <analyze>May 30, 2018</analyze>
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
* 检查空串的预处理结果是否正常
* 
*/ 
@Test
public void if_the_result_of_empty_string_is_right() throws Exception {
    Assert.assertEquals(preProcess("").isEmpty(), true);
}

/**
 *
 * 检查正常字符串的预处理结果是否正常
 *
 */
@Test
public void if_the_result_of_normal_string_is_right() throws Exception {
    ArrayList result = preProcess("abcdef");
    Assert.assertEquals(result.get(0), "abcdef");
}

/**
 *
 * 检查scheme代码的预处理结果是否正常
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
 * 检查复杂scheme代码的预处理结果是否正常
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
 * 检查包含注释的scheme代码的预处理结果是否正常
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
