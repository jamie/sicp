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
