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
 * @since <pre>June 1, 2018</pre>
 */
public class SchemeNumberTest {
    SchemeNumber number;

    @Before
    public void before() throws Exception {
        number = new SchemeNumber(-1);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        Assert.assertEquals(Type.Number, number.getType());
    }

    /**
     * Method: getContent()
     */
    @Test
    public void testGetContent() {
        Assert.assertEquals(-1, number.getContent().intValue());
    }

} 
