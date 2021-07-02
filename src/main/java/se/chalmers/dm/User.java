package se.chalmers.dm;

/**
 * Osman Osman
 * Jamal Mohammed
 */
public class User {
  // private instance variabels
  private int ID;
  private String Ssn;
  private String FName;
  private String Lname;
  private String email;
  private boolean isActive;

  public User(int id, String Ssn, String FName, String Lname, String email, boolean isActive) {
    this.ID = id;
    this.Ssn = Ssn;
    this.FName = FName;
    this.Lname = Lname;
    this.email = email;
    this.isActive = isActive;
  }

  /**
   * String representation of the user
   *
   * @return String
   */
  public String toString() {
    return (ID + ";" + Ssn + ";" + FName + ";" + Lname + ";" + email + ";" + isActive);
  }
}
