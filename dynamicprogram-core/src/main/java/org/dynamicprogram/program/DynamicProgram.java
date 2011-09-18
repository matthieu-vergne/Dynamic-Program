package org.dynamicprogram.program;

import java.util.Collection;

/**
 * A dynamic program is a program where several parts can be dynamically
 * modified. Each part of a dynamic program is a program itself, meaning you can
 * compose programs.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
// TODO manage I/O
public abstract class DynamicProgram extends Program {

	/**
	 * This method should return all the parts which can be updated.
	 * 
	 * @return the parts which can be updated
	 */
	public abstract Collection<? extends Program> getUpdatableParts();

	/**
	 * 
	 * @return true if there is updatable parts, false otherwise
	 */
	public boolean hasUpdatableParts() {
		return !getUpdatableParts().isEmpty();
	}

	/**
	 * This method should implement a way to replace an updatable part by
	 * another part. A constraint is given on the type of the part : you cannot
	 * replace a part by a completely different one, they have to be from the
	 * same class. If you need to allow several classes for the same part, you
	 * need a common parent class. Then you can use this method casting both
	 * parts with the parent class.
	 * 
	 * @param current
	 *            the part to replace
	 * @param replacement
	 *            the new part
	 */
	public abstract <DP extends Program> void replace(DP current, DP replacement);
}
