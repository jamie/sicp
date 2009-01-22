; Write a procedure power-close-to that takes as arguments two positive
; integers b and n, and returns the smallest power of b that is greater
; than n. That is, it should return the smallest integer e such that 
; b^e > n. You may use the Scheme procedure expt, which raises a given
; number to a given power.
;
;   (power-close-to 2 1000)
;   10
;
;   (expt 2 10)
;   1024

(defn expt [n e]
  (if (= e 0)
      1
      (* n (expt n (- e 1)))))

(= 4 (expt 2 2))
(= 1024 (expt 2 10))

(defn power-iter [b n e]
  (if (< n (expt b e))
      e
      (power-iter b n (+ e 1))))

(defn power-close-to [b n]
  (power-iter b n 1))

(= 10 (power-close-to 2 1000))
