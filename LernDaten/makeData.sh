#!/bin/bash
FILES=$(readlink -f $1)
FILES="$FILES/*.csv"
for f in $FILES
do
  echo "Processing $f file..."
  java -jar Mustererkennung.jar $f
  #echo "\$0 = $1"
  
done
