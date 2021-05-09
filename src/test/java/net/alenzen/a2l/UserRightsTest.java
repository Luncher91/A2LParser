package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class UserRightsTest {
	private Asap2File file;
	private UserRights userRights;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		userRights = file.getProject().getModules().get(0).getUserRights().get(0);

		assertNotNull(userRights);
	}

	@Test
	void testUserLevelId() {
		assertEquals("admins", userRights.getUserLevelId());
	}

	@Test
	void testReadOnly() {
		assertEquals(true, userRights.isReadOnly());
	}

	@Test
	void testGroupReferences() {
		String[] groups = new String[] { "g1", "g2", "g3" };
		List<String> gr = userRights.getGroups().get(0);

		assertEquals(groups.length, gr.size());

		for (int i = 0; i < gr.size(); i++) {
			assertEquals(groups[i], gr.get(i));
		}
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(UserRights.class).verify();
	}
}
