#
# Compile Java to native image.
# Requires javac, jar, native-image in $PATH.
# Do not run directly - use "sam build" to populate ARTIFACTS_DIR
#

build-HelloWorldFunction:
	./gradlew build
	native-image --enable-url-protocols=http -jar app/build/libs/app.jar app/build/distributions/bootstrap
	cp app/build/distributions/bootstrap $(ARTIFACTS_DIR)