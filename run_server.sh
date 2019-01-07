#!/bin/bash
cd bin
rmiregistry &
java com.vladwoguer.server.ServerOperation
