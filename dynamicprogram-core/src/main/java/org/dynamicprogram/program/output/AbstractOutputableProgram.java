package org.dynamicprogram.program.output;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This abstract class implements a simple way to manage outputs via a
 * {@link HashMap}. A protected method is added in order to change the outputs
 * inside the class. The listeners {@link OutputListener} are also managed.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Output>
 *            The kind of outputs. In a general case it will be {@link Object}
 *            (to manage several kind of data) but it is not a mandatory.
 * @param <ID>
 *            The type of the IDs used to identify each output. It can be the
 *            data themselves.
 */
public abstract class AbstractOutputableProgram<Output, ID> implements
		OutputableProgram<Output, ID> {

	/**
	 * The outputs.
	 */
	private final Map<ID, Output> outputs = new HashMap<ID, Output>();
	/**
	 * The listeners for these outputs.
	 */
	private final Collection<OutputListener<Output, ID>> listeners = new HashSet<OutputListener<Output, ID>>();

	/**
	 * Modify the asked output. This operation generate a {@link OutputListener}
	 * event.
	 * 
	 * @param id
	 *            the ID of the needed output
	 * @param data
	 *            the output
	 */
	protected void setOutput(ID id, Output data) {
		checkID(id);
		Output oldOutput = getOutput(id);
		outputs.put(id, data);
		Output newOutput = getOutput(id);
		for (OutputListener<Output, ID> listener : listeners) {
			listener.outputUpdated(id, oldOutput, newOutput);
		}
	}

	/**
	 * Give the asked output.
	 */
	@Override
	public Output getOutput(ID id) {
		checkID(id);
		return outputs.get(id);
	}

	/**
	 * Check the given ID is known.
	 * 
	 * @param id
	 *            the ID to check
	 */
	private void checkID(ID id) {
		if (!getOutputIDs().contains(id)) {
			throw new IllegalArgumentException(id + " is not a known ID.");
		}
	}

	/**
	 * Register a listener for the output setting.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addOutputListener(OutputListener<Output, ID> listener) {
		listeners.add(listener);
	}

	/**
	 * Unregister a listener for the output setting.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeOutputListener(OutputListener<Output, ID> listener) {
		listeners.remove(listener);
	}

	/**
	 * 
	 * @return all the registered listeners for the Output setting
	 */
	public Collection<OutputListener<Output, ID>> getOutputListeners() {
		return listeners;
	}
}
