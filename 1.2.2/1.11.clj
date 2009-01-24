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

(=  1 (f 1))
(=  2 (f 2))
(=  3 (f 3))
(= 10 (f 4)) ;               3 + 2*2 + 3*1
(= 22 (f 5)) ;        10 + 2*3 + 3*2
(= 51 (f 6)) ; 22 + 2*10 + 3*3
