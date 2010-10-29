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
