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
