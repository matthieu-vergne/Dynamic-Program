package org.dynamicprogram.linker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.dynamicprogram.data.AbstractDataManager;
import org.dynamicprogram.data.DataManager;
import org.dynamicprogram.program.InputableProgram;
import org.dynamicprogram.program.OutputableProgram;
import org.junit.Test;

public class IOLinkerTest {

	@Test
	public void testLink() {
		class Source implements OutputableProgram<String, Integer> {

			private DataManager<String, Integer> outputManager = new AbstractDataManager<String, Integer>() {
				private final String[] outputs = new String[] { null, null };

				@Override
				public Collection<Integer> getDataIDs() {
					return Arrays.asList(0, 1);
				}

				@Override
				public String getData(Integer id) {
					return outputs[id];
				}

				@Override
				protected void internalSetPart(Integer id, String data) {
					outputs[id] = data;
				}
			};

			@Override
			public DataManager<String, Integer> getOutputManager() {
				return outputManager;
			}

			@Override
			public void execute() {
				// nothing to do
			}

		}

		class Target implements InputableProgram<String, Integer> {

			private DataManager<String, Integer> inputManager = new AbstractDataManager<String, Integer>() {
				private final String[] inputs = new String[] { null };

				@Override
				public Collection<Integer> getDataIDs() {
					return Arrays.asList(0);
				}

				@Override
				public String getData(Integer id) {
					return inputs[id];
				}

				@Override
				protected void internalSetPart(Integer id, String data) {
					inputs[id] = data;
				}
			};

			@Override
			public DataManager<String, Integer> getInputManager() {
				return inputManager;
			}

			@Override
			public void execute() {
				// nothing to do
			}

		}

		IOLinker linker = new IOLinker();
		Source source = new Source();
		linker.setSource(source, 0);
		Target target = new Target();
		linker.setTarget(target, 0);

		String data = "test";
		source.getOutputManager().setData(0, data);
		linker.link();
		assertEquals(data, target.getInputManager().getData(0));

		data = "test 2";
		source.getOutputManager().setData(0, data);
		linker.link();
		assertEquals(data, target.getInputManager().getData(0));

		data = null;
		source.getOutputManager().setData(0, data);
		linker.link();
		assertEquals(data, target.getInputManager().getData(0));

		data = "test 3";
		source.getOutputManager().setData(1, data);
		linker.setSource(source, 1);
		linker.link();
		assertEquals(data, target.getInputManager().getData(0));

		data = "test 4";
		source.getOutputManager().setData(1, data);
		linker.link();
		assertEquals(data, target.getInputManager().getData(0));
	}
}
