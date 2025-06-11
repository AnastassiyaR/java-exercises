package maintainer;

import check.Checker;
import exceptions.UniException;
import java.util.Set;

public class MaintainerBuilder {

    private Set<String> items;
    private int desiredVacationDays;
    private String university;

    /**
     * Maintainer Builder with items he can repair
     * @param items
     * @return this items
     */
    public MaintainerBuilder withItems(Set<String> items) throws UniException {
        new Checker<Set<String>>().nullCheck(items);
        this.items = items;
        return this;
    }

    /**
     * Maintainer Builder with his desired vacation days
     * @param desiredVacationDays
     * @return this desired vacation days
     */
    public MaintainerBuilder withDesiredVacationDays(int desiredVacationDays) throws UniException {
        new Checker<Integer>().nullCheck(desiredVacationDays);
        this.desiredVacationDays = desiredVacationDays;
        return this;
    }

    /**
     * Maintainer Builder with university he works
     * @param university
     * @return this university
     */
    public MaintainerBuilder withUniversity(String university) throws UniException {
        new Checker<String>().nullCheck(university);
        this.university = university;
        return this;
    }

    /**
     * Build Maintainer
     * @return maintainer
     * @throws UniException
     */
    public Maintainer build() throws UniException {
        return new Maintainer(items, desiredVacationDays, university);
    }
}
