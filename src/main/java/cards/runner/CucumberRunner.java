package cards.runner;

import cards.pojos.Return;
import cards.spec.Specifications;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import static cards.spec.Specifications.installSpecification;
import static cards.utils.ConfigurationProperties.getConfiguration;

@CucumberOptions(tags = "",
        features = {"src/test/resources/features"},
        glue = {"cards.step_definitions"},
        plugin = {"pretty", "html:main/cucumber-reports.html"}
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
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
