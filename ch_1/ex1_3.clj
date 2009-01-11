(defn sum-largest-squares [a b c]
  (cond (and (> a c) (> b c)) (+ (* a a) (* b b))
        (and (> b a) (> c a)) (+ (* b b) (* c c))
        :else (+ (* a a) (* c c))))

(sum-largest-squares 1 2 3)
(sum-largest-squares 2 3 1)
(sum-largest-squares 3 1 2)

; Lets refactor some, I love refactoring

(defn square [x] (* x x))

(defn sum-of-squares [a b]
  (+ (square a) (square b)))

; cheating, using knowledge I shouldn't have yet :P
(defn two-max-helper [max next-max list]
  (cond (empty? list) [max next-max]
        :else (cond (> (first list) max)      (two-max-helper (first list) max (rest list))
                    (> (first list) next-max) (two-max-helper max (first list) (rest list))
                    :else                     (two-max-helper max next-max (rest list)))))
(defn two-max [list]
  (cond (> (first list) (second list))
          (two-max-helper (first list) (second list) (rest (rest list)))
        :else
          (two-max-helper (second list) (first list) (rest (rest list)))))

(defn sum-largest-squares [list]
  (apply + (map square (two-max list))))

(sum-largest-squares '(1 2 3))
(sum-largest-squares '(2 3 1))
(sum-largest-squares '(3 1 2))
