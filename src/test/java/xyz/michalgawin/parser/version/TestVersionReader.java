package xyz.michalgawin.parser.version;

import org.junit.Assert;
import org.junit.Test;

public class TestVersionReader {

	private static VersionReader versionReader = new VersionReader();

	@Test
	public void testVersion() {
		Assert.assertEquals(new Version("1"), versionReader.readVersion("1"));

		Assert.assertEquals(new Version("1.23"), versionReader.readVersion("1.23"));

		Assert.assertEquals(new Version("1.23.456"), versionReader.readVersion("1.23.456"));

		Assert.assertEquals(new Version("1.23.456-alpha"), versionReader.readVersion("1.23.456-alpha"));

		Assert.assertEquals(new Version("1.23.456"), versionReader.readVersion("[1.23.456-+]"));

		Assert.assertEquals(new Version("1.23"), versionReader.readVersion("(1.23.+)"));
	}

	@Test
	public void testVersionRange() {
		Version version = new VersionReader().readVersion("(,]");
		Assert.assertNull(version.getLowerLimitVersion());
		Assert.assertNull(version.getUpperLimitVersion());

		version = new VersionReader().readVersion("(1,]");
		Assert.assertEquals("1", version.getLowerLimitVersion().toString());
		Assert.assertNull(version.getUpperLimitVersion());

		version = new VersionReader().readVersion("(,1]");
		Assert.assertNull(version.getLowerLimitVersion());
		Assert.assertEquals("1", version.getUpperLimitVersion().toString());

		Assert.assertEquals(new Version("1", "1"), versionReader.readVersion("(1,1]"));

		Assert.assertEquals(new Version("1.23", "1.23"), versionReader.readVersion("1.23,1.23"));

		Assert.assertEquals(new Version("1.23.456", "1.23.456"), versionReader.readVersion("1.23.456,1.23.456"));

		Assert.assertEquals(new Version("1.23.456", "1.23.456-alpha"), versionReader.readVersion("1.23.456,1.23.456-alpha"));

		Assert.assertEquals(new Version("1.23.456-alpha", "1.23.456-beta"), versionReader.readVersion("1.23.456-alpha,1.23.456-beta"));

		Assert.assertEquals(new Version("1.23.456-123", "1.23.456-123"), versionReader.readVersion("[1.23.456-123,1.23.456-123]"));

		Assert.assertEquals(new Version("1.23.456-0", "1.23.456"), versionReader.readVersion("[1.23.456-0,1.23.456)"));

		Assert.assertEquals(new Version("1.23.456", "1.23.456"), versionReader.readVersion("(1.23.456,1.23.456-+]"));
	}
}
