package com.alex.client;

import com.alex.interfaces.IQuote;

import java.net.URL;
import java.net.URLClassLoader;

public final class Main {
    public static void main(String[] args) throws Exception {
        final URL url1 = new URL("file:///home/alex/IntelliJ/IntelliJ-Workspace/ClassloadingPluralsight/lib/implementations.jar");
        final URLClassLoader ucl1 = new URLClassLoader(new URL[]{url1});
        final Class<?> clazz1 = Class.forName("com.alex.implementations.Quote", true, ucl1);
        final IQuote quote1 = (IQuote) clazz1.newInstance();

        final URL url2 = new URL("file:///home/alex/IntelliJ/IntelliJ-Workspace/ClassloadingPluralsight/lib/implementations.jar");
        final URLClassLoader ucl2 = new URLClassLoader(new URL[]{url2});
        final Class<?> clazz2 = Class.forName("com.alex.implementations.Quote", true, ucl2);
        final IQuote quote2 = (IQuote) clazz2.newInstance();

        System.out.printf("clazz1 == clazz2? %b\n", clazz1 == clazz2);
        System.out.printf("quote1.class == quote2.class? %b\n", quote1.getClass() == quote2.getClass());
    }
}
