package be.ordina.utdemo.factoids;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class FactoidWithCorrectFileNumberOfFactsExceedsLengthOfFileTest {

    @Test
    public void main_with_other_value_than_default_should_complete_without_errors_and_only_print_facts() {
        Factoid.main(new String [] {"150"});
    }

}
