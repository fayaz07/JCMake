#!/bin/bash

echo "Setting up properties file"

PROPERTIES_FILE="local.properties"

touch $PROPERTIES_FILE

echo "gpr.usr=gh-user" >> $PROPERTIES_FILE
echo "gpr.key=token" >> $PROPERTIES_FILE

echo "Properties file created at $PROPERTIES_FILE"
