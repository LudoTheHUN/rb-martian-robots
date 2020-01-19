# rb-martian-robots

A Clojure library designed to solve the rb martian robot problem.

## Notes for rb

* Basic entry point via a main function. KISS for now.
  * Would use https://github.com/clojure/tools.cli for a more sophisticated command line entry point.
* Keeping template License.
* I'm not making provisions for corrupt input (that does not conform to constraints provided). 
* I have probably spend a little more than 3 hours on this.
* Please note the tests.
* I adopted a pure functional style here, without using types or records (so as to explore the solution space quickly).
* RAM and integer numeric limits are the only limitations I can think of.
* This solution is not optimal in RAM utilisation. Here, RAM usage scales with robots and their instructions. Could be made to scale with only world (Mars) size.
* Some tests left as TODOs.

## Prerequisites

`lein` - https://leiningen.org/

`java`

## Usage

Single `lein` based run:

```
lein run <input-filename> <output-filename>
```

or build a java jar file with:

```
lein uberjar
```

and run jar artifact with:

```
java -jar target/rb-martian-robots-0.1.0-SNAPSHOT-standalone.jar <input-filename> <output-filename>
```


## TODOs 

* DONE Test with sample data
* DONE KISS entry point.
* DONE read a file with input data
* DONE parse file
* DONE business logic breakdown
  * DONE rotation
  * DONE Moving
  * DONE Falling off edge
  * DONE leave scent
* DONE problem representation
* DONE tests in representation space
* DONE processing flow
* DONE write output file
* DONE build uberjar
* DONE test against sample data with uberjar
* Break up single core file into namespaces

## License

Copyright © 2020 FIXME
