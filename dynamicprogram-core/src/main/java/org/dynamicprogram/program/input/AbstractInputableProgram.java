package org.dynamicprogram.program.input;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This abstract class implements a simple way to manage inputs via a
 * {@link HashMap}. A protected method is added in order to access the inputs
 * inside the class. The listeners {@link InputListener} are also managed.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Input>
 *            The kind of inputs. In a general case it will be {@link Object}
 *            (to manage several kind of data) but it is not a mandatory.
 * @param <ID>
 *            The type of the IDs used to identify each input. It can be the
 *            data themselves.
 */
public abstract class AbstractInputableProgram<Input, ID> implements
		InputableProgram<Input, ID> {

	/**
	 * The inputs.
	 */
	private final Map<ID, Input> inputs = new HashMap<ID, Input>();
	/**
	 * The listeners for these inputs.
	 */
	private final Collection<InputListener<Input, ID>> listeners = new HashSet<InputListener<Input, ID>>();

	/**
	 * This method should give all the IDs which can identify an input of this
	 * program.
	 * 
	 * @return the possible IDs for the inputs.
	 */
	public abstract Collection<ID> getInputIDs();

	/**
	 * Modify the asked input. This operation generate a {@link InputListener}
	 * event.
	 */
	@Override
	public void setInput(ID id, Input data) {
		checkID(id);
		Input oldInput = getInput(id);
		inputs.put(id, data);
		Input newInput = getInput(id);
		for (InputListener<Input, ID> listener : listeners) {
			listener.inputUpdated(id, oldInput, newInput);
		}
	}

	/**
	 * Give the asked input.
	 * 
	 * @param id
	 *            the ID of the needed input
	 * @return the input
	 */
	protected Input getInput(ID id) {
		checkID(id);
		return inputs.get(id);
	}

	/**
	 * Check the given ID is known.
	 * 
	 * @param id
	 *            the ID to check
	 */
	private void checkID(ID id) {
		if (!getInputIDs().contains(id)) {
			throw new IllegalArgumentException(id + " is not a known ID.");
		}
	}

	/**
	 * Register a listener for the input setting.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addInputListener(InputListener<Input, ID> listener) {
		listeners.add(listener);
	}

	/**
	 * Unregister a listener for the input setting.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeInputListener(InputListener<Input, ID> listener) {
		listeners.remove(listener);
	}

	/**
	 * 
	 * @return all the registered listeners for the input setting
	 */
	public Collection<InputListener<Input, ID>> getInputListeners() {
		return listeners;
	}
}
