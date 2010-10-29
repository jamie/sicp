; Write a Scheme expression whose evaluation would result in an error
; if `and` were a procedure but actually will have a value because and
; is a special form. Do the same for `or`.

(defn and- [a b] (and a b))
(defn or- [a b] (or a b))

(defn loopy [] (loopy))

(assert (= false (and false (loopy))))
(assert (= (or true (loopy))))

; TODO: I'm not sure this is exactly what the question is asking
