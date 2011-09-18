package org.dynamicprogram.program;

import org.dynamicprogram.data.DataManager;

/**
 * An outputable program is a program giving specific outputs.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The kind of ID to use in order to identify the outputs of the
 *            program.
 * @param <Output>
 *            The kind of outputs managed.
 */
public interface OutputableProgram<Output, ID> extends Program {

	/**
	 * 
	 * @return the manager of the outputs
	 */
	public DataManager<Output, ID> getOutputManager();
}
