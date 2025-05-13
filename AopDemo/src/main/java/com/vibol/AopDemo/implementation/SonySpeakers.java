package com.vibol.AopDemo.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.vibol.AopDemo.interfaces.Speakers;
import com.vibol.AopDemo.model.Song;


@Component
@Primary
public class SonySpeakers implements Speakers{

	@Override
	public String makeSound(Song song) {
		return "Playing the song "+ song.getTitle()+ " by "
                + song.getSingerName()+
                " with Sony speakers";
	}

}
