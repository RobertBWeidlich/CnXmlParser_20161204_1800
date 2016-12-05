package com.cn.xml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class FQXmlElementTest {

    FQXmlElement fqe = null;

    @Before
    public void init() {
        fqe = new FQXmlElement();
    }

    @Test
    public void test00Something() {
        assertTrue(true);
    }

    @Test
    public void test01Echo() {
        String testStr = fqe.echo("abc");
        String tStr = "abc";
        String rStr = fqe.echo("abc");
        assertEquals("Must be equal", tStr, rStr);
    }

    @Test
    public void test02PopWhenEmpty() {
        String rv = fqe.pop();
        assertEquals("must be empty string", "", rv);
    }

    @Test
    public void test03Add3Elements() {
        fqe.push("abc");
        fqe.push("def");
        fqe.push("ghi");

        String eVal = "abc.def.ghi";
        String rVal = fqe.getString();

        assertEquals("", eVal, rVal);
    }

    @Test
    public void test04CheckState() {
        // make sure we are starting with a new instance of fqe
        String rVal = fqe.getString();
        String eVal = "";
        assertEquals("", eVal, rVal);
    }

    @Test
    public void test05PopReturnsCorrectValue() {
        fqe.push("abc");
        fqe.push("def");
        fqe.push("ghi");
        String rVal = fqe.pop();
        String eVal = "ghi";
        assertEquals("Test pop returns correct value", eVal, rVal);
    }

    @Test
    public void test06PushAndPop() {
        fqe.push("ABC");
        fqe.push("DEF");
        fqe.push("XYZ");
        fqe.pop();
        String rVal = fqe.getString();
        String eVal = "ABC.DEF";
        assertEquals("Test push and pop", eVal, rVal);
    }

    @Test
    public void test07TooManyPops() {
        fqe.push("ABC");
        fqe.push("DEF");
        fqe.push("XYZ");
        fqe.pop();
        fqe.pop();
        fqe.pop();
        fqe.pop();
        fqe.pop();
        fqe.pop();
        fqe.pop();

        String rVal = fqe.getString();
        String eVal = "";
        assertEquals("Test push and pop", eVal, rVal);
    }
}
