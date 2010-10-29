; Using the results of exercises 1.16 and 1.17, devise a procedure that
; generates an iterative process for multiplying two integers in terms of
; adding, doubling, and halving and uses a logarithmic number of steps.

(defn doubled [i]
  (+ i i))
(defn halve [i]
  (/ i 2))

(defn mul [a b]
  (defn iter [i a b]
    (if (= b 0)
        i
        (if (even? b)
            (recur i (doubled a) (halve b))
            (recur (+ i a) a (- b 1)))))
  (iter 0 a b))

(assert (= 35  (mul 5 7)))
(assert (= 100 (mul 10 10)))
