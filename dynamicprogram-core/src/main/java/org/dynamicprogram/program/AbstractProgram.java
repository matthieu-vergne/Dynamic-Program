package org.dynamicprogram.program;

import java.util.Collection;
import java.util.HashSet;

/**
 * This abstract class implements some convenient features for a {@link Program}
 * , like the listeners of the execution.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
public abstract class AbstractProgram implements Program {
	/**
	 * The registered execution listeners.
	 */
	private final Collection<ExecutionListener> executionListeners = new HashSet<ExecutionListener>();

	/**
	 * This method should implement the program to execute, using the different
	 * available parts. Note this method should not be called directly to
	 * execute this program, this is the role of {@link #execute()}.
	 */
	protected abstract void internalExecute();

	/**
	 * Execute this program. You can be advertised to the execution registering
	 * an {@link ExecutionListener} via
	 * {@link #addExecutionListener(ExecutionListener)}.
	 */
	public void execute() {
		for (ExecutionListener listener : getExecutionListeners()) {
			listener.preExecution();
		}
		internalExecute();
		for (ExecutionListener listener : getExecutionListeners()) {
			listener.postExecution();
		}
	}

	/**
	 * Register a listener on the execution steps.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addExecutionListener(ExecutionListener listener) {
		executionListeners.add(listener);
	}

	/**
	 * Unregister a listener on the execution steps.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeExecutionListener(ExecutionListener listener) {
		executionListeners.remove(listener);
	}

	/**
	 * 
	 * @return all the registered execution listeners
	 */
	public Collection<ExecutionListener> getExecutionListeners() {
		return executionListeners;
	}
}
