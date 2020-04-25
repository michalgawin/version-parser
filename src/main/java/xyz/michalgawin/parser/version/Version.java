package xyz.michalgawin.parser.version;

public class Version {

	private String lowerLimitVersion;
	private String upperLimitVersion;

	public Version(String version) {
		this(version, version);
	}

	public Version(String lowerLimitVersion, String upperLimitVersion) {
		this.lowerLimitVersion = lowerLimitVersion;
		this.upperLimitVersion = upperLimitVersion;
	}

	public Version getLowerLimitVersion() {
		if (lowerLimitVersion == null) {
			return null;
		}
		return new Version(lowerLimitVersion);
	}

	public Version getUpperLimitVersion() {
		if (upperLimitVersion == null) {
			return null;
		}
		return new Version(upperLimitVersion);
	}

	public int getMajor() {
		String part = getPart(upperLimitVersion, 0);
		if (part != null) {
			return Integer.valueOf(part);
		}
		return -1;
	}

	public int getMinor() {
		String part = getPart(upperLimitVersion, 1);
		if (part != null) {
			return Integer.valueOf(part);
		}
		return -1;
	}

	public int getPatch() {
		String part = getPart(upperLimitVersion, 2);
		if (part != null) {
			return Integer.valueOf(part);
		}
		return -1;
	}

	public String getBuild() {
		return getPart(upperLimitVersion, 3);
	}

	public String getPart(String version, int part) {
		if (version == null) {
			return null;
		}

		String[] parts = version.split("[\\.-]");
		if (parts.length > part) {
			return parts[part];
		}

		return null;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!(object instanceof Version)) {
			return false;
		}
		Version version = (Version) object;
		return this == object ||
				(lowerLimitVersion.equals(version.lowerLimitVersion) &&
						upperLimitVersion.equals(version.upperLimitVersion));
	}

	@Override
	public String toString() {
		if (lowerLimitVersion.equals(upperLimitVersion)) {
			return upperLimitVersion;
		}
		return String.format("%s,%s", lowerLimitVersion, upperLimitVersion);
	}

}