/* Alper Eken
   aq0647
   Systemutvecklare
 */

package partyModel;

import javax.swing.*;

public class GuestManager {
  /* Keep ONLY the following instance variables for the class:
     - number of guests currently stored in the list/array
       (not the total possible number of guests (use .length for this))
     - one array for the list with guests where
       the guests are objects of class Guest
  */
  private Guest[] guestList;

  /* Create a constructor with one parameter which is
     the max number of elements (guests) in the list/array.
     Create the array with the max number of elements.
     Check that the max number of Guests is larger than 0.
     If max number of guests is not larger than 0 ask the user to give a new value
     until you get a value that is larger than 0.
   */
  public GuestManager(int maxNbrOfGuests) {
    while (maxNbrOfGuests <= 0) {
      String input = JOptionPane.showInputDialog("Max number of guests need to be higher than 0, please try again.");

      if (input == null) {
        JOptionPane.showMessageDialog(null, "Input canceled, exiting the program.");
        System.exit(0);
      }

      input = input.trim();

      if (input.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Input cannot be empty, please try again.");
        continue;
      }

      if (input.matches("\\d")) {
        maxNbrOfGuests = Integer.parseInt(input);
        if (maxNbrOfGuests <= 0) {
          JOptionPane.showMessageDialog(null, "Number of guests must be higher than 0, please try again.");
        }
      } else {
        JOptionPane.showMessageDialog(null, "The input is invalid, you can only enter a number, please try again.");
      }
    }
    guestList = new Guest[maxNbrOfGuests];
  }


  /* A method that returns the number of guests stored in
     the guest list.
   */
  public int getNbrOfGuests(){
    int count = 0;
    for (int i = 0; i < guestList.length; i++) {
      if (guestList[i] != null){
        count ++;
      }
    }
    return count;
  }

  /* Create a method to add a guest to the list with all the parameters
     matching instance variables of Guest and Address.
     In the method create a new Guest-object and let classes Guest and Address
     take care of issues with values of the parameters.
     Add the new Guest-object to the array at the first empty element (here is where you
     use the instance variable for number of guests stored in the list). If there are no
     more empty elements call a private method in GuestManager to increase the size
     of the array and then add the new Guest-object. Do not forget to update the value
     of the instance variable for number of guests in the list.
   */
    public void addGuest(String firstName, String lastName, int age, String street, String city, String zipCode, Countries country) {
      for (int i = 0; i < guestList.length; i++) {
        if (guestList[i] == null) {
          guestList[i] = new Guest(firstName, lastName, age, street, city, zipCode, country);
          return;
        }
      }
      increaseGuestList();
      guestList[guestList.length - 10] = new Guest (firstName, lastName, age, street, city, zipCode, country);
    }

  /* Create method to delete a guest by giving the index in the array
     for the object (guest) to delete as a parameter to the method.
     This method should call the private method moveElementsToLeft to remove
     the empty element in between other objects (may not be necessary
     if the guest removed was the last guest in the list).
     Remember to update the value of the instance variable for
     number of guests in the list.
   */

  public void deleteGuest(int index){
    if (index >= 0 && index < guestList.length && guestList[index] != null){
      moveElementsToLeft(index);
    }
  }

  private void moveElementsToLeft(int index){
        /* Add code to remove empty element places that
           is not att the end of the array. Start at
           the parameter index that is the empty element
           whose gap we want to fill.

           You are not allowed to take a shortcut by using class Array or similar from a Java-library.
         */
    for (int i = index; i < guestList.length - 1; i++){
      guestList[i] = guestList [i+1];
    }
    guestList[guestList.length - 1] = null;
  }

  private void increaseGuestList(){
    /* Write code that creates a new array of Guest-objects
       that is 10 elements larger that the current array instance variable .

       Copy the current array to the larger array and after that is done
       point the instance variable to the new array.

       You are not allowed to take a shortcut by using class Array or similar from a Java-library.
     */
    Guest[] newGuestList = new Guest[guestList.length + 10];
    for (int i = 0; i < guestList.length; i++){
      newGuestList[i] = guestList[i];
    }
    guestList = newGuestList;
  }

  /* A method that returns the Guest-object at the given
     index n the parameter.
   */
  public Guest getGuestAt(int index){
    /* Add code to check that the parameter index
       has a value within the range of the array.
       If index is not in valid range return null.
       Yes, some checks are done in the Controller-object
       in this program but we do not want this class to depend
       on other classes for this, so sometimes we write redundant
       error handling in different ways to have more robust classes or code.
     */
    if (index >= 0 && index < guestList.length){
      return guestList[index];
    }
    return null;
  }

  public String[] getInfoStrings() {
    /* Write code that returns an array of strings where each element
       represents information about one guest in the list by calling every
       Guest-object's toString method.

       The number of elements in the array should be equal to the
       number of guests currently stored in the list.
       (no strings should be created for empty places at the end of the array st)
    */

    String[] infoStrings = new String[getNbrOfGuests()];
    int index = 0;
    for (int i = 0; i < guestList.length; i++){
      if (guestList[i] != null){
        infoStrings[index++] = guestList[i].toString();
      }
    }
    return infoStrings;
  }

  public String getStatistics(){
    /* Write code that calculates the following statistics and returns these as a
      formatted String using line breaks for each statistic.
      - total number of guests
      - number of adults where an adult guest is someone over the age of 13
      - number of children where a child is someone age 13 or younger
      - the name and age of the oldest guest
      - the name and age of the youngest guest

      If there are no guests in the guest list return a string that
      informs the user of this.
    */
    if (getNbrOfGuests() == 0){
      return "There are no guests in the list.";
    }
    int adults = 0;
    int children = 0;
    Guest oldest = null;
    Guest youngest = null;

    for (int i = 0; i < guestList.length; i++) {
      if (guestList[i] != null){
        Guest guest = guestList[i];
        if (guest.getAge() > 13) {
          adults++;
        } else {
          children++;
        }
        if (oldest == null || guest.getAge() > oldest.getAge()) {
          oldest = guest;
        }
        if (youngest == null || guest.getAge() < youngest.getAge()) {
          youngest = guest;
        }
      }
    }

    //This return statement is just used for the code template. Erase and replace with code for calculating tha statistics as asked for above.
    return ("Number of guests: " + getNbrOfGuests()+ "\n"+
            "Number of adults: " + adults + "\n"+
            "Number of children: " + children + "\n"+
            "Oldest guest is: " + oldest.getFirstName() + " and is " + oldest.getAge() + " years old \n"+
            "Youngest guest: " + youngest.getFirstName() + " and is " + youngest.getAge() + " years old");
  
  }

}