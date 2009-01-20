(defn sum-largest-squares [a b c]
  (cond (and (< a b) (< a c)) (+ (* b b) (* c c))
        (and (< b a) (< b c)) (+ (* a a) (* c c))
        (and (< c a) (< c b)) (+ (* a a) (* b b))))

(sum-largest-squares 1 2 3)
(sum-largest-squares 2 3 1)
(sum-largest-squares 3 1 2)

; Lets refactor some, I love refactoring

;(defn sum-largest-squares [a b c]
;  (defn sum-of-squares [a b]
;    (+ (square a) (square b)))
;
;  (defn two-max [a b c]
;    (remove (fn [x] (= x (min a b c))) '(a b c)))
;
;  (sum-of-squares (two-max a b c)))
;
;(sum-largest-squares 1 2 3)
;(sum-largest-squares 2 3 1)
;(sum-largest-squares 3 1 2)


"~~~~~~~~"

(defn sum-largest-squares [list]
  (defn sum-of-squares [a b]
    (+ (square a) (square b)))

  ; cheating with empty? and list manipulation
  (defn two-max-helper [max next-max list]
    (if (empty? list)
        [max next-max]
        (cond (> (first list) max)      (two-max-helper (first list) max (rest list))
              (> (first list) next-max) (two-max-helper max (first list) (rest list))
              :else                     (two-max-helper max next-max (rest list)))))
  (defn two-max [list]
    (if (> (first list) (second list))
        (two-max-helper (first list) (second list) (rest (rest list)))
        (two-max-helper (second list) (first list) (rest (rest list)))))

  ; cheating with apply, map
  (apply + (map square (two-max list))))

(sum-largest-squares '(1 2 3))
(sum-largest-squares '(2 3 1))
(sum-largest-squares '(3 1 2))

; but real elegance winds up being
(defn sum-largest-squares [a b c]
  (apply + (map square (rest (sort (list a b c))))))

(sum-largest-squares 1 2 3)
(sum-largest-squares 2 3 1)
(sum-largest-squares 3 1 2)
