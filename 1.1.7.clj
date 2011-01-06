;;; 1.7

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

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn close?
  ([x y] (close? x y 0.001))
  ([x y tolerance]
    (< (abs (- x y)) tolerance)))

(defn good-enough? [guess x]
  (close? (square guess) x))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
      guess
      (recur (improve guess x)
             x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(defn test_sqrt [x]
  (assert (close? x (sqrt (square x)))))

(test_sqrt 9)
(test_sqrt (+ 100 37))
(test_sqrt (+ (sqrt 2) (sqrt 3)))
(test_sqrt 1000)

; In very small numbers, the delta used in good-enough? can be many times
; the result we want, leading to inaccurate answers.

(assert (close? 0.03125 (sqrt (square 0.000000001))))
; Initial value far too small, gives up too early.

; In very large numbers, (for example, adding a digit to the below) the
; floating-point representation is incapable of becoming any more accurate
; with each iteration (average x y returning x) and so we get into an
; infinite loop without making any progress
(assert (= 1234567890987654 (sqrt (square 1234567890987654))))

; Basing our good-enough? on a proportion of the input does result
; in better answers for both large and small values.
(defn good-enough? [guess x]
  (close? (square guess) x (* x 0.000000001)))

(test_sqrt 0.000000001)

; There is a trade-off though. With the scaled good-enough? we are less
; accurate on this first example due to float conversion (we were exact
; previously) but at least we do terminate on the second test.
(assert (close? 1234567890987654 (sqrt (square 1234567890987654)) 1000000))
(assert (close? 1234567890987654321 (sqrt (square 1234567890987654321)) 1000000))


;;; 1.8

; Newton's method for cube roots is based on the fact that if y is an
; approximation to the cube root of x, then a better approximation is
; given by the value 
;
;  x / (y^2) + 2y
;  --------------
;       3
;
; Use this formula to implement a cube-root procedure analogous to the
; square-root procedure. (In section 1.3.4 we will see how to implement
; Newton's method in general as an abstraction of these square-root and
; cube-root procedures.) 

; Differences from sqrt are:
; swap square for cube
; change implementation of improve
; rename sqrt, sqrt-iter to curt, curt-iter

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(defn cube [x]
  (* x x x))

(defn improve [guess x]
  (/ (+ (/ x (* guess guess))
        (* guess 2))
     3))

(defn close?
  ([x y] (close? x y 0.0000001))
  ([x y tolerance]
    (< (abs (- x y)) tolerance)))

(defn good-enough? [guess x]
  (close? (cube guess) x (* x 0.0000001)))

(defn curt-iter [guess x]
  (if (good-enough? guess x)
      guess
      (recur (improve guess x)
             x)))

(defn curt [x]
  (curt-iter 1.0 x))

(defn test-curt [x]
  (assert (close? x (curt (cube x)))))

(test-curt 3.0)
(test-curt 123.005)
(test-curt 0.0098)


;;; IM 1.6

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

(assert (= true (real-root? 1 0 0)))
(assert (= true (real-root? 1 0 -1)))
(assert (= false (real-root? 1 0 1)))

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


;;; IM 1.7

; Write a procedure power-close-to that takes as arguments two positive
; integers b and n, and returns the smallest power of b that is greater
; than n. That is, it should return the smallest integer e such that 
; b^e > n. You may use the Scheme procedure expt, which raises a given
; number to a given power.
;
;   (power-close-to 2 1000)
;   10
;
;   (** 2 10)
;   1024

(defn expt [a b]
  (int (Math/pow a b)))

(defn power-iter [b n e]
  (if (< n (expt b e))
      e
      (power-iter b n (+ e 1))))

(defn power-close-to [b n]
  (power-iter b n 1))

(assert (= 10 (power-close-to 2 1000)))

