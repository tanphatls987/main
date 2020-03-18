package seedu.address.model.hotel.person;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * Check if the person name is in a predetermine list.
 */
public class MatchNamePredicate implements Predicate<Person> {
    private final HashSet<Name> nameList;

    /**
     * Create predicate with nameList
     * @param nameList
     */
    public MatchNamePredicate(List<Name> nameList) {
        ///nameList may change later on
        this.nameList = new HashSet<>(nameList);
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param person the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Person person) {
        return nameList.contains(person.getName());
    }
    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }
        if (!(oth instanceof MatchNamePredicate)) {
            return false;
        }
        MatchNamePredicate othPred = (MatchNamePredicate) oth;
        return othPred.nameList.equals(nameList);
    }

}
