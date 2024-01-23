package cards.step_definitions;

import cards.spec.Specifications;
import io.cucumber.java.en.Given;

import static cards.spec.Specifications.installSpecification;
import static cards.utils.ConfigurationProperties.getConfiguration;

public class CommonSteps {
    @Given("request specifications have been set")
    public void setRequestSpecifications() {
        installSpecification(Specifications.requestSpec(getConfiguration().getString("deck.of.card.uri")), Specifications.responseSpecOK200());
    }
}
