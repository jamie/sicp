; Prove that Fib(n) is the closest integer to phi^n/ sqrt5, where 
; phi = (1 + sqrt5)/2. Hint: Let psi = (1 - sqrt5)/2. Use induction and the
; definition of the Fibonacci numbers (see section 1.2.2) to prove that 
; Fib(n) = (phi^n - psi^n)/ sqrt5.

; Definition of fibonnaci:
;             0                   if n = 0
; Fib(n) = {  1                   if n = 1
;             Fib(n-1) + Fib(n-2) otherwise

(def phi (/ (+ 1 (sqrt 5)) 2))
(def psi (/ (- 1 (sqrt 5)) 2))
phi
psi

; phi^0 = 1, psi^0 = 1, 1-1 = 0, 0/sqrt5 = 0
; fib(0) = 0

; phi^1 = 1.354..., psi^1 = -0.354..., 1.354... - 0.354... = 1.709...
; sqrt(5) = 1.709...
; 1.709/1.709 = 1
; fib(1) = 1

; fib(n) = (phi^n - psi^n) / sqrt5

; (phi^n - psi^n) / sqrt5 = (phi^n-1 - psi^n-1) / sqrt5 + (phi^n-2 - psi^n-2) / sqrt5
; (phi^n - psi^n) = (phi^n-1 - psi^n-1) + (phi^n-2 - psi^n-2)
; (phi^n - psi^n) = phi^n-1 + phi^n-2 - (psi^n-1 + psi^n-2)
; (phi^n - psi^n) = (phi + 1)(phi^n-2) - (psi + 1)(psi^n-2)

; TODO
