;;; 1.3

; Define a procedure that takes three numbers as arguments and returns the
; sum of the squares of the two larger numbers.

(defn sum-largest-squares [a b c]
  (cond (and (< a b) (< a c)) (+ (* b b) (* c c))
        (and (< b a) (< b c)) (+ (* a a) (* c c))
        (and (< c a) (< c b)) (+ (* a a) (* b b))))

(assert (= 13 (sum-largest-squares 1 2 3)))
(assert (= 13 (sum-largest-squares 1 3 2)))
(assert (= 13 (sum-largest-squares 2 1 3)))
(assert (= 13 (sum-largest-squares 2 3 1)))
(assert (= 13 (sum-largest-squares 3 1 2)))
(assert (= 13 (sum-largest-squares 3 2 1)))


;;; IM 1.3

; Write a Scheme expression whose evaluation would result in an error
; if `and` were a procedure but actually will have a value because and
; is a special form. Do the same for `or`.

(defn and- [a b] (and a b))
(defn or- [a b] (or a b))

(defn loopy [] (recur))

(assert (= false (and false (loopy))))
(assert (= (or true (loopy))))

; Because these are special forms, the call to loopy is never evaluated.
; If `and` or `or` were regular procedures, all arguments would need to
; be evaluated before the procedure is called, and loopy will never
; terminate.


;;; IM 1.4

; Define a procedure sign that takes a number as its argument and 
; returns 1 if the number is positive, -1 if the number is negative,
; and 0 if the number is 0.

(defn sign [val]
  (cond (> val 0) 1
        (< val 0) -1
        :else 0))

(assert (= 1 (sign 10)))
(assert (= 1 (sign 1)))
(assert (= 0 (sign 0)))
(assert (= -1 (sign -1)))
(assert (= -1 (sign -10)))


;;; IM 1.5

; Define a procedure called true-false that takes one argument and 
; returns 1 if the argument is true and 0 if it is false.  For example,
;
;   (true-false (> 3 2))
;   1
;
;   (true-false (and (> 3 2) (< 3 1)))
;   0
;
; Write two definitions of true-false - one that uses if and one that
; uses cond.

(defn true-false [val]
  (if val 1 0))

(assert (= 1 (true-false (> 3 2))))
(assert (= 0 (true-false (and (> 3 2) (< 3 1)))))

(defn true-false2 [val]
  (cond val 1
	:else 0))

(assert (= 1 (true-false2 (> 3 2))))
(assert (= 0 (true-false2 (and (> 3 2) (< 3 1)))))

