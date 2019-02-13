# Rate Limit

## Requirement 


Imagine you have a bucket that gets filled with tokens at the rate of 1 token per second. 
The bucket can hold a maximum of N tokens. Implement a thread-safe class that lets threads get a token when one is available. 
If no token is available, then the token-requesting threads should block.

The class should expose an API called getToken that various threads can call to get a token


## Notes 

* 1 request per second is a slowest
* Synchronized keyword on a method
* wait() always must be called inside a loop [read more](https://stackoverflow.com/questions/1038007/why-should-wait-always-be-called-inside-a-loop)


## Code 

```java

public synchronized void getToken() throws InterruptedException {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / delay;

        if (possibleTokens > maxTokens) {
            possibleTokens = maxTokens;
        }

        if (possibleTokens == 0) {
            Thread.sleep(delay);
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (Instant.now()));

    }

```




[return to main page](../../../../../../README.md)
