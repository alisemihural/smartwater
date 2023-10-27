#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <SoftwareSerial.h>
SoftwareSerial serial(D6,D5);

#define FIREBASE_HOST "smart-water-monitoring-s-bd2ac-default-rtdb.firebaseio.com" 
#define FIREBASE_AUTH "7cSh3kQPm5k0ll1QD5iYzmXMZ8okYezPscR0B6oo" 
#define WIFI_SSID "POCOPHONE" 
#define WIFI_PASSWORD "YXH7VCLLNPJX" 

int chStat=2;

void setup() {
  serial.begin(9600);
  Serial.begin(9600);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
}
 
void loop() {
     serial.write(Firebase.getInt("PressureChamber"));  
     Serial.println(Firebase.getInt("PressureChamber"));
     /*if(Firebase.getInt("PressureChamber")==1){
      if(chStat==0 or 2){
         serial.write(Firebase.getInt("PressureChamber"));
        delay(75000);
        Firebase.setInt("PressureChamber",0);
        chStat=1;
        } else {
          Firebase.setInt("PressureChamber",0);
        }
      }
     if(Firebase.getInt("PressureChamber")==2){
      if(chStat==1 or 2){
         serial.write(Firebase.getInt("PressureChamber"));
        delay(75000);
        Firebase.setInt("PressureChamber",0);
        chStat=0;
        } else {
          Firebase.setInt("PressureChamber",0);
        }
      }
      if(Firebase.getInt("PressureChamber")==0){
        serial.write(Firebase.getInt("PressureChamber"));
      }
  
     Serial.println(Firebase.getInt("PressureChamber"));

     if (Firebase.failed()) { 
     Serial.print("Error"); 
     Serial.println(Firebase.error());   
     
     return;
 } 
 */
}
