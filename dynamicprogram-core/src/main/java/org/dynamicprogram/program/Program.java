package org.dynamicprogram.program;

import java.util.Collection;
import java.util.HashSet;

/**
 * A program is an instance able to execute a process.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
// TODO manage I/O
public abstract class Program {
	/**
	 * The registered execution listeners.
	 */
	Collection<ExecutionListener> executionListeners = new HashSet<ExecutionListener>();

	/**
	 * This method should implement the program to execute, using the different
	 * available parts. Note this method should not be called directly to
	 * execute this program, this is the role of {@link #execute()}.
	 */
	protected abstract void process();

	/**
	 * Execute this program. You can be advertised to the execution registering
	 * an {@link ExecutionListener} via
	 * {@link #addExecutionListener(ExecutionListener)}.
	 */
	public void execute() {
		for (ExecutionListener listener : executionListeners) {
			listener.preExecution();
		}
		process();
		for (ExecutionListener listener : executionListeners) {
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
}
