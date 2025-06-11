package ee.taltech.iti0202.mysticorbs.exceptions;

import ee.taltech.iti0202.mysticorbs.oven.Oven;

public class CannotFixException extends Exception {
  public enum Reason {
    IS_NOT_BROKEN, FIXED_MAXIMUM_TIMES, NOT_ENOUGH_RESOURCES
  }

  private Oven oven;
  private Reason reason;

  /**
   * Expection once the oven cant be fixed
   * @param oven
   * @param reason
   */
  public CannotFixException(Oven oven, Reason reason) {
    super("Cannot fix oven: " + oven.getName() + ", reason: " + reason);
    this.oven = oven;
    this.reason = reason;
  }

  public Oven getOven() {
    return oven;
  }
  public Reason getReason() {
    return reason;
  }

}
