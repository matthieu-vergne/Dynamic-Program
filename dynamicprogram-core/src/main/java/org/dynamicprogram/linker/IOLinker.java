package org.dynamicprogram.linker;

import org.dynamicprogram.data.DataManager;
import org.dynamicprogram.program.InputableProgram;
import org.dynamicprogram.program.OutputableProgram;
import org.dynamicprogram.program.Program;

/**
 * This linker allows to link an output from a {@link OutputableProgram} to an
 * input from an {@link InputableProgram}. When this linker is executed, the
 * data of the registered output is send to the registered input.
 * 
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 * 
 */
// TODO create builder for composed programs
public class IOLinker {
	/**
	 * The source of the data.
	 */
	private Wrapper source;
	/**
	 * The target of the data.
	 */
	private Wrapper target;

	/**
	 * Register the source output.
	 * 
	 * @param program
	 *            the source program
	 * @param id
	 *            the ID of the output to use
	 */
	public <OutputID> void setSource(OutputableProgram<?, OutputID> program,
			OutputID id) {
		source = new Wrapper();
		source.program = program;
		source.id = id;
	}

	/**
	 * Register the target input.
	 * 
	 * @param program
	 *            the target program
	 * @param id
	 *            the ID of the input to use
	 */
	public <InputID> void setTarget(InputableProgram<?, InputID> program,
			InputID id) {
		target = new Wrapper();
		target.program = program;
		target.id = id;
	}

	/**
	 * Transfer the data from the registered source to the registered target.
	 */
	@SuppressWarnings("unchecked")
	public void link() {
		DataManager<Object, Object> inputManager = ((InputableProgram<Object, Object>) target.program)
				.getInputManager();
		DataManager<Object, Object> outputManager = ((OutputableProgram<Object, Object>) source.program)
				.getOutputManager();
		inputManager.setData(target.id, outputManager.getData(source.id));
	}

	private class Wrapper {
		Program program;
		Object id;
	}
}
