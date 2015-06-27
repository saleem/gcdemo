# gcdemo

A simple demonstrator of Java's garbage collection mechanism.

## Garbage Collection

Here's are a few articles on how garbage collection works in Java:

1. Tutorial at Oracle: http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html

2. A simple overview at Dynatrace: http://www.dynatrace.com/en/javabook/how-garbage-collection-works.html

## Purpose of this demonstrator

The purpose of this demonstrator is to show how Java handles garbage collection of objects. Specifically, I want to explore and show how objects with strong (normal) and ``WeakReference``s are garbage collected.

The unit tests use explicit calls to ``System.gc()`` to illustrate various points. 
**Explicit calls to ``System.gc()`` are not recommended in production-ready code**. 
They are (a) inefficient and (b) not even guaranteed to cause a garbage collection. 
Because of the latter reason, I put the current thread to sleep in the tests to increase the probability that the gc will indeed run. 
"This works on my machine", that is, the tests pass and produce expected behavior. 
Please let me know if this is not the case for you; 
I'm interested in seeing if the gc is optimized differntly on different hardware/software architectures. 

This is simply a demonstrator -- please use it to learn about how gc works and not as a hint on how to write production-ready code. Thank you!

## How to run this demo

1. Clone it: ``git clone https://github.com/saleem/gcdemo.git``
2. Run the tests: ``./gradlew test``
3. Read the code, especially the ``GarbageCollectionTest`` class.
