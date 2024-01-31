package cards.hooks;

import cards.pojos.Deck;
import cards.pojos.Return;
import cards.spec.Specifications;
import cards.utils.EndPoints;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static cards.spec.Specifications.installSpecification;
import static cards.utils.ConfigurationProperties.getConfiguration;
import static io.restassured.RestAssured.given;

public class Hooks {

    @Before ()
    public static String shuffleDeck() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Deck deck = given()
                .when().get(EndPoints.NEW_DECK.getEndPoint() + EndPoints.SHUFFLE.getEndPoint())
                .then().log().all()
                .extract().body().jsonPath().getObject("", Deck.class);
        Assert.assertTrue(deck.getSuccess());
        return Deck.getDeck_id();
    }

    @Before()
    public static String shuffleDeckWithAces () {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Deck deck = given()
                .when().get(EndPoints.NEW_DECK.getEndPoint() + EndPoints.SHUFFLE.getEndPoint() + "?cards=AS,AS,AD,AD,AC,AC,AH,AH")
                .then().log().all()
                .extract().body().jsonPath().getObject("", Deck.class);
        Assert.assertTrue(deck.getSuccess());
        return Deck.getDeck_id();
    }
}

