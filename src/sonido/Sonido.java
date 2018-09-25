package sonido;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
	
	public static void playSound(String soundName) {
	 
	   try 
	   {
		Clip clip = AudioSystem.getClip( );
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	    
	    clip.open(audioInputStream);
	    
	    clip.start( );
	   }
	   catch(Exception ex)
	   {
	     System.out.println(ex);
	     ex.printStackTrace( );
	   }
		

	      
		
	}
	
    public static Clip music(String audio) {
        Clip clip = null;
 	   try 
 	   {
 		clip = AudioSystem.getClip( );
 	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile( ));
 	   
 	    clip.open(audioInputStream);
 	    clip.loop(Clip.LOOP_CONTINUOUSLY);
 	   
 	   }
 	   catch(Exception ex)
 	   {
 	     System.out.println(ex);
 	     ex.printStackTrace( );
 	   }
 	  return clip;
    
    }
    
    public static void stop(Clip clip) {
    	clip.stop();
    }
    


}