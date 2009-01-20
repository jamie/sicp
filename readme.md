# SICP Exercises

Root directories are the section in the text, filenames are exercise
number. Plain text responses in .txt files, scheme source in .ss 
files (tested with [PLT Scheme][]), clojure source in .clj files
(tested with [Clojure][] v20081217).

Also included are exerciese from the Instructor's Manual, prefixed
with an 'M'.

[PLT Scheme]: http://www.plt-scheme.org/
[Clojure]: http://clojure.org/

# Support Files

I've included clojure.jar, v20081217, and a simple wrapper bash script
that will open up the Clojure REPL.

# Notes

There are some special form differences between the scheme presented in
SICP and Clojure.  I'm documenting them here as I go along.

    Scheme                                Clojure
                                          
    (define a 3)                          (def a 3)
                                          
    (cond ((= a 4) 6)                     (cond (= a 4) 6
          ((= b 4) (+ 6 7 a))                   (= b 4) (+ 6 7 a)
          (else 25))                            :else 25)
                                          
    (define (a-plus-abs-b a b)            (defn a-plus-abs-b [a b]
      ((if (> b 0) + -) a b))               ((if (> b 0) + -) a b))
                                          
                                          ; The JVM doesn't automatically support
                                          ; tail-call optimization, so Clojure
                                          ; provides a keyword to hint it
    (defn sqrt-iter [guess x]             (defn sqrt-iter [guess x]
      (if (good-enough? guess x)            (if (good-enough? guess x)
          guess                                 guess
          (sqrt-iter (improve guess x)          (recur (improve guess x)
                     x)))                              x)))             
    
    



