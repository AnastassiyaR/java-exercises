package university;

import check.Checker;
import exceptions.UniException;

public class UniversityBuilder {
    private String universityName;

    /**
     * University Builder with name
     * @param universityName
     * @return university's name
     */
    public UniversityBuilder withName(String universityName) throws UniException {
        new Checker<String>().nullCheck(universityName);
        this.universityName = universityName;
        return this;
    }

    /**
     * University build
     * @return build
     * @throws UniException
     */
    public University build() throws UniException {
        return new University(universityName);
    }
}
