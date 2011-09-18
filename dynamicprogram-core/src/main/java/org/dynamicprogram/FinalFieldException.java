package org.dynamicprogram;

import java.lang.reflect.Field;

@SuppressWarnings("serial")
public class FinalFieldException extends RuntimeException {

	public FinalFieldException(Field field) {
		super("The field " + field.getName()
				+ " is declared as an updatable part but is final.");
	}
}
