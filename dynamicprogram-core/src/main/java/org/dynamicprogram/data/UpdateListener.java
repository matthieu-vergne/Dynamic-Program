package org.dynamicprogram.data;

/**
 * The update listener is a way to be advertised of data updates in
 * {@link DataManager}. It is used in {@link AbstractDataManager}.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <Data>
 *            The data which are wrapped by this manager. In a general case it
 *            will be Object (to manage several kind of data) but it is not a
 *            mandatory.
 * @param <ID>
 *            The type of the IDs used to identify each piece of data. It can be
 *            the data themselves
 */
public interface UpdateListener<Data, ID> {

	/**
	 * 
	 * @param id
	 *            the ID of the updated data
	 * @param oldPart
	 *            the old data
	 * @param newPart
	 *            the new data
	 */
	public void dataUpdated(ID id, Data oldData, Data newData);
}
