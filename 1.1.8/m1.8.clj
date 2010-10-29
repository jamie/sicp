; The following procedure power-close-to finds the smallest power of its
; first argument that is greater than its second argument. (This is a 
; solution to an exercise in manual section 1.1.7.)
;
;   (defn power-close-to [b n]
;     (power-iter b n 1))
;
;   (defn power-iter [b n e]
;     (if (> (** b e) n)
;         e
;         (recur b n (+ e 1))))
;
; Embed the definition of power-iter inside power-close-to. Take
; advantage of lexical scoping to remove unnecessary parameters from the
; embdedded power-iter, and explain why you could remove those
; parameters.

(defn expt [a b]
  (int (Math/pow a b)))

(defn power-close-to [b n]
  (defn power-iter [e]
    (if (> (expt b e) n)
        e
        (recur (+ e 1))))

  (power-iter 1))

(assert (= 10 (power-close-to 2 1000)))
