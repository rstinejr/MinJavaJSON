# MinJavaJSON

**Minimal Javax JSON parser example**

This program demonstrates the operation of the javax.json pull-parser and the fasterxml.jackson.core.JsonParser.

It depends on javax.json-1.0.4.jar, which I downloaded from [JSON Processing](https://jsonp.java.net),
and the three fasterxml.jackson jar files, which are from a maven repository.

This project assumes that java and ant are installed.

Note that the sample data is a slight modification of the example on Wikipedia page [JSON]https://en.wikipedia.org/wiki/JSON)

## Streaming example

Running net.waltonstine.json.javax.piped.PipeParser demonstrates that the parser is in fact streaming; input is fed to the parser slowly, in small bits, over a pipe.  The parser spits out the parsed result as it processes its input.  Run this by itself by

```and run-piped```

## To build and execute

```ant clean run```
