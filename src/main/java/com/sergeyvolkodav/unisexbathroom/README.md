# Unisex Bathroom Problem

## Requirement 

A bathroom is being designed for the use of both males and females in an office but requires the following constraints to be maintained:

* There cannot be men and women in the bathroom at the same time.
* There should never be more than three employees in the bathroom simultaneously.

## Notes 
* No deadlock 
* Potential [starvation](https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html)

## Code 

```java

  public void maleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (isGender == Gender.FEMALE) {
                wait();
            }
            maxEmps.acquire();
            isGender = Gender.MEN;
            emps++;
        }

        useBathroom(Gender.MEN);
        maxEmps.release();

        synchronized (this) {
            emps--;
            if (emps == 0) {
                isGender = Gender.NONE;
            }
            notifyAll();
        }
    }

    public void femaleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {

            while (isGender == Gender.MEN) {
                wait();
            }
            maxEmps.acquire();
            isGender = Gender.FEMALE;
            emps++;
        }

        useBathroom(Gender.FEMALE);
        maxEmps.release();

        synchronized (this) {
            emps--;
            if (emps == 0) {
                isGender = Gender.NONE;
            }
            notifyAll();
        }
    }

```


[return to main page](../../../../../../README.md)
