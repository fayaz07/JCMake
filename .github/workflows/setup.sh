#!/bin/bash

echo "Setting up properties file"

PROPERTIES_FILE="local.properties"

touch $PROPERTIES_FILE

echo "gpr.usr=$1" >> $PROPERTIES_FILE
echo "gpr.key=$2" >> $PROPERTIES_FILE

echo "Properties file created at $PROPERTIES_FILE"
