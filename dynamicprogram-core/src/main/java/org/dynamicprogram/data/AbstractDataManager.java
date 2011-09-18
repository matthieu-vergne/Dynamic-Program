package org.dynamicprogram.data;

import java.util.Collection;
import java.util.HashSet;

/**
 * This abstract class implements some convenient features for a
 * {@link DataManager}, like the listeners of the update.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of IDs used to identify the updatable parts. Anything can
 *            be used (enum, integers, objects, etc.). It is possible to use the
 *            parts themselves if you do not want to create specific IDs.
 */
public abstract class AbstractDataManager<Data, ID> implements
		DataManager<Data, ID> {
	/**
	 * The registered update listeners.
	 */
	private final Collection<UpdateListener<Data, ID>> updateListeners = new HashSet<UpdateListener<Data, ID>>();

	/**
	 * Update the asked data. You can be advertised to the update registering an
	 * {@link UpdateListener} via {@link #addUpdateListener(UpdateListener)}.
	 */
	@Override
	public void setData(ID id, Data data) {
		Data oldPart = getData(id);
		internalSetPart(id, data);
		Data newPart = getData(id);
		for (UpdateListener<Data, ID> listener : getUpdateListeners()) {
			listener.dataUpdated(id, oldPart, newPart);
		}
	}

	/**
	 * This method should implement the replacement of a piece of data. It
	 * should be not used directly to update the data, use
	 * {@link #setData(Object, Object)} to do such a replacement.
	 * 
	 * @param id
	 *            the ID of the data to replace
	 * @param data
	 *            the new data
	 */
	protected abstract void internalSetPart(ID id, Data data);

	/**
	 * 
	 * @return the registered update listeners.
	 */
	private Collection<UpdateListener<Data, ID>> getUpdateListeners() {
		return updateListeners;
	}

	/**
	 * Register a listener for the updating.
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addUpdateListener(UpdateListener<Data, ID> listener) {
		updateListeners.add(listener);
	}

	/**
	 * Unregister a listener for the updating.
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeUpdateListener(UpdateListener<Data, ID> listener) {
		updateListeners.remove(listener);
	}
}
