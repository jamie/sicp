;;; 1.11

; A function f is defined by the rule that f(n) = n if n<3 and
; f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n>= 3. Write a procedure that
; computes f by means of an iterative process. 

; recursive
(defn f [n]
  (if (< n 3)
      n
      (+ (f (- n 1))
         (* 2 (f (- n 2)))
         (* 3 (f (- n 3))) )))

; iterative translation
(defn f [n]
  (defn iter [i n1 n2 n3]
    (if (> i n)
        n1
        (recur (+ i 1) (+ n1 (* 2 n2) (* 3 n3)) n1 n2)))
  (if (< n 3)
      n
      (iter 4 3 2 1)))

(assert (=  1 (f 1)))
(assert (=  2 (f 2)))
(assert (=  3 (f 3)))
(assert (= 10 (f 4))) ;               3 + 2*2 + 3*1
(assert (= 22 (f 5))) ;        10 + 2*3 + 3*2
(assert (= 51 (f 6))) ; 22 + 2*10 + 3*3


;;; 1.12

; The following pattern of numbers is called Pascal's triangle. 
;     1
;    1 1
;   1 2 1
;  1 3 3 1
; 1 4 6 4 1
; The numbers at the edge of the triangle are all 1, and each number inside
; the triangle is the sum of the two numbers above it. Write a procedure
; that computes elements of Pascal's triangle by means of a recursive process. 

(defn pascal [row col]
  (cond (= 1 row) 1
        (= 1 col) 1
        (= row col) 1
        :else (+ (pascal (- row 1) col) 
                 (pascal (- row 1) (- col 1)))))

(assert (= 1 (pascal 1 1)))

(assert (= 1 (pascal 2 1)))
(assert (= 1 (pascal 2 2)))

(assert (= 1 (pascal 3 1)))
(assert (= 2 (pascal 3 2)))
(assert (= 1 (pascal 3 3)))

(assert (= 1 (pascal 4 1)))
(assert (= 3 (pascal 4 2)))
(assert (= 3 (pascal 4 3)))
(assert (= 1 (pascal 4 4)))


;;; 1.13

; Prove that Fib(n) is the closest integer to phi^n/ sqrt5, where 
; phi = (1 + sqrt5)/2. Hint: Let psi = (1 - sqrt5)/2. Use induction and the
; definition of the Fibonacci numbers (see section 1.2.2) to prove that 
; Fib(n) = (phi^n - psi^n)/ sqrt5.
; 
; Definition of fibonnaci:
;             0                   if n = 0
; Fib(n) = {  1                   if n = 1
;             Fib(n-1) + Fib(n-2) otherwise

(defn ** [n e] (Math/pow n e))
(defn sqrt [n] (Math/sqrt n))
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


;;; IM 1.10

; Use block structure to hide the definition of fib-iter inside fib. Does
; lexical scoping allow you to remove any parameters from the embedded
; fib-iter?
;
;    (defn fib-iter [a b count]
;      (if (= count 0)
;          b
;          (fib-iter (+ a b) a (- count 1))))
;
;    (defn fib [n]
;      (fib-iter 1 0 n))

(defn fib [n]
  (defn fib-iter [a b count]
    (if (= count 0)
        b
        (fib-iter (+ a b) a (- count 1))))
  (fib-iter 1 0 n))

(assert (= 3 (fib 4)))
(assert (= 55 (fib 10)))
(assert (= 610 (fib 15)))

; Cannot remove any parameters from fib-iter, because none stay constant
; between calls


;;; IM 1.11

; Use block structure to hide the definitions of cc and first-denomination
; inside count-change. Does lexical scoping allow you to remove any parameters
; from the embedded procedures?
;
;    (defn first-denomination [kinds-of-coins]
;      (cond (= kinds-of-coins 1) 1
;            (= kinds-of-coins 2) 5
;            (= kinds-of-coins 3) 10
;            (= kinds-of-coins 4) 25
;            (= kinds-of-coins 5) 50))
;
;    (defn cc [amount kinds-of-coins]
;      (cond (= amount 0) 1
;            (or (< amount 0) (= kinds-of-coins 0)) 0
;            :else (+ (cc amount
;                         (- kinds-of-coins 1))
;                     (cc (- amount (first-denomination kinds-of-coins))
;                         kinds-of-coins))))
;
;    (defn count-change [amount]
;      (cc amount 5))

(defn count-change [amount]
  (defn first-denomination [kinds-of-coins]
    (cond (= kinds-of-coins 1) 1
          (= kinds-of-coins 2) 5
          (= kinds-of-coins 3) 10
          (= kinds-of-coins 4) 25
          (= kinds-of-coins 5) 50))
  (defn cc [amount kinds-of-coins]
    (cond (= amount 0) 1
          (or (< amount 0) (= kinds-of-coins 0)) 0
          :else (+ (cc amount
                       (- kinds-of-coins 1))
                   (cc (- amount (first-denomination kinds-of-coins))
                       kinds-of-coins))))
  (cc amount 5))

(assert (= 292 (count-change 100)))

; No, values of both amount and kinds-of-coins change between calls to cc,
; and kinds-of-coins is variable in calls to first-denomination.


