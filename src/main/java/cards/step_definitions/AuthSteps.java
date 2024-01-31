package cards.step_definitions;

import cards.hooks.Hooks;
import cards.pojos.Card;
import cards.pojos.Deck;
import cards.pojos.Draw;
import cards.spec.Specifications;
import cards.utils.EndPoints;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;

import static cards.hooks.Hooks.shuffleDeckWithAces;
import static cards.runner.CucumberRunner.shuffleDeck;
import static cards.spec.Specifications.*;
import static cards.utils.ConfigurationProperties.getConfiguration;
import static cards.utils.EndPoints.DRAW_CARD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class AuthSteps {

    @Then("get request to draw {int} cards from the deck has been sent and the remaining count now is: {int}")
    public void requestToDraw5Cards(int count, int remaining) {
        String deckId1 = shuffleDeck();
        Draw draw = given()
                .when()
                .get("/" + deckId1 + DRAW_CARD.getEndPoint() + count)
                .then().log().all()
                .extract()
                .response().as(new TypeRef<>() {
                });
        remaining = 52 - count;
        assertEquals(draw.getRemaining(), remaining);
    }

    @Then("get request to draw all cards from the deck has been sent and each card has value: ACE")
    public void requestToDrawAll() {
        String deckId2 = shuffleDeckWithAces();
        Draw draw = given()
                .when()
                .get("/" + deckId2 + DRAW_CARD.getEndPoint() + 8)
                .then().log().all()
                .extract().as(Draw.class);
        for (Card card : draw.getCards()) {
            assertEquals(card.getValue(), "ACE");
        }
    }


    @Then("get request to draw 5 specific cards has been sent, these cards are not present in the deck anymore and the cards count now is 47")
    public void requestToDraw5SpecificCards() {
        String deckId1 = shuffleDeck();
        given()
                .when()
                .get("/" + deckId1 + DRAW_CARD.getEndPoint() +"5&cards=AD,6D,KD,AC,6C")
                .then().log().all()
                .body("remaining", equalTo(47))
                .body("cards.code", not(hasItems("AD", "6D", "KD", "AC", "6C")));
    }
}





