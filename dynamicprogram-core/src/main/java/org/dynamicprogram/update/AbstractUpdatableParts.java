package org.dynamicprogram.update;

import java.util.Collection;
import java.util.HashSet;

import org.dynamicprogram.program.AbstractProgram;
import org.dynamicprogram.program.Program;

/**
 * This abstract class implements some convenient features for a
 * {@link UpdatableParts}, like the listeners of the update.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of IDs used to identify the updatable parts. Anything can
 *            be used (enum, integers, objects, etc.). It is possible to use the
 *            parts themselves if you do not want to create specific IDs.
 */
public abstract class AbstractUpdatableParts<ID> implements UpdatableParts<ID> {
	/**
	 * The registered update listeners.
	 */
	private final Collection<UpdateListener<ID>> updateListeners = new HashSet<UpdateListener<ID>>();

	/**
	 * Update the asked part. You can be advertised to the update registering an
	 * {@link UpdateListener} via {@link #addUpdateListener(UpdateListener)}.
	 */
	@Override
	public void setPart(ID id, Program program) {
		Program oldPart = getPart(id);
		internalSetPart(id, program);
		Program newPart = getPart(id);
		for (UpdateListener<ID> listener : getUpdateListeners()) {
			listener.partUpdated(id, oldPart, newPart);
		}
	}

	/**
	 * This method should implement the replacement of a part. It should be not
	 * used directly to update a part, use
	 * {@link #setPart(Object, AbstractProgram)} to do such a replacement.
	 * 
	 * @param id
	 *            the ID of the part to replace
	 * @param newPart
	 *            the new part
	 */
	protected abstract void internalSetPart(ID id, Program program);

	/**
	 * 
	 * @return the registered update listeners.
	 */
	private Collection<UpdateListener<ID>> getUpdateListeners() {
		return updateListeners;
	}

	/**
	 * Register a listener for the updating.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addUpdateListener(UpdateListener<ID> listener) {
		getUpdateListeners().add(listener);
	}

	/**
	 * Unregister a listener for the updating.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeUpdateListener(UpdateListener<ID> listener) {
		getUpdateListeners().remove(listener);
	}
}
