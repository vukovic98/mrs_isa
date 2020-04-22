/***********************************************************************
 * Module:  User.java
 * Author:  Vladimir
 * Purpose: Defines the Class User
 ***********************************************************************/

import java.util.*;

/** @pdOid 431c6b2a-a928-44d2-91fc-57a50cbd9260 */
public class User {
   /** @pdOid 7c8620d1-7c2f-4e10-a55d-a18076eeac86 */
   private String email;
   /** @pdOid 394cdf2c-7e6a-418e-bec4-cc6b97eb4047 */
   private String password;
   /** @pdOid 923d74d8-58dc-4b7c-a63f-f2437eef2c13 */
   private String firstName;
   /** @pdOid 7c14c431-e096-4eca-bc43-1d91b8d6a01e */
   private String lastName;
   /** @pdOid e4414c65-2f1f-476e-9b5d-daa33c0d91db */
   private String address;
   /** @pdOid d42d3e51-dfe7-44da-bccf-b8fbaca76070 */
   private String city;
   /** @pdOid 58964c73-729b-41ff-89a0-a51fd0b1bbc4 */
   private String country;
   /** @pdOid 3b00c820-52bc-4b91-9498-8a22357cd35d */
   private String phoneNumber;
   /** @pdOid a23b41a9-6b06-427b-ab24-ae3a4916300f */
   private String insuranceNumber;
   
   /** @pdRoleInfo migr=no name=RegistrationRequest assc=association13 mult=0..1 */
   public RegistrationRequest registrationRequest;

}