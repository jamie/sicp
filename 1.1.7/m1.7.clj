; Write a procedure power-close-to that takes as arguments two positive
; integers b and n, and returns the smallest power of b that is greater
; than n. That is, it should return the smallest integer e such that 
; b^e > n. You may use the Clojure procedure **, which raises a given
; number to a given power.
;
;   (power-close-to 2 1000)
;   10
;
;   (** 2 10)
;   1024

(defn power-iter [b n e]
  (if (< n (** b e))
      e
      (power-iter b n (+ e 1))))

(defn power-close-to [b n]
  (power-iter b n 1))

(= 10 (power-close-to 2 1000))
