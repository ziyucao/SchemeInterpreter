package com.ecnu.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SchemeNumber Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <process>June 1, 2018</process>
 */
public class SchemeNumberTest {
    SchemeNumber number;

    @Before
    public void before() throws Exception {
        number = new SchemeNumber(-1d);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        Assert.assertEquals(TokenType.Number, number.getType());
    }

    /**
     * Method: getContent()
     */
    @Test
    public void testGetContent() {
        Assert.assertEquals(-1d, number.getContent().doubleValue(), 1.0E-4);
    }

} 
