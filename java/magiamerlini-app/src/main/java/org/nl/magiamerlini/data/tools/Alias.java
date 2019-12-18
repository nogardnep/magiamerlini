package org.nl.magiamerlini.data.tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Alias {
	public float value();

	public String name();
}
