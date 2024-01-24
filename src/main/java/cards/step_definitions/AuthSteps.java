package cards.step_definitions;

import cards.pojos.Card;
import cards.pojos.Deck;
import cards.pojos.Draw;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthSteps {

    @Then("get request to shuffle the deck has been sent and the deck has been shuffled successfully")
    public void requestToShuffle() {
        Deck deck = given()
                .when().get("/api/deck/new/shuffle/?deck_count=1")
                .then().log().all()
                .extract().body().jsonPath().getObject("", Deck.class);
        Assert.assertEquals(deck.getSuccess(), true);
        System.out.println("Deck id now is: " + deck.getDeck_id());
    }

    @Then("get request to draw 5 cards from the deck has been sent and the remaining cards count now is 47")
    public void requestToDraw5Cards() {
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

    @Then("get request to draw 2 cards from the deck has been sent and the remaining cards count now is 50")
    public void requestToDraw2Cards() {
        given()
                .get("/api/deck/h8tz419w96ru/draw/?count=2")
                .then().log().all()
                .body("remaining", equalTo(50));
    }

    @Then("get request to draw all cards from the deck has been sent and each card has value: ACE")
    public void requestToDrawAll() {
        Draw draw = given()
                .when()
                .get("/api/deck/adshf1gqwyc6/draw/?count=10")
                .then().log().all()
                .extract().as(Draw.class);
        for (Card card : draw.getCards()) {
            Assert.assertEquals(card.getValue(), "ACE");
        }
    }


    @Then("get request to draw 5 specific cards has been sent, these cards are not present in the deck anymore and the cards count now is 47")
    public void requestToDraw5SpecificCards() {
        given()
                .when().get("/api/deck/h8tz419w96ru/draw/bottom/?count=5&cards=AD,6D,KD,AC,6C")
                .then().log().all()
                .body("remaining", equalTo(47))
                .body("cards.code", not(hasItems("AD", "6D", "KD", "AC", "6C")));
    }
}





