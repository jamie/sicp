#!/usr/bin/env fish

for file in *.clj */*.clj
  echo $file
  clj $file
end
