package handlers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import settings.Settings;

public class SoundHandler {

	private boolean sounds;
	private double volume;
	
	public SoundHandler() {
		this(Settings.sounds, Settings.volume);
	}
	
	public SoundHandler(boolean sounds, double volume) {
		this.sounds = sounds;
		this.volume = volume;
	}
	
	public void playSound(String soundFile) {
		if(!sounds) return;
		
		Media sound = new Media(this.getClass().getResource(soundFile).toExternalForm());
		MediaPlayer player = new MediaPlayer(sound);
		
		player.setVolume(volume);
		
		player.play();
	}
	
}
