package org.nl.magiamerlini.data.tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

	public float min();

	public float max();

	public float step();

	public float defaultValue();

	public Alias[] aliases() default {};

}
