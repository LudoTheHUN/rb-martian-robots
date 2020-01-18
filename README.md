# rb-martian-robots

A Clojure library designed to solve the rb martian robot problem.

## Notes for rb

* Basic entry point via a main function. KISS for now.
  * Would use https://github.com/clojure/tools.cli for a more sophisticated command line entry point.
* Keeping template License.
* I'm not making provisions for corrupt input (that does not conform to constraints provided).

## Prerequisites

`lein` - https://leiningen.org/

`java`

## Usage

Single `lain` run:

```
lein run <input-filename> <output-filename>
```

or build

```
lein uberjar
```

and run jar artifact:

```
java -jar target/rb-martian-robots-0.1.0-SNAPSHOT-standalone.jar <input-filename> <output-filename>

```

## TODOs

* Test with sample data
* KISS entry point.
* read a file with input data
* parse file
* business logic breakdown
* problem representation
* tests in representation space
* encode rules
* processing flow
* write output file
* build uberjar
* test against sample data with uberjar.

[comment]: <>  (
Setting up ssh for github
`export GIT_SSH=/Users/ludwik/code/clojure/rb-martian-robots/gitssh.sh`
)



## License

Copyright © 2020 FIXME
