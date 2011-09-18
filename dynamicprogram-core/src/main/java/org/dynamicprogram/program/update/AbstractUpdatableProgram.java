package org.dynamicprogram.program.update;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.dynamicprogram.program.Program;

/**
 * This abstract class implements a simple way to manage subprograms via a
 * {@link HashMap}. The listeners {@link ProgramUpdateListener} are also
 * managed.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of the IDs used to identify each subprogram. It can be
 *            the programs themselves.
 */
public abstract class AbstractUpdatableProgram<ID> implements
		UpdatableProgram<ID> {

	/**
	 * The subprograms.
	 */
	private final Map<ID, Program> subprograms = new HashMap<ID, Program>();
	/**
	 * The listeners for these updates.
	 */
	private final Collection<ProgramUpdateListener<ID>> listeners = new HashSet<ProgramUpdateListener<ID>>();

	/**
	 * This method should give all the IDs which can identify a subprogram of
	 * this program.
	 * 
	 * @return the possible IDs for the subprograms
	 */
	public abstract Collection<ID> getSubprogramIDs();

	/**
	 * Modify the asked program. This operation generate a
	 * {@link ProgramUpdateListener} event.
	 * 
	 * @param id
	 *            the ID of the needed program
	 * @param program
	 *            the program
	 */
	@Override
	public void setSubprogram(ID id, Program program) {
		checkID(id);
		Program oldUpdate = getSubprogram(id);
		subprograms.put(id, program);
		Program newUpdate = getSubprogram(id);
		for (ProgramUpdateListener<ID> listener : listeners) {
			listener.programUpdated(id, oldUpdate, newUpdate);
		}
	}

	/**
	 * Give the asked program.
	 */
	@Override
	public Program getSubprogram(ID id) {
		checkID(id);
		return subprograms.get(id);
	}

	/**
	 * Check the given ID is known.
	 * 
	 * @param id
	 *            the ID to check
	 */
	private void checkID(ID id) {
		if (!getSubprogramIDs().contains(id)) {
			throw new IllegalArgumentException(id + " is not a known ID.");
		}
	}

	/**
	 * Register a listener for the program updating.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addProgramUpdateListener(ProgramUpdateListener<ID> listener) {
		listeners.add(listener);
	}

	/**
	 * Unregister a listener for the program updating.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeProgramUpdateListener(ProgramUpdateListener<ID> listener) {
		listeners.remove(listener);
	}

	/**
	 * 
	 * @return all the registered listeners for the program updating
	 */
	public Collection<ProgramUpdateListener<ID>> getUpdateListeners() {
		return listeners;
	}
}
