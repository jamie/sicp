; Define a procedure that takes three numbers as arguments and returns the
; sum of the squares of the two larger numbers.

(defn sum-largest-squares [a b c]
  (cond (and (< a b) (< a c)) (+ (* b b) (* c c))
        (and (< b a) (< b c)) (+ (* a a) (* c c))
        (and (< c a) (< c b)) (+ (* a a) (* b b))))

(sum-largest-squares 1 2 3)
(sum-largest-squares 2 3 1)
(sum-largest-squares 3 1 2)
