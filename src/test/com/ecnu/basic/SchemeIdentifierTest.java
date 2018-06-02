package com.ecnu.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SchemeIdentifier Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <process>June 1, 2018</process>
 */
public class SchemeIdentifierTest {

    SchemeIdentifier id;
    @Before
    public void before() throws Exception {
        id = new SchemeIdentifier("name");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        Assert.assertEquals(TokenType.Identifier, id.getType());
    }

    /**
     * Method: getContent()
     */
    @Test
    public void testGetContent() {
        Assert.assertEquals("name", id.getContent());
    }


} 
