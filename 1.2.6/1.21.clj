; Use the smallest-divisor procedure to find the smallest divisor of each of
; the following numbers: 199, 1999, 19999.

(defn square [x]
  (* x x))
(defn remainder [x y]
  (if (< x y)
      x
      (recur (- x y) y)))

(defn smallest-divisor [n]
  (defn divides? [a b]
    (= remainder b a) 0)
  (defn find-divisor [n test]
    (cond (> (square test) n) n
          (divides? test n) test
          :else (find-divisor n (+ test 1))))
  (find-divisor n 2))

(smallest-divisor 199)
(smallest-divisor 1999)
(smallest-divisor 19999)
