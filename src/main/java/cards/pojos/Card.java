package cards.pojos;

import java.util.Map;

public class Card {
    private String code;
    private String image;
    private Map<String, String> images;
    private String value;
    private String suit;

    public Card() {
    }

    public Card(String code, String image, Map<String, String> images, String value, String suit) {
        this.code = code;
        this.image = image;
        this.images = images;
        this.value = value;
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
}
