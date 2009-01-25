; The exponentiation algorithms in this section are based on performing
; exponentiation by means of repeated multiplication. In a similar way, one
; can perform integer multiplication by means of repeated addition. The
; following multiplication procedure (in which it is assumed that our language
; can only add, not multiply) is analogous to the expt procedure:
;
;    (defn mul [a b] 
;      (if (= b 0) 
;          0 
;          (+ a (mul a (- b 1))))) 
;
; This algorithm takes a number of steps that is linear in b. Now suppose we
; include, together with addition, operations double, which doubles an
; integer, and halve, which divides an (even) integer by 2. Using these,
; design a multiplication procedure analogous to fast-expt that uses a
; logarithmic number of steps. 

;     (defn fast-expt [b n]
;       (cond (= n 0) 1
;             (even? n) (square (fast-expt b (/ n 2)))
;             :else (* b (fast-expt b (- n 1)))))

(defn doubled [i]
  (+ i i))
(defn halve [i]
  (/ i 2))

(defn fast-mul [x y]
  (cond (= y 1) x
        (even? y) (doubled (fast-mul x (halve y)))
        :else (+ x (fast-mul x (- y 1)))))

(fast-mul 5 7)
(fast-mul 10 10)
