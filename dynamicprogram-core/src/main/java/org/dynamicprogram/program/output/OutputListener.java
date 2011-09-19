package org.dynamicprogram.program.output;

/**
 * The output listener is a way to be advertised of output setting in a
 * {@link OutputableProgram}. It is used in {@link AbstractOutputableProgram}.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Output>
 *            The type of output managed. In a general case it will be Object
 *            (to manage several kind of data) but it is not a mandatory.
 * @param <ID>
 *            The type of the IDs used to identify each output. It can be the
 *            output itself.
 */
public interface OutputListener<Output, ID> {

	/**
	 * 
	 * @param id
	 *            the ID of the set output
	 * @param oldOutput
	 *            the old output
	 * @param newOutput
	 *            the new output
	 */
	public void outputUpdated(ID id, Output oldOutput, Output newOutput);
}
