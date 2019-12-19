package org.nl.magiamerlini.data.items;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.nl.magiamerlini.components.BaseComponent;
import org.nl.magiamerlini.data.tools.Parameter;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;
import org.nl.magiamerlini.utils.Logger;

import com.google.common.base.CaseFormat;

public abstract class Item {
	protected Logger logger;

	public Item() {
		this.logger = new Logger(BaseComponent.class.getSimpleName(), true);
		applyDefaultValues();
	}

	public List<ParameterSnapshot> getParameters() {
		List<ParameterSnapshot> parameters = new ArrayList<ParameterSnapshot>();

		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Parameter.class)) {
				Column column = field.getAnnotation(Column.class);
				Parameter parameter = field.getAnnotation(Parameter.class);
				float value = parameter.defaultValue();

				try {
					value = (float) this.getClass().getMethod(getGetMethodName(column.name())).invoke(this);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}

				parameters.add(new ParameterSnapshot(column.name(), value, parameter.min(), parameter.max(),
						parameter.step()));
			}
		}

		return parameters;
	}

	public void applyDefaultValues() {
		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Parameter.class)) {
				Column column = field.getAnnotation(Column.class);
				Parameter parameter = field.getAnnotation(Parameter.class);

				try {
					this.getClass().getMethod(getSetMethodName(column.name()), float.class).invoke(this,
							parameter.defaultValue());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void applyParameter(ParameterSnapshot snapshot) {
		try {
			this.getClass().getMethod(getSetMethodName(snapshot.getName()), float.class).invoke(this,
					snapshot.getValue());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}

	public String toDisplay() {
		return this.getClass().getSimpleName();
	}

	private String getSetMethodName(String name) {
		return "set" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
	}

	private String getGetMethodName(String name) {
		return "get" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
	}
}
