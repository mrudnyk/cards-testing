package cards.pojos;

public class Return {
    private Boolean success;
    private String deck_id;
    private Integer remaining;

    public Return() {
        this.success = success;
        this.deck_id = deck_id;
        this.remaining = remaining;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public Integer getRemaining() {
        return remaining;
    }
}
