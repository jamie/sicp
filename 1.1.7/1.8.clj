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

(defn cube [x]
  (* x x x))

(defn improve [guess x]
  (/ (+ (/ x (* guess guess))
        (* guess 2))
     3))

(defn good-enough? [guess x]
  (< (abs (- (cube guess) x)) (* x 0.0000001)))

(defn curt-iter [guess x]
  (if (good-enough? guess x)
      guess
      (recur (improve guess x)
             x)))

(defn curt [x]
  (curt-iter 1.0 x))

3.0
(curt (cube 3.0))
123.005
(curt (cube 123.005))
0.0098
(curt (cube 0.0098))
