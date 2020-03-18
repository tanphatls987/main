package seedu.address.model.hotel.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ids.PersonId;
import seedu.address.testutil.TypicalPersons;

class MatchPersonIdPredicateTest {

    @Test
    void test() {
        Person alice = TypicalPersons.ALICE;
        List<PersonId> personIdList = new ArrayList<>();

        personIdList.add(TypicalPersons.BENSON.getPersonId());
        MatchPersonIdPredicate pred1 = new MatchPersonIdPredicate(personIdList);

        personIdList.add(alice.getPersonId());
        MatchPersonIdPredicate pred2 = new MatchPersonIdPredicate(personIdList);

        assertFalse(pred1.test(alice));
        assertTrue(pred2.test(alice));
    }
}
