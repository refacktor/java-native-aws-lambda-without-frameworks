# What

This is a sample AWS Lambda Function built using Java and AOT (Ahead Of Time compilation to binary executable), without any frameworks or dependencies.

## What do you mean "No Frameworks", or "No Dependencies"?

The Lambda Function is implemented in plain Java code with a `main()` method and no dependencies.

There is some tooling involved (Graal native-image, make, SAM, Maven, JUnit) but these are just to help with compiling / testing / packaging / deploying and do not pull any dependencies into the final executable.

# "No Dependencies" huh? What's this Commons Codec dependency in the pom.xml?

This is just an arbitrary dependency thrown in to demonstrate how to use dependencies, when you want them. It's only used in the sample code in `Handler::handle()` and easily removed.

Please note that adding Jackson Databind (tried on 2.13.4) as a dependency breaks the deployment. Probably just one of many popular Java libraries that don't work with native image compilation.

# Why

I needed to create a really simple AWS Lambda Function and I wanted to use a strongly-typed language with AOT native compilation to minimize cold-start times. I looked into Golang and Rust but after trying out the samples, decided to stick with Java. After a frustrating few minutes trying to figure out whether Dropwizard or Micronaut were the better lightweight alternative to Springboot, I decided to ditch the use of a framework altogether.

Modern, best-practices Java has so many layers of abstraction that it's really hard to know what's going on under the covers, so this also serves as a learning tool.

# How to run the demo code

NOTE: On Windows OS, use a WSL shell for this.

- Install Graal: use the supplied `install_graal.sh` or equivalent manual steps
- Verify Graal installation: `javac`, `jar`, and `native-image` in the path?
- Install AWS SAM CLI and configure your local credentials
- `mvn test` to compile the Java to bytecode and run the tests, but stop short of the (slower) native compilation
- `sam build` will compile everything all the way to the native stage
- `sam deploy` will ask a few questions and upload the executable image to AWS and create a Lambda Function
- Go to the AWS Console to execute the function manually

# Performance

Cold-start time is around 110ms. Hello world executable is 17MB in size, compressed to 5MB.

# Conclusions

This architecture delivers a more level playing field when comparing Java to the likes of Go and Rust for serverless deployment.

Traditional Java with JIT and Springboot are a poor fit for serverless as the runtime initialization cycles become wasteful. Springboot and other "platform"-like Java frameworks are hyper-optimized for the long-lived JVMs on persistent servers.

Going AOT and without any mandatory framework dependencies enables more deliberate control over the serverless init cycle i.e. only pay for what you need.

# TL;DR

Looks very promising, but failure to get Jackson 2.13.4 to work is kind of a show-stopper to using this architecture on any real-world project.
