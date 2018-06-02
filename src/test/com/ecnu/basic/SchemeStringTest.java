package com.ecnu.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SchemeString Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <process>June 1, 2018</process>
 */
public class SchemeStringTest {

    SchemeString string;
    @Before
    public void before() throws Exception {
        string = new SchemeString("abc\n");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        Assert.assertEquals(TokenType.String, string.getType());
    }

    /**
     * Method: getContent()
     */
    @Test
    public void testGetContent() {
        Assert.assertEquals("abc\n", string.getContent());
    }
} 
