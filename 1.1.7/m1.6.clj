; The zeroes of the polynomial ax^2 + bx + c are given by -b plus or
; minus sqrt(b^2 - 4ac) times 1/2a. If the discriminant b^2 - 4ac is
; greater than or equal to 0, the roots are both real; otherwise the
; roots are complex with real part -b/2a. Define a procedure 
; smallest-real-part that takes the coefficients a, b and c as arguments
; and returns either the real part of the roots (if they are complex),
; or else the real root having the smaller absolute value. Be sure
; that your procedure continues to work if some of the coefficients 
; or the discriminant are zero (but you may assume that a is always
; nonzero).

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
  (or (= guess x)
      (< (abs (- (square guess) x)) (* x 0.0000001))))
(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
      guess
      (recur (improve guess x)
             x)))
(defn sqrt [x]
  (sqrt-iter 1.0 x))


(defn discriminant [a b c]
  (- (* b b) (* 4 a c)))

(defn real-root? [a b c]
  (<= 0 (discriminant a b c)))

(= true (real-root? 1 0 0))
(= true (real-root? 1 0 -1))
(= false (real-root? 1 0 1))

(defn real-part [a b c]
  (/ (- b) (* 2 a)))

(defn root [sign a b c]
  ; -b +- sqrt(bb - 4ac)
  ; --------------------
  ;         2a
  (/ (sign (- b) 
           (sqrt (discriminant a b c)))
     (* 2 a)))

(defn root-one [a b c]
  (root + a b c))
(defn root-two [a b c]
  (root - a b c))

(defn smallest-real-part [a b c]
  (if (real-root? a b c)
      (min (abs (root-one a b c)) (abs (root-two a b c)))
      (real-part a b c)))

; TODO: some concrete examples with known answers to test with?
(smallest-real-part 1 0 0)
(smallest-real-part 1 0 -1)
(smallest-real-part 1 3 1)
