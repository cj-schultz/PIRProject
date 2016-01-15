/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirtesting;
import com.pi4j.io.gpio.*;
import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * @author colinschultz
 */
public class PIRTesting {
    private static int PIR_PIN = 4;
    
    public static void main(String args[]) throws InterruptedException{
        GpioController gpio = GpioFactory.getInstance();
        
        final GpioPinDigitalInput pirSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05);
        
        System.out.println("Starting test...after 3 seconds");
        Thread.sleep(1000);
        System.out.println("2..");
        Thread.sleep(1000);
        System.out.println("1..");
        Thread.sleep(1000);
        System.out.println("Begin\n");
        boolean detected = false;
        boolean visitor = false;
        while(true){
            if(pirSensor.getState() == LOW && detected){
                System.out.println("RESET");
                detected = false;
            }
            if(pirSensor.getState() == HIGH && !detected){
                System.out.println("MOTION DETECTED");
                detected = true;
                if(!visitor){
                    System.out.println("visitor entered..");
                    visitor = true;
                    playSound();
                }else{
                    System.out.println("visitor has left the building");
                    visitor = false;
                }
            }
        }
    }
    
    static void playSound(){
        File sound = new File("/home/pi/seinfeld.wav");
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);                        
        }catch(Exception e){
             System.out.println(e);
        } 
    }
}
