package user;

import exceptions.UniException;

public class UserBuilder {
    private String username;
    private String password;
    private final int number = 6;
    private int dailyLimit = number;

    /**
     * User Builder with username
     * @param username
     * @return
     */
    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * User Builder with password
     * @param password
     * @return
     */
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * User build
     * @return build
     * @throws UniException
     */
    public User build() throws UniException {
        if (username == null || password == null) {
            throw new UniException(UniException.Reason.ALL_PARAMETERS_MUST_BE_SET);
        }

        User user = new User(username, password);
        user.setDailyLimit(this.dailyLimit);
        return user;
    }
}
