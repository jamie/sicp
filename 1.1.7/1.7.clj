; The good-enough? test used in computing square roots will not be very
; effective for finding the square roots of very small numbers. Also, in
; real computers, arithmetic operations are almost always performed with
; limited precision. This makes our test inadequate for very large numbers.
; Explain these statements, with examples showing how the test fails for
; small and large numbers. An alternative strategy for implementing
; good-enough? is to watch how guess changes from one iteration to the
; next and to stop when the change is a very small fraction of the guess.
; Design a square-root procedure that uses this kind of end test. Does this
; work better for small and large numbers?

; Clojure builtin
;(defn abs [x]
;  (cond (< x 0) (- x)
;        :else x))

; Clojure builtin
;(defn square [x]
;  (* x x))

; Clojure builtin
;(defn average [x y]
;  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
      guess
      (recur (improve guess x)
             x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9)
(sqrt (+ 100 37))
(sqrt (+ (sqrt 2) (sqrt 3)))
(square (sqrt 1000))

; In very small numbers, the delta used in good-enough? can be many times
; the result we want, leading to inaccurate answers.

(sqrt (square 0.000000001))
; Gives us 0.03125000000000001, because it gives up too early

; In very large numbers, (for example, adding a digit to the below) the
; floating-point representation is incapable of becoming any more accurate
; with each iteration (average x y returning x) and so we get into an
; infinite loop without making any progress
(sqrt (square 1234567890987654))

; Basing our good-enough? on a proportion of the input does result
; in better answers for both large and small values
(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) (* x 0.0000001)))

0.000000001
(sqrt (square 0.000000001))
1234567890987654
(sqrt (square 1234567890987654))
1234567890987654321
(sqrt (square 1234567890987654321))
