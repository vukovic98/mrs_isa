/***********************************************************************
 * Module:  MedicalRecord.java
 * Author:  Vladimir
 * Purpose: Defines the Class MedicalRecord
 ***********************************************************************/

import java.util.*;

/** @pdOid 563a705e-de78-4304-8262-29386163110f */
public class MedicalRecord {
   /** @pdOid e54d785d-eba6-41bb-99df-4e54c25a9f38 */
   private int height;
   /** @pdOid a1441566-7a6a-48bb-abb4-3e40c999d582 */
   private int weight;
   /** @pdOid d9624886-c886-4532-aed8-027cf7ac4d90 */
   private String bloodType;
   /** @pdOid 9c2db058-bac0-4939-97f9-e52e05dd0df6 */
   private int bloodPressure;
   /** @pdOid 73f13957-afb8-4942-9841-8c39c42f93d3 */
   private Collection<String> allergies;
   /** @pdOid a29c0e02-b8b0-4348-8529-8aca5229559c */
   private Collection<String> perscriptions;
   /** @pdOid e0d2babb-5798-45a9-be2a-7df4ed1ba199 */
   private String medicalHistory;
   
   /** @pdRoleInfo migr=no name=Pacient assc=association18 mult=1..1 side=A */
   public Pacient pacient;

}