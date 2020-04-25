package xyz.michalgawin.parser.version;

import org.junit.Assert;
import org.junit.Test;

public class TestVersion {

	@Test
	public void testVersionParts() {
		Version version = new Version("1.23.456-alpha");
		Assert.assertEquals(1, version.getMajor());
		Assert.assertEquals(23, version.getMinor());
		Assert.assertEquals(456, version.getPatch());
		Assert.assertEquals("alpha", version.getBuild());
	}

}
