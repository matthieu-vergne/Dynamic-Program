package org.dynamicprogram.linker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.dynamicprogram.program.input.InputableProgram;
import org.dynamicprogram.program.output.OutputableProgram;
import org.junit.Test;

public class IOLinkerTest {

	@Test
	public void testLink() {
		class Source implements OutputableProgram<String, Integer> {

			public final String[] outputs = new String[] { null, null };

			@Override
			public void execute() {
				// nothing to do
			}
			
			@Override
			public Collection<Integer> getOutputIDs() {
				return Arrays.asList(0, 1);
			}

			@Override
			public String getOutput(Integer id) {
				return outputs[id];
			}

		}

		class Target implements InputableProgram<String, Integer> {

			public final String[] inputs = new String[] { null };

			@Override
			public void execute() {
				// nothing to do
			}
			
			@Override
			public Collection<Integer> getInputIDs() {
				return Arrays.asList(0);
			}

			@Override
			public void setInput(Integer id, String data) {
				inputs[id] = data;
			}

		}

		IOLinker linker = new IOLinker();
		Source source = new Source();
		linker.setSource(source, 0);
		Target target = new Target();
		linker.setTarget(target, 0);

		String data = "test";
		source.outputs[0] = data;
		linker.link();
		assertEquals(data, target.inputs[0]);

		data = "test 2";
		source.outputs[0] = data;
		linker.link();
		assertEquals(data, target.inputs[0]);

		data = null;
		source.outputs[0] = data;
		linker.link();
		assertEquals(data, target.inputs[0]);

		data = "test 3";
		source.outputs[1] = data;
		linker.setSource(source, 1);
		linker.link();
		assertEquals(data, target.inputs[0]);

		data = "test 4";
		source.outputs[1] = data;
		linker.link();
		assertEquals(data, target.inputs[0]);
	}
}
