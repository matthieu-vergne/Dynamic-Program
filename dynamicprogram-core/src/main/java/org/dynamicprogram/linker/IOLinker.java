package org.dynamicprogram.linker;

import org.dynamicprogram.program.input.InputableProgram;
import org.dynamicprogram.program.output.OutputableProgram;

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
	private OutputableProgram<Object, Object> out;
	/**
	 * The source ID of the data.
	 */
	private Object outId;
	/**
	 * The target of the data.
	 */
	private InputableProgram<Object, Object> in;
	/**
	 * The target ID of the data.
	 */
	private Object inId;

	/**
	 * Register the source output.
	 * 
	 * @param program
	 *            the source program
	 * @param id
	 *            the ID of the output to use
	 */
	@SuppressWarnings("unchecked")
	public <OutputID> void setSource(OutputableProgram<?, OutputID> program,
			OutputID id) {
		out = (OutputableProgram<Object, Object>) program;
		outId = id;
	}

	/**
	 * Register the target input.
	 * 
	 * @param program
	 *            the target program
	 * @param id
	 *            the ID of the input to use
	 */
	@SuppressWarnings("unchecked")
	public <InputID> void setTarget(InputableProgram<?, InputID> program,
			InputID id) {
		in = (InputableProgram<Object, Object>) program;
		inId = id;
	}

	/**
	 * Transfer the data from the registered source to the registered target.
	 */
	public void link() {
		in.setInput(inId, out.getOutput(outId));
	}
}
