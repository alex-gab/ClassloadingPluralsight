package com.alex.implementations;

import com.alex.interfaces.IQuote;

public final class Quote implements IQuote {
    @Override
    public String getQuote() {
        return "A rolling stone gathers no moss";
    }
}
