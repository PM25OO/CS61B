package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordnetGraph wordnetGraph;

    public HyponymsHandler(WordnetGraph wordnetGraph) {
        this.wordnetGraph = wordnetGraph;
    }

    @Override
    public String handle(NgordnetQuery q) {
        return "";
    }
}
