(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

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

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) (* x 0.0000001)))

0.000000001
(sqrt (square 0.000000001))
1234567890987654
(sqrt (square 1234567890987654))
1234567890987654321
(sqrt (square 1234567890987654321))
