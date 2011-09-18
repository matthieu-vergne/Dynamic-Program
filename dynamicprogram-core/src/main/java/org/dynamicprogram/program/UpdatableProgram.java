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
	 * 
	 * @return the manager of the updatable subprograms
	 */
	public DataManager<Program, ID> getSubprogramManager();
}
