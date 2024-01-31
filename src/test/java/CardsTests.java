import cards.pojos.Card;
import cards.pojos.Deck;
import cards.pojos.Draw;
import cards.pojos.Return;
import cards.spec.Specifications;
import cards.utils.ConfigurationProperties;
import cards.utils.EndPoints;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.util.List;

import static cards.pojos.Deck.getDeck_id;
import static cards.spec.Specifications.*;
import static cards.utils.ConfigurationProperties.getConfiguration;
import static cards.utils.EndPoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CardsTests {
    @BeforeMethod ()
    public static String shuffleDeck() {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
        Deck deck = given()
                .when().get(NEW_DECK.getEndPoint() + SHUFFLE.getEndPoint())
                .then().log().all()
                .extract().body().jsonPath().getObject("", Deck.class);
        assertTrue(deck.getSuccess());
        return getDeck_id();
    }

    @BeforeMethod()
    public static String shuffleDeckWithAces () {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
        Deck deck = given()
                .when().get(NEW_DECK.getEndPoint() + SHUFFLE.getEndPoint() + "?cards=AS,AS,AD,AD,AC,AC,AH,AH")
                .then().log().all()
                .extract().body().jsonPath().getObject("", Deck.class);
        assertTrue(deck.getSuccess());
        return getDeck_id();
    }


    @Test(description = "Drawing 5 cards from the deck")
    public void checkRemainingCardsCount1() {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
        String deckId1 = shuffleDeck();
        int count = 10;
        Draw draw = given()
                .when()
                .get(deckId1 + DRAW_CARD.getEndPoint() + count)
                .then().log().all()
                .extract()
                .response().as(new TypeRef<>() {
                });
        int remaining = 52 - count;
        assertEquals(draw.getRemaining(), remaining);
    }

    @Test(description = "Draw cards from deck with aces using stream")
    public void deckWithAces2() {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
        String deckId2 = shuffleDeckWithAces();
        List<Card> cards = given()
                .when()
                .get(deckId2 + DRAW_CARD.getEndPoint() + 8)
                .then().log().all()
                .extract().body().jsonPath().getList("cards", Card.class);
        cards.stream().forEach(x -> assertEquals(x.getValue(), "ACE"));
    }

    @Test(description = "Draw cards from deck with aces using for loop")
    public void deckWithAces1() {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
        String deckId2 = shuffleDeckWithAces();
        Draw draw = given()
                .when()
                .get(deckId2 + DRAW_CARD.getEndPoint() + 8)
                .then().log().all()
                .extract().as(Draw.class);
        for (Card card : draw.getCards()) {
            assertEquals(card.getValue(), "ACE");
        }
    }

    @Test(description = "Draw 5 specific cards")
    public void drawing5SpecificCards() {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
        String deckId1 = shuffleDeck();
        given()
                .when()
                .get(deckId1 + DRAW_CARD.getEndPoint() +"5&cards=AD,6D,KD,AC,6C")
                .then().log().all()
                .body("remaining", equalTo(47))
                .body("cards.code", not(hasItems("AD", "6D", "KD", "AC", "6C")));
    }
}




