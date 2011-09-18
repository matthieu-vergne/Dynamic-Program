package org.dynamicprogram.program;

import java.util.Collection;

/**
 * A dynamic program is a program where several parts can be dynamically
 * modified. Each part of a dynamic program is a program itself, meaning you can
 * compose programs.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of IDs used to identify the updatable parts. Anything can
 *            be used (enum, integers, objects, etc.). It is possible to use the
 *            parts themselves if you do not want to create specific IDs.
 */
public abstract class DynamicProgram<ID> extends Program {

	/**
	 * This method should return all the IDs identifying updatable parts.
	 * 
	 * @return the IDs of the parts which can be updated
	 */
	public abstract Collection<ID> getUpdatableIDs();

	/**
	 * 
	 * @return true if there is updatable parts, false otherwise
	 */
	public boolean hasUpdatableParts() {
		return !getUpdatableIDs().isEmpty();
	}

	/**
	 * This method should implement a way to set an updatable part.
	 * 
	 * @param id
	 *            the ID of the part to set
	 * @param program
	 *            the program to use for this part
	 */
	public abstract <DP extends Program> void setPart(ID id, DP program);
}
