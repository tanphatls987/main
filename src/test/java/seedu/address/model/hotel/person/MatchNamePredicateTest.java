package seedu.address.model.hotel.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

class MatchNamePredicateTest {

    @Test
    void test() {
        Person alice = ALICE;
        List<Name> nameList = new ArrayList<>();

        nameList.add(BENSON.getName());
        MatchNamePredicate pred1 = new MatchNamePredicate(nameList);

        nameList.add(alice.getName());

        MatchNamePredicate pred2 = new MatchNamePredicate(nameList);

        assertFalse(pred1.test(alice));
        assertTrue(pred1.test(BENSON));
        assertTrue(pred2.test(alice));
    }
}
