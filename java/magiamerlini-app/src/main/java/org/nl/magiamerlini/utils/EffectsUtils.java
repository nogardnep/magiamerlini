package org.nl.magiamerlini.utils;

import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.effects.audio.ReverbAudioEffect;

public class EffectsUtils {
	public static Class<?> getAudioEffectTypeCorrespondingTo(int number) {
		Class<?> type = null;

		switch (number) {
		case 0:
			type = ReverbAudioEffect.class;
			break;
		}

		return type;
	}

	public static Class<?> getVideoEffectTypeCorrespondingTo(int number) {
		Class<?> type = null;

		switch (number) {
			// TODO
		}

		return type;
	}

}
