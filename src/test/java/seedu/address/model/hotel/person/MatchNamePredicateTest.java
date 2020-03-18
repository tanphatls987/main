package seedu.address.model.hotel.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPersons;

class MatchNamePredicateTest {

    @Test
    void test() {
        Person alice = TypicalPersons.ALICE;
        List<Name> nameList = new ArrayList<>();

        nameList.add(TypicalPersons.BENSON.getName());
        MatchNamePredicate pred1 = new MatchNamePredicate(nameList);

        nameList.add(alice.getName());

        MatchNamePredicate pred2 = new MatchNamePredicate(nameList);

        assertFalse(pred1.test(alice));
        assertTrue(pred1.test(TypicalPersons.BENSON));
        assertTrue(pred2.test(alice));
    }
}
