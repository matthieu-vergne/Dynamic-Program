package org.dynamicprogram.program;

import org.dynamicprogram.data.DataManager;

/**
 * An inputable program is a program asking for specific inputs.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The kind of ID to use in order to identify the inputs of the
 *            program.
 * @param <Input>
 *            The kind of inputs managed.
 */
public interface InputableProgram<Input, ID> extends Program {

	/**
	 * This method should implement a way to write the asked input of this
	 * program. This can be done easily via a {@link DataManager}.
	 * 
	 * @return the manager of the inputs
	 */
	public void setInput(ID id, Input data);
}
