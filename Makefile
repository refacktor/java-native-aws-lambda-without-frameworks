#
# Compile Java to native image.
# Requires javac, jar, native-image in $PATH.
#

build-HelloWorldFunction:
	@sh -c 'if test -z "$$ARTIFACTS_DIR"; then echo "USER ERROR: do not run make directly, use sam build instead"; exit 1; fi'
	mvn package
	native-image --enable-url-protocols=http -jar target/sample-app-1.jar target/bootstrap
	cp target/bootstrap $(ARTIFACTS_DIR)