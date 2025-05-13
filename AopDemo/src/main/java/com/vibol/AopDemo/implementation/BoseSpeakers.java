package com.vibol.AopDemo.implementation;

import org.springframework.stereotype.Component;

import com.vibol.AopDemo.interfaces.Speakers;
import com.vibol.AopDemo.model.Song;

@Component
public class BoseSpeakers implements Speakers {

	@Override
	public String makeSound(Song song) {
		return "Playing the song " + song.getTitle() + " by " + song.getSingerName() + " with Bose speakers";
	}

}
