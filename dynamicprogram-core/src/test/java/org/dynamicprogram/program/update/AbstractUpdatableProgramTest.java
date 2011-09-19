package org.dynamicprogram.program.update;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.dynamicprogram.program.AbstractProgram;
import org.dynamicprogram.program.Program;
import org.junit.Test;

public class AbstractUpdatableProgramTest {

	class Subprogram extends AbstractProgram {

		@Override
		protected void internalExecute() {
			// nothing to do
		}
	}

	@Test
	public void testUpdate() {
		AbstractUpdatableProgram<Integer> program = new AbstractUpdatableProgram<Integer>() {

			@Override
			public void execute() {
				// nothing to do
			}

			@Override
			public Collection<Integer> getSubprogramIDs() {
				return Arrays.asList(0, 1);
			}
		};

		assertNull(program.getSubprogram(0));
		assertNull(program.getSubprogram(1));

		Program p0 = new Subprogram();
		program.setSubprogram(0, p0);
		assertEquals(p0, program.getSubprogram(0));
		assertNull(program.getSubprogram(1));

		Program p1 = new Subprogram();
		program.setSubprogram(1, p1);
		assertEquals(p0, program.getSubprogram(0));
		assertEquals(p1, program.getSubprogram(1));
	}

	@Test
	public void testListeners() {
		AbstractUpdatableProgram<Integer> program = new AbstractUpdatableProgram<Integer>() {

			@Override
			public void execute() {
				// nothing to do
			}

			@Override
			public Collection<Integer> getSubprogramIDs() {
				return Arrays.asList(0, 1);
			}
		};
		final Object[] update = new Object[3];
		final Boolean[] updated = new Boolean[] { false };
		program.addProgramUpdateListener(new ProgramUpdateListener<Integer>() {

			@Override
			public void programUpdated(Integer id, Program oldProgram,
					Program newProgram) {
				assertArrayEquals(update, new Object[] { id, oldProgram,
						newProgram });
				updated[0] = true;
			}
		});

		update[0] = 0;
		update[1] = null;
		update[2] = new Subprogram();
		updated[0] = false;
		program.setSubprogram((Integer) update[0], (Program) update[2]);
		assertTrue(updated[0]);

		update[0] = 0;
		update[1] = update[2];
		update[2] = new Subprogram();
		updated[0] = false;
		program.setSubprogram((Integer) update[0], (Program) update[2]);
		assertTrue(updated[0]);

		update[0] = 1;
		update[1] = null;
		update[2] = new Subprogram();
		updated[0] = false;
		program.setSubprogram((Integer) update[0], (Program) update[2]);
		assertTrue(updated[0]);
	}

}
