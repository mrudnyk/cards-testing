import cards.pojos.Card;
import cards.pojos.Deck;
import cards.pojos.Draw;
import cards.pojos.Return;
import cards.spec.Specifications;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.util.List;
import static cards.spec.Specifications.installSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CardsTests {
    private final static String URL = "https://deckofcardsapi.com";

    Card card = new Card();
    Deck deck = new Deck();
    Return return1 = new Return();
    Draw draw = new Draw();

    @Test
    public void shuffleDeck () {
    installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Deck deck = given()
                .when().get("/api/deck/new/shuffle/?deck_count=1")
                        .then().log().all()
                        .extract().body().jsonPath().getObject("", Deck.class);
        Assert.assertEquals(deck.getSuccess(), true);
        System.out.println("Deck id now is: " + deck.getDeck_id());
    }
    @Test (description = "Drawing 2 cards from the deck")
    public void checkRemainingCardsCount () {
        Draw draw = given()
                .accept(ContentType.JSON)
                .when()
                .get(URL + "/api/deck/h8tz419w96ru/draw/?count=2")
                .then().log().all()
                .extract().body().jsonPath().getObject("", Draw.class);
        Assert.assertEquals(draw.getRemaining().toString(), 50);
        System.out.println("Remaining now is: " + draw.getRemaining());
    }

    @Test
    public void checkRemainingCardCount2 () {
        installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        given()
                .get("/api/deck/h8tz419w96ru/draw/?count=2")
                .then().log().all()
                .body("remaining", equalTo(50));
        System.out.println();

    }
    @AfterTest
    public void returnCards () {
        installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Return return1 = RestAssured.given().log().all()
                .when()
                .get(URL + "/api/deck/h8tz419w96ru/return/").as(Return.class);
        Assert.assertEquals(return1.getRemaining(), 52);
    }

    @Test (description = "Draw cards from deck with aces")
    public void deckWithAces () {
        installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<Card> cards = given()
                .when()
                .get("/api/deck/adshf1gqwyc6/draw/?count=10&cards=AS,AD,AC,AH")
                .then().log().all()
                .extract().body().jsonPath().getList("cards", Card.class);
        cards.stream().forEach(x->Assert.assertEquals(x.getValue(), "ACE"));
    }

    @Test (description = "Draw cards from deck with aces")
    public void deckWithAces1 () {
        installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Draw response = given()
                .when()
                .get("/api/deck/adshf1gqwyc6/draw/?count=10&cards=AS,AD,AC,AH")
                .then().log().all()
                .extract().as(Draw.class);
        for (Card card: response.getCards()) {
            Assert.assertEquals(card.getValue(), "ACE");
        }
    }

    @Test
    public void drawing5SpecificCards () {
        installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        //List <Card> cards =
                given()
                .when().get("/api/deck/h8tz419w96ru/draw/bottom/?count=5&cards=AD,6D,KD,AC,6C")
                .then().log().all()
                        .body("remaining", equalTo(47))
                                .body("cards.code", not(hasItems("AD","6D","KD","AC","6C")));
               // .extract().body().jsonPath().getList("cards", Card.class);
        //Assert.assertTrue(cards.stream().anyMatch(x->x.getCode().equals("AD,6D,KD,6C,2C")));

    }

}



