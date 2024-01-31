package cards.utils;

public enum EndPoints {
        NEW_DECK("new"),
        SHUFFLE("shuffle"),
        DRAW_CARD("draw/?count=");

    private final String URL;
    EndPoints(String url) {
        this.URL = url;
    }
    public String getEndPoint() {
        return this.URL;
    }
}
