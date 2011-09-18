package org.dynamicprogram.program;

import org.dynamicprogram.data.DataManager;

/**
 * An updatable program is a program where some parts can be dynamically
 * modified.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The kind of ID to use in order to identify the updatable
 *            subprograms.
 */
public interface UpdatableProgram<ID> extends Program {

	/**
	 * This method should implement a way to update the asked subprogram. This
	 * can be done easily via a {@link DataManager}.
	 * 
	 * @param id
	 *            the Id of the subprogram
	 * @param program
	 *            the program to use
	 */
	public void setSubprogram(ID id, Program program);

	/**
	 * This method should implement a way to get the asked subprogram. This can
	 * be done easily via a {@link DataManager}.
	 * 
	 * @param id
	 *            the id of the subprogram
	 * @return the subprogram
	 */
	public Program getSubprogram(ID id);
}
