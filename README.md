# udemy-complete-java-dev

**Note:** This repository is archived and read-only.

Challenges and exercises from Tim Buchalka's Java course on Udemy. Each source file is a standalone exercise, grouped by course topic under `src/main/java/`.

## Project structure

- **`basics/`** — `firststeps`, `expressions` and `controlflow` (loops, enhanced `switch`, number challenges such as `Palindrome`, `LargestPrime`, `NumberToWords`)
- **`oop/`** — `abstraction`, `composition`, `encapsulation`, `inheritance`, `polymorphism`
- **`nestedclasses/`**, **`generics/`**, **`datastructures/`** — inner/local classes, generics, arrays, `ArrayList`, `LinkedList`
- **`lambdas/`**, **`concurrency/`** — lambdas and method references; threads, synchronization, executors
- **`util/`** — small helpers (`Console`, `OS`)

## Getting started

Requires JDK 22 or newer (the Maven wrapper is included). Only classes with a `main` method are runnable.

```bash
./mvnw compile
java -cp target/classes basics.controlflow.Palindrome
```

## License

GNU General Public License v3.0 (see `LICENSE`).
