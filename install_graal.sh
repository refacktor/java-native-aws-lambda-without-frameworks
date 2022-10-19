#!/bin/bash
#
# quick-n-dirty script to install GraalVM with native-image compiler
#

curl https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.2.0/graalvm-ce-java17-linux-amd64-22.2.0.tar.gz

tar -zxvf graalvm-ce-java17-linux-amd64-22.2.0.tar.gz

sudo graalvm-ce-java17-22.2.0/bin/gu install native-image

sudo ln -sf $PWD/graalvm-ce-java17-22.2.0/bin/* /usr/local/bin/

