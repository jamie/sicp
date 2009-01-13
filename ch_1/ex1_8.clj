; Differences from sqrt are:
; swap square for cube
; change impl of improve
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
