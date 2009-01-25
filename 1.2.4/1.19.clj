; There is a clever algorithm for computing the Fibonacci numbers in a
; logarithmic number of steps. Recall the transformation of the state
; variables a and b in the fib-iter process of section 1.2.2: a <- a + b and
; b <- a. Call this transformation T, and observe that applying T over and
; over again n times, starting with 1 and 0, produces the pair Fib(n + 1) and
; Fib(n). In other words, the Fibonacci numbers are produced by applying T^n,
; the nth power of the transformation T, starting with the pair (1,0). Now
; consider T to be the special case of p = 0 and q = 1 in a family of
; transformations T_pq, where T_pq transforms the pair (a,b) according to
; a <- bq + aq + ap and b <- bp + aq. Show that if we apply such a
; transformation T_pq twice, the effect is the same as using a single
; transformation T_p'q' of the same form, and compute p' and q' in terms of p
; and q. This gives us an explicit way to square these transformations, and
; thus we can compute T^n using successive squaring, as in the fast-expt
; procedure. Put this all together to complete the following procedure, which
; runs in a logarithmic number of steps:
;
;    (defn fib [n]
;      (defn fib-iter [a b p q count]
;        (cond ((= count 0) b)
;              ((even? count)
;               (fib-iter a
;                         b
;                         <??>      ; compute p'
;                         <??>      ; compute q'
;                         (/ count 2)))
;              (else (fib-iter (+ (* b q) (* a q) (* a p))
;                              (+ (* b p) (* a q))
;                              p
;                              q
;                              (- count 1)))))
;      (fib-iter 1 0 0 1 n))

(defn fib [n]
  (defn fib-iter [a b p q count]
    (cond (= count 0) b
          (even? count) (recur a
                               b
                               p ; TODO
                               q ; TODO
                               (/ count 2))
          :else (recur (+ (* b q) (* a q) (* a p))
                       (+ (* b p) (* a q))
                       p
                       q
                       (- count 1))))
  (fib-iter 1 0 0 1 n))

(fib 1)
(fib 2)
(fib 3)
(fib 4)
(fib 5)
