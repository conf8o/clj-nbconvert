(ns clj-nbconvert.utils)

(defn success-comp
  "Compose functions. Process continues from front until any function returns nil."
  [function & more]
  (fn [val & vals]
    (loop [x (apply function val vals)
           fs more]
      (cond
        (nil? x) nil
        (empty? fs) x
        :else (recur ((first fs) x) (next fs))))))
