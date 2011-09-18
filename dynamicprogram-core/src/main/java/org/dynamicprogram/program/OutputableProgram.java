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
	 * This method should implement a way to read the asked output of this
	 * program. This can be done easily via a {@link DataManager}.
	 * 
	 * @return the manager of the outputs
	 */
	public Output getOutput(ID id);
}
