#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <SoftwareSerial.h>
SoftwareSerial serial(D6,D5);

#define FIREBASE_HOST "smart-water-monitoring-s-bd2ac-default-rtdb.firebaseio.com" 
#define FIREBASE_AUTH "7cSh3kQPm5k0ll1QD5iYzmXMZ8okYezPscR0B6oo" 
#define WIFI_SSID "POCOPHONE" 
#define WIFI_PASSWORD "YXH7VCLLNPJX" 

int debiArduino; //Debi
unsigned long totalML;
float akisHizi;

void setup() {
 serial.begin(9600);
 Serial.begin(9600);
 
 WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
 Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
 
 totalML  = 0;
 akisHizi = 0.0;
 delay(500); 
}
 
void loop() {
  serial.write("s");
  if (serial.available()>0)
  {
    debiArduino=serial.read();
    totalML += debiArduino;
    akisHizi = (debiArduino * 60) / 1000;
    
    Firebase.setBool("WaterMeter1",true);
    Firebase.setInt("Debi1", debiArduino);
    Firebase.setInt("Total1",totalML);
    Firebase.setInt("Total1day",totalML);
    Firebase.setFloat("Hiz1",akisHizi);
    
  } else{
    Firebase.setBool("WaterMeter1",false);
  }
  
    
  if (Firebase.failed()) { 
     Serial.print("setting /number failed:"); 
     Serial.println(Firebase.error());   
     return;
     }
 
 delay(1000); 
 
}
