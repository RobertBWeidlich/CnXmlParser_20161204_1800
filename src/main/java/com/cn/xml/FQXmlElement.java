package com.cn.xml;

import java.util.Stack;
//import java.util.Arrays;

/**
 * Maintain a list of XML Elements Names, for example "abc.def.ghi"
 */
public class FQXmlElement {
    private Stack stack;

    /**
     * Constructor
     */
    public FQXmlElement() {
        this.stack = new Stack();
        System.out.println("ctor()");
    }

    /**
     * setup JUnit tests
     * @param s
     * @return
     */
    public String echo(String s) {
        return s;
    }

    /**
     * @param elStr element to be appended to the list of Strings
     */
    public void push(String elStr) {
        this.stack.push(elStr);
    }

    /**
     * @return array of String, each corresponding to an element of the fully-qualified list of elements
     */
    public String pop() {
        if (this.stack.size() > 0)
            return this.stack.pop().toString();
        else
            return "";
    }

    /**
     * @return array of Strings, each corresponding to element name
     */
    String[] get() {
        int stackLen = stack.size();
        if (stackLen < 1)
            return null;
        String[] ss = new String[stackLen];
        for (int i = 0; i < stackLen; i++) {
            ss[i] = this.stack.get(i).toString();
        }

        return ss;
    }

    /**
     *
     * @return list of XML elements names, for example "abc.def.ghi"
     */
    String getString() {
        String[] ss = this.get();
        if (ss == null)
            return "";

        int ssLen = ss.length;
        if (ssLen < 1)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ssLen; i++) {
            sb.append(ss[i]);
            if ((i+1) < ssLen)
                sb.append(".");
        }

        return sb.toString();
    }

    /**
     *
     * @return number of elements in name
     */
    int size() {
        return this.stack.size();
    }
}
