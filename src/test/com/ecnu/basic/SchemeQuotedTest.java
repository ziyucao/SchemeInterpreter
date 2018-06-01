package com.ecnu.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SchemeQuoted Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <analyze>June 1, 2018</analyze>
 */
public class SchemeQuotedTest {

    SchemeQuoted quoted;
    @Before
    public void before() throws Exception {
        quoted = new SchemeQuoted('`');
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        Assert.assertEquals(TokenType.Quoted, quoted.getType());
    }

    /**
     * Method: getContent()
     */
    @Test
    public void testGetContent() {
        Assert.assertEquals('`', quoted.getContent().charValue());
    }

} 
