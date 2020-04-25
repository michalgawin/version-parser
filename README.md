# version-parser

Parser for version and version range where version consists of 
following parts {major}.{minor}.{patch}-{build} or their subset.

Examples:
- 1
- 1.23.+
- 1.23.456-alpha
- [1.23.456-alpha]
- [1.23.+,1.23.456-alpha]
- (,]

### Snippet
``` Java
Version version = new VersionReader().readVersion("(,1.23.456-alpha");
version.getUpperLimitVersion().getMajor();
```
