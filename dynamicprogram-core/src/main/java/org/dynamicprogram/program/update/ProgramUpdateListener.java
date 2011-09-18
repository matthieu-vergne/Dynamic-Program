package org.dynamicprogram.program.update;

import org.dynamicprogram.program.Program;

/**
 * The update listener is a way to be advertised of a subprogram setting in a
 * {@link UpdatableProgram}. It is used in {@link AbstractUpdatableProgram}.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 * @param <ID>
 *            The type of the IDs used to identify each subprogram. It can be
 *            the programs themselves.
 */
public interface ProgramUpdateListener<ID> {

	/**
	 * 
	 * @param id
	 *            the ID of the subprogram
	 * @param oldProgram
	 *            the old program
	 * @param newProgram
	 *            the new program
	 */
	public void programUpdated(ID id, Program oldProgram, Program newProgram);
}
