package com.gonglin.test.serializable;

public class PortfolioException extends Exception {
    private static final long serialVersionUID = 102L;
    public PortfolioException (String message) {
        super(message);
    }
    public PortfolioException (String message, Throwable t) {
        super (message, t);
    }
}
