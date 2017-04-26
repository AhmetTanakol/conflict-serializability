# conflict-serializability


* Code was written in Java on Eclipse
* JavaSE 1.7 is required
* git clone git@github.com:AhmetTanakol/conflict-serializability.git
* If you would like to run on command line, please follow the instructions
##### Instructions
* Go to directory `path_to_project/conflict-serializability/src`
* Type `javac serializeFinder.java`
* Type `java serializeFinder`
* Enter history in this format `w 1 x r 2 x w 2 y r 3 y w 3 z r 1 z`
* Press `Enter`
* If it is serializable, it prints out "true" otherwise "false"
