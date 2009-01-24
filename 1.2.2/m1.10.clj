; Use block structure to hide the definition of fib-iter inside fib. Does
; lexical scoping allow you to remove any parameters from the embedded
; fib-iter?
;
;    (defn fib-iter [a b count]
;      (if (= count 0)
;          b
;          (fib-iter (+ a b) a (- count 1))))
;
;    (defn fib [n]
;      (fib-iter 1 0 n))

(defn fib [n]
  (defn fib-iter [a b count]
    (if (= count 0)
        b
        (fib-iter (+ a b) a (- count 1))))
  (fib-iter 1 0 n))

(= 3 (fib 4))
(= 55 (fib 10))
(= 610 (fib 15))

; Cannot remove any parameters from fib-iter, because none stay constant
; between calls
