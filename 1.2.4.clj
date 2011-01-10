;;; 1.16

; Design a procedure that evolves an iterative exponentiation process that
; uses successive squaring and uses a logarithmic number of steps, as does
; fast-expt. (Hint: Using the observation that (b^n/2)^2 = (b^2)^n/2, keep,
; along with the exponent n and the base b, an additional state variable a,
; and define the state transformation in such a way that the product a b^n is
; unchanged from state to state. At the beginning of the process a is taken to
; be 1, and the answer is given by the value of a at the end of the process.
; In general, the technique of defining an invariant quantity that remains
; unchanged from state to state is a powerful way to think about the design of
; iterative algorithms.) 

(defn exp [b n]
  (defn exp-iter [a b e]
    (if (= e 0)
        a
        (if (even? e)
            (recur a (* b b) (/ e 2))
            (recur (* a b) b (- e 1)))))
  (exp-iter 1 b n))

(assert (= 4  (exp 2 2)))
(assert (= 8  (exp 2 3)))
(assert (= 16 (exp 2 4)))

(assert (= 1024 (exp 2 10)))
(assert (= 100 (exp 10 2)))


;;; 1.17

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

(assert (= 35  (fast-mul 5 7)))
(assert (= 100 (fast-mul 10 10)))


;;; 1.18

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


;;; 1.19

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

(assert (= 0 (fib 0)))
(assert (= 1 (fib 1)))
(assert (= 1 (fib 2)))
(assert (= 2 (fib 3)))
(assert (= 3 (fib 4)))
(assert (= 5 (fib 5)))


;;; IM 1.12

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

(assert (= 1024 (expt 2 10)))

; We can remove b, as it remains unchanged between calls to expt-iter.


;;; IM 1.13

; a) Write a procedure nmult that takes one argument, a positive integer, and
;    produces the number of multiplications required to raise something to
;    that power using fast-expt.
; b) Can you determine a more concise description of the function computed by
;    the procedure nmult than the program itself?
; c) Show that for n > 1, (nmult n) <= 3 log2(n).

; TODO


