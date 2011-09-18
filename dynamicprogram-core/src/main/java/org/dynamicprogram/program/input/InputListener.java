package org.dynamicprogram.program.input;

/**
 * The input listener is a way to be advertised of input setting in a
 * {@link InputableProgram}. It is used in {@link AbstractInputableProgram}.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Input>
 *            The type of input managed. In a general case it will be Object (to
 *            manage several kind of data) but it is not a mandatory.
 * @param <ID>
 *            The type of the IDs used to identify each input. It can be the
 *            input itself.
 */
public interface InputListener<Input, ID> {

	/**
	 * 
	 * @param id
	 *            the ID of the set input
	 * @param oldInput
	 *            the old input
	 * @param newInput
	 *            the new input
	 */
	public void inputUpdated(ID id, Input oldInput, Input newInput);
}
