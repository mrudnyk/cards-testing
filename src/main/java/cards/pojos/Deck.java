package cards.pojos;

public class Deck {
    private Boolean success;
    private String deck_id;
    private Boolean shuffled;
    private Integer remaining;

    public Deck() {
    }

    public Deck(Boolean success, String deck_id, Boolean shuffled, Integer remaining) {
        this.success = success;
        this.deck_id = deck_id;
        this.shuffled = shuffled;
        this.remaining = remaining;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public Boolean getShuffled() {
        return shuffled;
    }

    public Integer getRemaining() {
        return remaining;
    }
}