package seedu.address.model.hotel.person;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.ids.PersonId;

/**
 * Check if person have personId in a predetermine list.
 */
public class MatchPersonIdPredicate implements Predicate<PersonId> {
    private final HashSet<PersonId> personIdList;

    /**
     * Create predicate.
     * @param personIdList
     */
    public MatchPersonIdPredicate(HashSet<PersonId> personIdList) {
        ///personIdList may change later
        this.personIdList = personIdList;
    }

    /**
     * Altenative constructor.
     * @param personIdList
     */
    public MatchPersonIdPredicate(List<PersonId> personIdList) {
        this.personIdList = new HashSet<>(personIdList);
    }

    @Override
    public boolean test(PersonId personId) {
        return personIdList.contains(personId);
    }

    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }
        if (!(oth instanceof MatchPersonIdPredicate)) {
            return false;
        }
        MatchPersonIdPredicate othPred = (MatchPersonIdPredicate) oth;
        return othPred.personIdList.equals(personIdList);
    }
}
