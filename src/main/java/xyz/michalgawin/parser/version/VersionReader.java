package xyz.michalgawin.parser.version;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class VersionReader {

	public Version readVersion(String version) {
		VersionLexer lexer = new VersionLexer(CharStreams.fromString(version));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		VersionParser parser = new VersionParser(tokens);

		ParseTreeWalker walker = new ParseTreeWalker();
		VersionListenerImpl listener = new VersionListenerImpl();
		if (version.contains(",")) {
			walker.walk(listener, parser.versionrange());
		} else {
			walker.walk(listener, parser.version());
		}

		return listener.getVersion();
	}

	public static class VersionListenerImpl extends VersionBaseListener {

		private String lowerLimitVersion;
		private String upperLimitVersion;

		@Override
		public void enterVersion(VersionParser.VersionContext ctx) {
			if (lowerLimitVersion == null && ctx.VERSION() != null) {
				lowerLimitVersion = getVersionString(ctx.VERSION().getText());
			}
		}

		@Override public void exitVersion(VersionParser.VersionContext ctx) {
			if (upperLimitVersion == null && ctx.VERSION() != null) {
				upperLimitVersion = getVersionString(ctx.VERSION().getText());
			}
		}

		@Override public void enterLowerversionlimit(VersionParser.LowerversionlimitContext ctx) {
			if (lowerLimitVersion == null && ctx.VERSION() != null) {
				lowerLimitVersion = getVersionString(ctx.VERSION().getText());
			}
		}

		@Override public void enterUpperversionlimit(VersionParser.UpperversionlimitContext ctx) {
			if (upperLimitVersion == null && ctx.VERSION() != null) {
				upperLimitVersion = getVersionString(ctx.VERSION().getText());
			}
		}

		public Version getVersion() {
			return new Version(lowerLimitVersion, upperLimitVersion);
		}

		private String getVersionString(String version) {
			return version.replace("-+", "")
					.replace(".+", "")
					.replace("+", "");
		}
	}

}