Zooplus Coding Exercise - Anagrams
==================================

Instructions
------------

An anagram is a word or sentence that can be rearranged to create another one, like 'Listen' can be turn into 'Silent', or 'Recitals' can turn into 'Articles'.

The purpose is to implement a command line application to match the anagram version of a given word or sentence.

To run the application it will be something like this:

```
java -cp <some_class_path> <class_name> <pathToDirectoryWithTextFiles>

scala -classpath <some_class_path> <class_name> <pathToDirectoryWithTextFiles>
```
Anyway is not needed to follow this structure to run the app, just let us know how to run it.

When this is run, the application should read one or more text files from the given directory that would work as dictionaries, map them somehow, and give a prompt to interact with it, so words or sentences can be used to search for anagrams.

Example (does not have to match your solution):
```
matchFor>
matchFor> Night
The anagram is... ‘Thing’
matchFor> Meal for one
The anagram is...’For me alone’
matchFor> We
The anagram is... not found here
```
You can implement a method using the following signature:

``` String searchAnagram(String word) ```

### Must have
* The application must run and work correctly.
* The application must have unit tests to prove that it works.
* Include a solution.md file with all the relevant information for the result, such as assumptions or how to build and run the application.
* Include a dictionary file or files. It can be a real dictionary or your own dictionary that suits your assumptions.
* In order to know how you work, we would like to have your commit history.

### Optional
* The application searches for the longest anagram that your dictionary contains. For example, 'Moon starer' is the longest anagram for 'Astronomer' as it has one more character. Example:
```
matchFor>
matchFor> Astronomer
The longest anagram is... ‘Moon starer’
matchFor> Statue of Liberty
The longest anagram is...’Built to stay free’
```

### Things to consider before starting
* How two words/sentences can be anagram of each other?
* Algorithm: how are you gonna search for anagrams?
* Data structure design and how to build the app
* How are you going to test it?
* Clean code is sexy
* Do not overcomplicate, less is more!

### How to proceed
* Use this private repository to develop your solution.
* Send an email to the recruitment manager when your solution is done.

### Important
* You can make your own assumptions to solve the problem. Please indicate them in the solution.md file.
* If you use external libraries (which is fine), please indicate the reason in your solution.md file.
* You can use Java, Kotlin, Scala or any JVM languange for your solution.
* If you have any doubts or something that you would like us to clarify, please feel free to contact us.
