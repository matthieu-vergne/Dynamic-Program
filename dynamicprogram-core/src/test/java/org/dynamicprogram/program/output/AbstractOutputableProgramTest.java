package org.dynamicprogram.program.output;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class AbstractOutputableProgramTest {

	@Test
	public void testUpdate() {
		AbstractOutputableProgram<String, Integer> program = new AbstractOutputableProgram<String, Integer>() {

			@Override
			public void execute() {
				// nothing to do
			}

			@Override
			public Collection<Integer> getOutputIDs() {
				return Arrays.asList(0, 1);
			}
		};

		assertNull(program.getOutput(0));
		assertNull(program.getOutput(1));

		String s0 = "aze";
		program.setOutput(0, s0);
		assertEquals(s0, program.getOutput(0));
		assertNull(program.getOutput(1));

		String s1 = "test";
		program.setOutput(1, s1);
		assertEquals(s0, program.getOutput(0));
		assertEquals(s1, program.getOutput(1));
	}

	@Test
	public void testListeners() {
		AbstractOutputableProgram<String, Integer> program = new AbstractOutputableProgram<String, Integer>() {

			@Override
			public void execute() {
				// nothing to do
			}

			@Override
			public Collection<Integer> getOutputIDs() {
				return Arrays.asList(0, 1);
			}
		};
		final Object[] update = new Object[3];
		final Boolean[] updated = new Boolean[] { false };
		program.addOutputListener(new OutputListener<String, Integer>() {

			@Override
			public void outputUpdated(Integer id, String oldOutput,
					String newOutput) {
				assertArrayEquals(update, new Object[] { id, oldOutput,
						newOutput });
				updated[0] = true;
			}
		});

		update[0] = 0;
		update[1] = null;
		update[2] = "aze";
		updated[0] = false;
		program.setOutput((Integer) update[0], (String) update[2]);
		assertTrue(updated[0]);

		update[0] = 0;
		update[1] = "aze";
		update[2] = "aze";
		updated[0] = false;
		program.setOutput((Integer) update[0], (String) update[2]);
		assertTrue(updated[0]);

		update[0] = 1;
		update[1] = null;
		update[2] = "test";
		updated[0] = false;
		program.setOutput((Integer) update[0], (String) update[2]);
		assertTrue(updated[0]);
	}

}
