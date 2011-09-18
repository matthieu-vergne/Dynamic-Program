package org.dynamicprogram.update;

import java.util.Collection;

import org.dynamicprogram.program.Program;

/**
 * Updatable parts are identified programs which can be dynamically replaced.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of IDs used to identify the updatable parts. Anything can
 *            be used (enum, integers, objects, etc.). It is possible to use the
 *            parts themselves if you do not want to create specific IDs.
 */
public interface UpdatableParts<ID> {

	/**
	 * This method should return all the IDs updatable parts.
	 * 
	 * @return the IDs of the parts which can be updated
	 */
	public Collection<ID> getPartIDs();

	/**
	 * This method should implement a way to set an updatable part.
	 * 
	 * @param id
	 *            the ID of the part to set
	 * @param program
	 *            the program to use for this part
	 */
	public void setPart(ID id, Program program);

	/**
	 * This method should implement a way to get the part corresponding to the
	 * given ID.
	 * 
	 * @param id
	 *            the ID of the part
	 * @return the identified part
	 */
	public Program getPart(ID id);
}
