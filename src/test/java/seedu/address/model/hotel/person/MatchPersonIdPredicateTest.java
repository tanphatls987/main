package seedu.address.model.hotel.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ids.PersonId;
import seedu.address.testutil.TypicalPersons.ALICE;
import seedu.address.testutil.TypicalPersons.BENSON;

class MatchPersonIdPredicateTest {

    @Test
    void test() {
        Person alice = ALICE;
        List<PersonId> personIdList = new ArrayList<>();

        personIdList.add(BENSON.getPersonId());
        MatchPersonIdPredicate pred1 = new MatchPersonIdPredicate(personIdList);

        personIdList.add(alice.getPersonId());
        MatchPersonIdPredicate pred2 = new MatchPersonIdPredicate(personIdList);

        assertFalse(pred1.test(alice));
        assertTrue(pred2.test(alice));
    }
}
