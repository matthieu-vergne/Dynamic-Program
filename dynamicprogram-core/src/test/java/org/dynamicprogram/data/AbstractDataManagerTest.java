package org.dynamicprogram.data;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class AbstractDataManagerTest {

	@Test
	public void testUpdate() {
		final String s0 = "0";
		final String s1 = "1";
		final String s2 = "2";
		AbstractDataManager<String, Integer> manager = new AbstractDataManager<String, Integer>() {
			private final String[] data = new String[] { s0, s1, s2 };

			@Override
			public Collection<Integer> getDataIDs() {
				return Arrays.asList(0, 1, 2);
			}

			@Override
			public String getData(Integer id) {
				return data[id];
			}

			@Override
			protected void internalSetPart(Integer id, String string) {
				data[id] = string;
			}

		};

		assertEquals(s0, manager.getData(0));
		assertEquals(s1, manager.getData(1));
		assertEquals(s2, manager.getData(2));

		manager.setData(0, s2);
		manager.setData(1, s0);
		manager.setData(2, s2);
		assertEquals(s2, manager.getData(0));
		assertEquals(s0, manager.getData(1));
		assertEquals(s2, manager.getData(2));
	}

	@Test
	public void testListeners() {
		final String s0 = "0";
		final String s1 = "1";
		final String s2 = "2";
		final Boolean executed[] = new Boolean[] { false, false };
		AbstractDataManager<String, Integer> manager = new AbstractDataManager<String, Integer>() {
			private final String[] data = new String[] { s0, s1, s2 };

			@Override
			public Collection<Integer> getDataIDs() {
				return Arrays.asList(0, 1, 2);
			}

			@Override
			public String getData(Integer id) {
				return data[id];
			}

			@Override
			protected void internalSetPart(Integer id, String string) {
				data[id] = string;
				assertArrayEquals(new Boolean[] { false, false }, executed);
				executed[0] = true;
			}

		};
		final Object[] updated = new Object[3];
		manager.addUpdateListener(new UpdateListener<String, Integer>() {

			@Override
			public void dataUpdated(Integer id, String oldPart, String newPart) {
				assertArrayEquals(updated,
						new Object[] { id, oldPart, newPart });
				assertArrayEquals(new Boolean[] { true, false }, executed);
				executed[1] = true;
			}
		});

		Arrays.fill(executed, false);
		updated[0] = 0;
		updated[1] = s0;
		updated[2] = "123";
		assertArrayEquals(new Boolean[] { false, false }, executed);
		manager.setData((Integer) updated[0], (String) updated[2]);
		assertArrayEquals(new Boolean[] { true, true }, executed);

		Arrays.fill(executed, false);
		updated[0] = 1;
		updated[1] = s1;
		updated[2] = "test";
		assertArrayEquals(new Boolean[] { false, false }, executed);
		manager.setData((Integer) updated[0], (String) updated[2]);
		assertArrayEquals(new Boolean[] { true, true }, executed);
	}
}
