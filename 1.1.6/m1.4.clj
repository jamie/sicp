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
