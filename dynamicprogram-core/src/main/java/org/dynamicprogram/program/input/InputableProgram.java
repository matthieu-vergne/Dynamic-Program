package org.dynamicprogram.program.input;

import java.util.Collection;

import org.dynamicprogram.program.Program;

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
	 * This method should give all the IDs which can identify an input of this
	 * program.
	 * 
	 * @return the possible IDs for the inputs.
	 */
	public abstract Collection<ID> getInputIDs();

	/**
	 * This method should implement a way to write the asked input of this
	 * program.
	 * 
	 * @return the manager of the inputs
	 */
	public void setInput(ID id, Input data);
}
