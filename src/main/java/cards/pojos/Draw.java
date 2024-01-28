package cards.pojos;

import java.util.List;

public class Draw {
    String error;
    private Boolean success;
    private String deck_id;
    private List<Card> cards;
    private Integer remaining;

    public Draw() {
    }

    public Draw(String error, Boolean success, String deck_id, List<Card> cards, Integer remaining) {
        this.error = error;
        this.success = success;
        this.deck_id = deck_id;
        this.cards = cards;
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "Draw{" +
                "success=" + success +
                ", deck_id='" + deck_id + '\'' +
                ", cards=" + cards +
                ", remaining=" + remaining +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public String getError() {
        return error;
    }
}





