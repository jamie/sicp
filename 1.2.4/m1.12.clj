; Use block structure to hide the definition of expt-iter inside expt. Does
; lexical scoping allow you to remove any parameters from the embedded
; expt-iter?
;
;    (defn expt [b n]
;      (expt-iter b n 1))
;    (defn expt-iter [b counter product]
;      (if (= counter 0)
;          product
;          (expt-iter b
;                     (- counter 1)
;                     (* b product))))

(defn expt [b n]
  (defn expt-iter [counter product]
    (if (= counter 0)
        product
        (recur (- counter 1) (* b product))))
  (expt-iter n 1))

(expt 2 10)

; We can remove b, as it remains unchanged between calls to expt-iter.
