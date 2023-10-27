#include <SoftwareSerial.h>

SoftwareSerial serial(5,6);

byte sensorInterrupt = 0;  
byte sensorPin       = 2;

float calibrationFactor = 8;

volatile byte pulseCount;  

float flowRate;
unsigned int flowMilliLitres;
unsigned long totalMilliLitres;

unsigned long oldTime;

String myString;
int sdata = 0;
void setup()
{
  serial.begin(9600);
  Serial.begin(9600);

  pinMode(sensorPin, INPUT);
  digitalWrite(sensorPin, HIGH);

  pulseCount        = 0;
  flowRate          = 0.0;
  flowMilliLitres   = 0;
  totalMilliLitres  = 0;
  oldTime           = 0;

  attachInterrupt(sensorInterrupt, pulseCounter, FALLING);
}


void loop()
{
   
   if((millis() - oldTime) > 1000) 
  { 
    detachInterrupt(sensorInterrupt);
 
    flowRate = ((1000.0 / (millis() - oldTime)) * pulseCount) / calibrationFactor;
  
    oldTime = millis();

    flowMilliLitres = (flowRate / 60) * 1000;
    
    totalMilliLitres += flowMilliLitres;
      
    unsigned int frac;
    
    Serial.print(int(flowRate));
    
      
    //Serial.print(totalMilliLitres);
    //Serial.print(totalMilliLitres/1000);

    if(serial.available()>0)
    {
      if(int(flowRate)!=0)
        {
        serial.write(int(flowMilliLitres));
        }
    }
   

    pulseCount = 0;

    attachInterrupt(sensorInterrupt, pulseCounter, FALLING);
 
    delay(1000);  
  }
}

void pulseCounter()
{
  pulseCount++;
}
