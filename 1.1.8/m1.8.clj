; The following procedure power-close-to finds the smallest power of its
; first argument that is greater than its second argument. (This is a 
; solution to an exercise in manual section 1.1.7.)
;
;   (define (power-close-to b n)
;     (power-iter b n 1))
;
;   (define (power-iter b n e)
;     (if (> (expt b e) n)
;         e
;         (power-iter b n (+ e 1))))
;
; Embed the definition of power-iter inside power-close-to. Take
; advantage of lexical scoping to remove unnecessary parameters from the
; embdedded power-iter, and explain why you could remove those
; parameters.

(defn expt [n e]
  (if (= e 0)
      1
      (* n (expt n (- e 1)))))

(defn power-close-to [b n]
  (defn power-iter [e]
    (if (> (expt b e) n)
        e
        (power-iter (+ e 1))))

  (power-iter 1))

(= 10 (power-close-to 2 1000))
