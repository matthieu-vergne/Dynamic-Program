package org.dynamicprogram;

/**
 * This linker allows to link an output to an input. When this linker is used,
 * the data of the registered output is send to the registered input.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Data>
 *            The type of data to manage.
 */
// TODO create builder for composed programs
public abstract class IOLinker<Data> {

	/**
	 * This method should implement a way to get the needed data from a source.
	 * 
	 * @return the data
	 */
	public abstract Data getOutput();

	/**
	 * This method should implement a way to put the given data in a target.
	 * 
	 * @param data
	 *            the data
	 */
	public abstract void setInput(Data data);

	/**
	 * Transfer the data from the given output to the given input.
	 */
	public void link() {
		setInput(getOutput());
	}
}
