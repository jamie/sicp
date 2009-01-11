# SICP Exercises

Broken down by chapter, plain text responses in .txt files, scheme source
in .ss files (tested with [PLT Scheme][]), clojure source in .clj files
(tested with [Clojure][] v20081217).

[PLT Scheme]: http://www.plt-scheme.org/
[Clojure]: http://clojure.org/

# Support Files

I've included clojure.jar, v20081217, and a simple wrapper bash script
that will open up the Clojure REPL.

# Notes

There are some special form differences between the scheme presented in
SICP and Clojure.  I'm documenting them here as I go along.

    Scheme                          Clojure
    
    (define a 3)                    (def a 3)
    
    (cond ((= a 4) 6)               (cond (= a 4) 6
          ((= b 4) (+ 6 7 a))             (= b 4) (+ 6 7 a)
          (else 25))                      :else 25)

