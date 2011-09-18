package org.dynamicprogram;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

/**
 * A dynamic program is a program where several (possibly all) parts can be
 * dynamically modified. Each part of a dynamic program is a dynamic program
 * itself, meaning you can compose programs.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
// TODO manage I/O
// TODO manage listeners (at least for execution)
// TODO manage parts in collections
// TODO update the javadoc to give the constraints to make a part updatable
public abstract class DynamicProgram {

	/**
	 * This method should implement the program to execute, using the different
	 * available parts. Note this method should not be called directly to
	 * execute this program, this is the role of {@link #execute()}.
	 */
	protected abstract void process();

	/**
	 * Execute this program.
	 */
	public void execute() {
		process();
	}

	/**
	 * This method should return all the parts which can be updated. The given
	 * parts can then be updated via the method
	 * {@link #replace(DynamicProgram, DynamicProgram)}, which use the
	 * reflection. It means that whatever the updatable parts are accessible or
	 * not (even private fields), they can be updated if they are given as
	 * updatable parts. In order to avoid mistakes, the final fields are not
	 * allowed to update.
	 * 
	 * @return the parts which can be updated
	 */
	public abstract Collection<? extends DynamicProgram> getUpdatableParts();

	/**
	 * 
	 * @return true if there is updatable parts, false otherwise
	 */
	public boolean hasUpdatableParts() {
		return !getUpdatableParts().isEmpty();
	}

	/**
	 * This method allows to retrieve a specific part giving its class.
	 * 
	 * @param clazz
	 *            the class of the wanted part
	 * @return the part if there is an updatable part corresponding to the
	 *         class, null otherwise
	 */
	@SuppressWarnings("unchecked")
	public <DP extends DynamicProgram> DP getUpdatablePartFromClass(
			Class<DP> clazz) {
		DP part = null;
		for (DynamicProgram program : getUpdatableParts()) {
			if (program.getClass().equals(clazz)) {
				if (part == null) {
					part = (DP) program;
				} else {
					throw new IllegalArgumentException(
							"Several parts correspond to the class "
									+ clazz.getName());
				}
			}
		}
		return part;
	}

	/**
	 * <p>
	 * Replace an current part by another. The parts which can be replaced are
	 * given by {@link #getUpdatableParts()}.
	 * </p>
	 * <p>
	 * It is possible to have an updatable part implemented as a final field. As
	 * the meanings of this two characteristics are in conflict, an exception is
	 * generated if you try to update such a part. This kind of situation has to
	 * be disambiguated by removing the part from the updatables or by removing
	 * its final modifier.
	 * </p>
	 * 
	 * @param current
	 *            the part to replace
	 * @param replacement
	 *            the new part
	 * @throws FinalFieldException
	 *             if the program is given as updatable but is implemented as
	 *             final.
	 */
	public <DP extends DynamicProgram> void replace(DP current, DP replacement) {
		if (!getUpdatableParts().contains(current)) {
			throw new IllegalArgumentException(
					"The updatable parts does not include " + current);
		}
		boolean replaced = false;
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				if (current == field.get(this)) {
					if (Modifier.isFinal(field.getModifiers())) {
						throw new FinalFieldException(field);
					} else {
						field.set(this, replacement);
						replaced = true;
						break;
					}
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException("this case should not occur : "
						+ e);
			} catch (SecurityException e) {
				throw new IllegalStateException("this case should not occur : "
						+ e);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException("this case should not occur : "
						+ e);
			}
		}

		if (!replaced) {
			throw new IllegalStateException(
					"Impossible to replace the program " + current);
		}
	}
}
