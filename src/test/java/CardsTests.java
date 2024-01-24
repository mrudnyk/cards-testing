import cards.pojos.Card;
import cards.pojos.Deck;
import cards.pojos.Draw;
import cards.pojos.Return;
import cards.spec.Specifications;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static cards.spec.Specifications.installSpecification;
import static cards.utils.ConfigurationProperties.getConfiguration;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CardsTests {

    @Test
    public void shuffleDeck() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Deck deck = given()
                .when().get("/api/deck/new/shuffle/?deck_count=1")
                .then().log().all()
                .extract().body().jsonPath().getObject("", Deck.class);
        Assert.assertEquals(deck.getSuccess(), true);
        System.out.println("Deck id now is: " + deck.getDeck_id());
    }

    @Test(description = "Drawing 5 cards from the deck")
    public void checkRemainingCardsCount1() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Draw draw = given()
                .when()
                .get("/api/deck/h8tz419w96ru/draw/?count=5")
                .then().log().all()
                .extract()
                .response().as(new TypeRef<>() {
                });
        Assert.assertEquals(draw.getRemaining(), 47);
        System.out.println("Remaining now is: " + draw.getRemaining());
    }

    @Test(description = "Drawing 2 cards from the deck")
    public void checkRemainingCardCount2() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        given()
                .get("/api/deck/h8tz419w96ru/draw/?count=2")
                .then().log().all()
                .body("remaining", equalTo(50));
    }

    @Test(description = "Draw cards from deck with aces", groups = {"Deck with aces only"})
    public void deckWithAces1() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Draw draw = given()
                .when()
                .get("/api/deck/adshf1gqwyc6/draw/?count=10")
                .then().log().all()
                .extract().as(Draw.class);
        for (Card card : draw.getCards()) {
            Assert.assertEquals(card.getValue(), "ACE");
        }
    }

    @Test(description = "Draw 5 specific cards")
    public void drawing5SpecificCards() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        given()
                .when().get("/api/deck/h8tz419w96ru/draw/bottom/?count=5&cards=AD,6D,KD,AC,6C")
                .then().log().all()
                .body("remaining", equalTo(47))
                .body("cards.code", not(hasItems("AD", "6D", "KD", "AC", "6C")));
    }

    @AfterMethod
    public void returnCards() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Return return1 = RestAssured.given().log().all()
                .when()
                .get("/api/deck/h8tz419w96ru/return/").as(Return.class);
        Assert.assertEquals(return1.getRemaining(), 52);
    }

    @AfterGroups("Deck with aces only")
    public void returnCards1() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Return return1 = RestAssured.given().log().all()
                .when()
                .get("/api/deck/adshf1gqwyc6/return/").as(Return.class);
        Assert.assertEquals(return1.getRemaining(), 10);
    }
}



