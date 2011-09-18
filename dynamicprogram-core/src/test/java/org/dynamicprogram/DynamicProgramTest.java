package org.dynamicprogram;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

public class DynamicProgramTest {

	@Test
	public void testUpdate() {
		// define internal programs
		class SubProgram extends DynamicProgram {
			@Override
			protected void process() {
				// nothing to do
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Collections.emptyList();
			}
		}

		// define root program
		final SubProgram subProgram = new SubProgram();
		class ProgramRoot extends DynamicProgram {
			private SubProgram p1 = subProgram;
			private SubProgram p2 = new SubProgram();

			@Override
			protected void process() {
				p1.process();
				p2.process();
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Arrays.asList(p1);
			}
		}
		ProgramRoot program = new ProgramRoot();

		// tests
		assertTrue(program.hasUpdatableParts());
		assertFalse(subProgram.hasUpdatableParts());

		Collection<DynamicProgram> subprograms = new HashSet<DynamicProgram>();
		subprograms.add(subProgram);
		assertTrue(program.getUpdatableParts().containsAll(subprograms));
		assertTrue(subprograms.containsAll(program.getUpdatableParts()));

		SubProgram program1B = new SubProgram();
		program.replace(subProgram, program1B);
		subprograms.clear();
		subprograms.add(program1B);
		assertTrue(program.getUpdatableParts().containsAll(subprograms));
		assertTrue(subprograms.containsAll(program.getUpdatableParts()));
	}

	@Test
	public void testFinalUpdate() {
		// define internal programs
		class SubProgram extends DynamicProgram {
			@Override
			protected void process() {
				// nothing to do
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Collections.emptyList();
			}
		}

		// define root program
		final SubProgram program1 = new SubProgram();
		class ProgramRoot extends DynamicProgram {
			private final SubProgram p1 = program1;
			private SubProgram p2 = new SubProgram();

			@Override
			protected void process() {
				p1.process();
				p2.process();
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Arrays.asList(p1);
			}
		}
		ProgramRoot program = new ProgramRoot();

		// tests
		Collection<DynamicProgram> subprograms = new HashSet<DynamicProgram>();
		subprograms.add(program1);
		assertTrue(program.getUpdatableParts().containsAll(subprograms));
		assertTrue(subprograms.containsAll(program.getUpdatableParts()));

		SubProgram program1B = new SubProgram();
		try {
			program.replace(program1, program1B);
			fail("No exception thrown.");
		} catch (FinalFieldException ex) {
		}
	}

	@Test
	public void testExecution() {
		// define internal programs
		abstract class SubProgram extends DynamicProgram {
			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Collections.emptyList();
			}
		}

		// define root program
		final Boolean executed[] = new Boolean[] { false };
		final SubProgram program1 = new SubProgram() {
			@Override
			protected void process() {
				executed[0] = true;
			}
		};
		class ProgramRoot extends DynamicProgram {
			private SubProgram p1 = program1;

			@Override
			protected void process() {
				p1.process();
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Arrays.asList(p1);
			}
		}
		ProgramRoot program = new ProgramRoot();

		// tests execution
		assertArrayEquals(new Boolean[] { false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true }, executed);

		Arrays.fill(executed, false);
		assertArrayEquals(new Boolean[] { false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true }, executed);

		SubProgram program2 = new SubProgram() {

			@Override
			protected void process() {
				// no boolean update
			}
		};
		program.replace(program1, program2);
		Arrays.fill(executed, false);
		assertArrayEquals(new Boolean[] { false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { false }, executed);

		program.replace(program2, program1);
		Arrays.fill(executed, false);
		assertArrayEquals(new Boolean[] { false }, executed);
		program.execute();
		assertArrayEquals(new Boolean[] { true }, executed);
	}

	@Test
	public void testAccessFromClass() {
		// define internal programs
		class SubProgram1 extends DynamicProgram {
			@Override
			protected void process() {
				// nothing to do
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Collections.emptyList();
			}
		}
		class SubProgram2 extends DynamicProgram {
			@Override
			protected void process() {
				// nothing to do
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Collections.emptyList();
			}
		}

		// define root program
		final SubProgram1 program1 = new SubProgram1();
		final SubProgram2 program2 = new SubProgram2();
		class ProgramRoot extends DynamicProgram {
			private SubProgram1 p1 = program1;
			private SubProgram2 p2 = program2;

			@Override
			protected void process() {
				p1.process();
				p2.process();
			}

			@Override
			public Collection<? extends DynamicProgram> getUpdatableParts() {
				return Arrays.asList(p1, p2);
			}
		}
		ProgramRoot program = new ProgramRoot();

		// tests
		assertSame(program1,
				program.getUpdatablePartFromClass(SubProgram1.class));
		assertSame(program2,
				program.getUpdatablePartFromClass(SubProgram2.class));
	}
}
