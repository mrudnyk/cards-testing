package cards.hooks;

import cards.pojos.Return;
import cards.spec.Specifications;
import io.cucumber.java.After;
import io.restassured.RestAssured;
import org.testng.Assert;
import static cards.spec.Specifications.installSpecification;
import static cards.utils.ConfigurationProperties.getConfiguration;

public class Hooks {

    @After()
    public static void after1() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Return return1 = RestAssured.given().log().all()
                .when()
                .get("/api/deck/h8tz419w96ru/return/").as(Return.class);
        Assert.assertEquals(return1.getRemaining(), 52);
    }

    @After()
    public static void after2() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
        Return return1 = RestAssured.given().log().all()
                .when()
                .get("/api/deck/adshf1gqwyc6/return/").as(Return.class);
        Assert.assertEquals(return1.getRemaining(), 10);
    }
}

