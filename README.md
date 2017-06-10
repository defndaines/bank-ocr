# Bank OCR

A Clojure implementation of the [Bank OCR](http://codingdojo.org/kata/BankOCR/)
kata.


## Usage

From the command line, `bank-ocr.jar` will outputs parsed account numbers to
Standard Out. (Because the application expects a single argument, there are
limited warnings when invoked incorrectly.)

```shell
$ java -jar bank-ocr.jar resources/use-case-1.dat

000000000
111111111
222222222
333333333
444444444
555555555
666666666
777777777
888888888
999999999
123456789
```


## Development

A [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) is
required to run and develop this application. To build yourself, you will need
to install [lein](https://leiningen.org/).

### Build an Uberjar

An "uberjar" is a single JAR file containing all required libraries which can be
invoked relatively easily from the command line.

To build:
```shell
$ lein clean
$ lein uberjar
```

This will generate the runnable JAR file at `target/bank-ocr.jar`.

### Running Bank ORC from the Command Line

Once you have the `bank-ocr.jar`, you can run it from the command line using
the following.

```shell
$ java -jar bank-ocr.jar path/to/file.txt > results.txt
```

The application outputs parsed account numbers to Standard Out. In the example
above, they are redirected to the `results.txt` file.


## License

Copyright © 2017 Michael S. Daines

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
