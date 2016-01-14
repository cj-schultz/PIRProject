import RPi.GPIO as GPIO
import time

PIRPin = 24  # Set PIR pin number

GPIO.setmode(GPIO.BCM)
GPIO.setup(PIRPin, GPIO.IN)  # Set PIR as input

detected = False  # Keeps track of whether someone has been detected
visitor = False  # Keeps track of if someone entered the room already

# Start sensor after 3 seconds
print("Starting test after 3 seconds...")
time.sleep(1)
print("2..")
time.sleep(1)
print("1..")
time.sleep(1)
print("Begin\n")

# Loop until CTRL-C
while True:
    pirValue = GPIO.input(PIRPin)

    if(pirValue == True and not(detected)):
        print("Motion detected..Welcome!")
        detected = True

    if(pirValue == False and detected):
        print("Reset")
        detected = False






