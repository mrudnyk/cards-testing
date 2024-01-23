package cards.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.Map;

public class Card {
    private String code;
    private String image;
    private Map<String, String> images;
    private String value;
    private String suit;

    public Card() {
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
