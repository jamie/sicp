; The exponentiation algorithms in this section are based on performing
; exponentiation by means of repeated multiplication. In a similar way, one
; can perform integer multiplication by means of repeated addition. The
; following multiplication procedure (in which it is assumed that our language
; can only add, not multiply) is analogous to the expt procedure:
;
;    (defn x [a b] 
;      (if (= b 0) 
;          0 
;          (+ a (x a (- b 1))))) 
;
; This algorithm takes a number of steps that is linear in b. Now suppose we
; include, together with addition, operations double, which doubles an
; integer, and halve, which divides an (even) integer by 2. Using these,
; design a multiplication procedure analogous to fast-expt that uses a
; logarithmic number of steps. 

(defn doubled [i]
  (+ i i))
(defn halve [i]
  (/ i 2))

(defn x [a b]
  (defn x-iter [i a b]
    (if (= b 0)
        i
        (if (even? b)
            (recur i (doubled a) (halve b))
            (recur (+ i a) a (- b 1)))))
  (x-iter 0 a b))

(x 5 7)
(x 10 10)
