

# Lab 1 - Inversion of Control

In this lab you will develop an IoC framework. Your framework cannot have Spring libraries.

###  Requirements
---
* Create '@MyBean' annotation only for classes.
* Create '@MyAutowired' annotation only for fields.
* Create the Injector Class 'MyInjector' to search for annotations and create objects.
	* Scan all classes under the current package.
	* Create an instance for every class that has '@MyAutowired' annotation.
* Create a map to hold the instances of classes.
* Create a 'getBean(Class clazz)' method that returns the object of the class from the Map. Throw a BeanNotFoundException if there is no object of the class in the Map. (You need to create the exception.)
	

## Submission
---
* Fork the repository and push your changes.
* Once you finished your project, send a Pull Request. (Send only one Pull Request once you finish the assignment.)

### Important Notes
---

 * You are not allowed to share codes with your classmates. If detected, you will get NC.
 * **For pairs:**
	 * Individual's work will be checked from the commits.
	 *  Share tasks evenly and fairly.
	 *  To have a clearer understanding of pair programming:
		 *  > **Pair programming** is an agile software development technique in which two programmers work together at one workstation. One, the _driver_, writes code while the other, the _observer_ or _navigator_ reviews each line of code as it is typed in. The two programmers switch roles frequently. 
		 * [Wikipedia](https://en.wikipedia.org/wiki/Pair_programming#:~:text=Pair%20programming%20is%20an%20agile,two%20programmers%20switch%20roles%20frequently.)

-   Remember to respect the code honor submission policy. All written code must be original. Presenting something as one’s own work when it came from another source is plagiarism and is forbidden.
    
-   Plagiarism is a very serious thing in all American academic institutions and is guarded against vigilantly by every professor.

