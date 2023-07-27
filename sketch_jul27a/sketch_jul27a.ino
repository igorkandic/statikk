void setup() {
  // put your setup code here, to run once:
  pinMode(13, OUTPUT);
  digitalWrite(13, HIGH);
  Serial.begin(9600);
}

void loop() {
  while (Serial.available() == 0) {}
  String teststr = Serial.readString();
  teststr.trim();
  if(teststr == "LOPinit"){
     Serial.println("success");
  }
  if(teststr == "shock"){
    digitalWrite(13, LOW);
    delay(2500);
    digitalWrite(13, HIGH);
    Serial.println("Done");
  }

}
