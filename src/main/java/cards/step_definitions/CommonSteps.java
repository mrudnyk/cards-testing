package cards.step_definitions;

import cards.spec.Specifications;
import io.cucumber.java.en.Given;

import static cards.spec.Specifications.*;
import static cards.utils.ConfigurationProperties.getConfiguration;

public class CommonSteps {
    @Given("request specifications have been set")
    public void setRequestSpecifications() {
        installSpecification(requestSpec(getConfiguration().getString("deck.of.card.uri")), responseSpecOK200());
    }
}
