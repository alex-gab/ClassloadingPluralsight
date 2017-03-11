package com.alex.client;

import com.alex.interfaces.IQuote;

import java.net.URL;
import java.net.URLClassLoader;

public final class Main {
    public static void main(String[] args) throws Exception {
        URL url = new URL(
                "http://localhost:8080/implementations.jar");
        final URLClassLoader ucl = new URLClassLoader(new URL[]{url});

        final Class<IQuote> clazz =
                (Class<IQuote>) ucl.loadClass("com.alex.implementations.Quote");
        final IQuote quote = clazz.newInstance();
        System.out.println(quote.getQuote());
    }
}
