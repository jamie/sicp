; Prove that Fib(n) is the closest integer to phi^n/ sqrt5, where 
; phi = (1 + sqrt5)/2. Hint: Let psi = (1 - sqrt5)/2. Use induction and the
; definition of the Fibonacci numbers (see section 1.2.2) to prove that 
; Fib(n) = (phi^n - psi^n)/ sqrt5.
; 
; Definition of fibonnaci:
;             0                   if n = 0
; Fib(n) = {  1                   if n = 1
;             Fib(n-1) + Fib(n-2) otherwise

(defn ** [n e] (.pow Math n e))
(defn sqrt [n] (.sqrt Math n))
(def phi (/ (+ 1 (sqrt 5)) 2))
(def psi (/ (- 1 (sqrt 5)) 2))

; Firstly, note that:
; phi^2 = 1 + phi
(= (* phi phi) (+ 1 phi))
; psi^2 = 1 + psi
(= (* psi psi) (+ 1 psi 0.0000000000000001)) ; rounding error

; So, we define Fib(n) as:
; Fib(n) = (phi^n - psi^n) / sqrt5
(defn fib [n]
  (/ (- (** phi n)
        (** psi n))
     (sqrt 5)))

; Given Fib(1) = Fib(2) = 1
(= 1.0 (fib 1))
(= 1.0 (fib 2))

; Assuming our definition of Fib(n) holds for 1..n-1,
; Fib(n) = Fib(n-1) + Fib(n-2)
; Fib(n) = (phi^n-1 - psi^n-1) / sqrt5 + (phi^n-2 - psi^n-2) / sqrt5
; Fib(n) = (phi^n-1 + phi^n-2 - psi^n-1 - psi^n-2) / sqrt5
; Fib(n) = ((phi + 1)(phi^n-2) - (psi + 1)(psi^n-2)) / sqrt5
; using above identities
; Fib(n) = ((phi^2)(phi^n-2) - (psi^2)(psi^n-2)) / sqrt5
; Fib(n) = (phi^n - psi^n) / sqrt5
;
; So, if Fib(i) holds for i<n, it holds for Fib(n)
