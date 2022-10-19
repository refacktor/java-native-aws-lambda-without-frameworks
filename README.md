# What

This is a sample AWS Lambda Function built using Java and pre-compiled to Native Image, without any frameworks or dependencies.

## What do you mean "No Frameworks", or "No Dependencies"?

It's just plain Java code with a main() method and no dependencies.

There is some tooling involved (Graal native-image, make, SAM) but these are just to help with compiling / packaging / deploying and do not pull any dependencies into the final executable.

## No Maven, No Gradle?

Nope. You don't need Maven or Gradle if you don't have any dependencies to manage. There is a Makefile to invoke the compilation steps.

## No Docker Images?

Nope.

# Why

I needed to create a really simple AWS Lambda Function and I wanted to use Java with AOT native compilation to minimize cold-start times. After a frustrating few minutes trying to figure out whether Dropwizard or Micronaut were the better lightweight alternative to Springboot, I decided to ditch the use of a framework altogether.

Modern, best-practices Java has so many layers of abstraction that it's really hard to know what's going on under the covers, so this also serves as a learning tool.

# How to run the demo code

- Install Graal: use the supplied `install_graal.sh` or equivalent manual steps
- Verify Graal installation: `javac`, `jar`, and `native-image` in the path?
- Install AWS SAM CLI and configure your local credentials
- `sam build` will compile everything
- `sam deploy` will ask a few questions and upload the executable image to AWS and create a Lambda Function
- Go to the AWS Console to execute the function manually

# Performance

Hello world executable is 5MB in size. Cold-start time is around 110ms.