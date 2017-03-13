package com.alex.database;

import com.alex.interfaces.IQuote;

public final class Main {
    public static void main(String[] args) {
        try {
            DatabaseClassLoader cl =
                    new DatabaseClassLoader(
                            "jdbc:mysql://localhost:3306/ClassloadingPluralsight?useSSL=false&user=pluralsightUser&password=pluralsightPassword");
            Class clazz = cl.findClass("com.alex.implementations.Quote");
            IQuote quote = (IQuote) clazz.newInstance();
            System.out.println(quote.getQuote());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
