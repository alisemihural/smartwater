#include <SoftwareSerial.h>

SoftwareSerial serial(5,6);
int PCStat;

void setup() {
 pinMode(3,OUTPUT);
 pinMode(2,OUTPUT);
 
 serial.begin(9600);
 Serial.begin(9600);
 
 delay(5000);

}

void loop() {
  
   if(serial.available()>0)
   {
      PCStat=serial.read();
      Serial.println(PCStat);
   }
   
  if(PCStat==1){
   HighPres();
   return;
  }
  else if(PCStat==2){
   LowPres();
   return;
  }else if (PCStat==4){
    Stop();
  }
  
}


void HighPres(){
  digitalWrite(2,HIGH);
  digitalWrite(3,LOW);
}

void LowPres(){
 digitalWrite(3,HIGH);
 digitalWrite(2,LOW);
}


void Stop(){
  digitalWrite(2,LOW);
  digitalWrite(3,LOW);
  }
