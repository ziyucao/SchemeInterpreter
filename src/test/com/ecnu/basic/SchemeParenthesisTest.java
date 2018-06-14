package com.ecnu.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SchemeParenthesis Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <process>June 1, 2018</process>
 */
public class SchemeParenthesisTest {

    SchemeParenthesis parenthesis;
    @Before
    public void before() throws Exception {
        parenthesis = new SchemeParenthesis('(');
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        Assert.assertEquals(TokenType.Parenthesis, parenthesis.getType());
    }

    /**
     * Method: getContent()
     */
    @Test
    public void testGetContent() {
        Assert.assertEquals('(', parenthesis.getContent().charValue());
    }

} 
