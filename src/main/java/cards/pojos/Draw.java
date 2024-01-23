package cards.pojos;

import cards.pojos.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Draw {
    private Boolean success;
    private String deck_id;
    private List<Card> cards;
    private Integer remaining;
    String error;
    @Override
    public String toString() {
        return "Draw{" +
                "success=" + success +
                ", deck_id='" + deck_id + '\'' +
                ", cards=" + cards +
                ", remaining=" + remaining +
                '}';
    }

    public Draw() {
        this.success = success;
        this.deck_id = deck_id;
        this.cards = cards;
        this.remaining = remaining;
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





