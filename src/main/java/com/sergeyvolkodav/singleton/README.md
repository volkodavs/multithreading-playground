# Singleton

## Requirement 

* Create an object on request 
* Thread safe
* Small overhead on synchronization 


## Notes 

* [Block synchronization](http://www.java67.com/2013/01/difference-between-synchronized-block-vs-method-java-example.html)
* Volatile keyword for [happens-before](https://en.wikipedia.org/wiki/Java_memory_model) semantics
* Lazy initialization
* [Double-Checked Locking](https://en.wikipedia.org/wiki/Double-checked_locking)

## Code 

```java
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                    System.out.println("Singleton object created");
                }
            }
        }
        return singleton;
    }
``` 

[return to main page](../../../../../../README.md)
