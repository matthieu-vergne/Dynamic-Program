package org.dynamicprogram.update;

import org.dynamicprogram.program.Program;

/**
 * The update listener is a way to be advertised of part updates in
 * {@link UpdatableParts}. It is used in {@link AbstractUpdatableParts}.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of IDs used to identify the updatable parts. Anything can
 *            be used (enum, integers, objects, etc.). It is possible to use the
 *            parts themselves if you do not want to create specific IDs.
 */
public interface UpdateListener<ID> {

	/**
	 * 
	 * @param id
	 *            the ID of the updated part
	 * @param oldPart
	 *            the old part
	 * @param newPart
	 *            the new part
	 */
	public void partUpdated(ID id, Program oldPart, Program newPart);
}
