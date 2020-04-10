package seedu.address.model.hotel.person;

import java.util.function.Predicate;

import seedu.address.model.ids.PersonId;


/**
 * Match person predicate, or of namePredicate and personIdPredicate
 */
public class MatchPersonPredicate implements Predicate<Person> {
    private Predicate<Name> namePredicate;
    private Predicate<PersonId> personIdPredicate;

    /**
     * Constructs a new MatchPersonPredicate
     */
    public MatchPersonPredicate() {
        this.namePredicate = unused -> false;
        this.personIdPredicate = unused -> false;
    }

    /**
     * set this.namePredicate
     */
    public void setNamePredicate(Predicate<Name> namePredicate) {
        this.namePredicate = namePredicate;
    }

    /**
     * set this.personIdPredicate
     */
    public void setPersonIdPredicate(Predicate<PersonId> personIdPredicate) {
        this.personIdPredicate = personIdPredicate;
    }

    @Override
    public boolean test(Person person) {
        return this.namePredicate.test(person.getName())
                || this.personIdPredicate.test(person.getPersonId());
    }
}
