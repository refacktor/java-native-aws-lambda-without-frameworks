#
# Compile Java to native image.
# Requires javac, jar, native-image in $PATH.
# Do not run directly - use "sam build" to populate ARTIFACTS_DIR
#

build-HelloWorldFunction:
	mkdir -p bin/classes
	javac src/**.java -d bin/classes
	jar -cvf bin/classes.jar -C bin/classes .
	native-image --enable-url-protocols=http -cp bin/classes.jar Handler bin/bootstrap
	cp bin/bootstrap $(ARTIFACTS_DIR)