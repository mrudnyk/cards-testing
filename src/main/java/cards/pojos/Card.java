package cards.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class Card {
    private String code;
    private String image;
    private List<String> images;
    private String value;
    private String suit;

    @Override
    public String toString() {
        return "Card{" +
                "code='" + code + '\'' +
                ", image='" + image + '\'' +
                ", images=" + images +
                ", value='" + value + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }

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

    public List<String> getImages() {
        return images;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
}
