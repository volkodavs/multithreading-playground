# Singleton

## Requirement 

* Create an object on request 
* Thread safe
* Small overhead on synchronization 


## Notes 

* Block synchronization
* Volatile keyword for happens-before semantics 
* Lazy initialization
* Double-Checked Locking

## Code snippet 

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
