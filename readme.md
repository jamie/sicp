# SICP Exercises

The full text of the book is available [online][]. I'm also working
through problems presented in the [instructor's manual][], whose
problems are prefixed with an M.

[online]: http://mitpress.mit.edu/sicp/full-text/book/book.html
[instructor's manual]: http://mitpress.mit.edu/catalog/item/default.asp?ttype=2&tid=3849

I'm planning on working through solutions in a number of lisp dialects
(and perhaps other languages as well). Solutions are grouped by
section and type (based on the file extension).

.txt are plain text solutions to essay questions.

.ss are Scheme files, run through [PLT Scheme][].

.clj are [Clojure][] solutions, using Clojure 1.2. Assertions are
included, and will raise an error if the assertion does not pass.

[PLT Scheme]: http://www.plt-scheme.org/
[Clojure]: http://clojure.org/

# Clojure Notes

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
    
                                          ; A number of mathy builtins
                                          ; provided by Java's Math class.
                                          ; To call them, use Class/method
                                          (assert (= 8 (Math/pow 2 3)))

