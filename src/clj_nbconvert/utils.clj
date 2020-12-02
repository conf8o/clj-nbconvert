(ns clj-nbconvert.utils)

(defn maybe-comp
  "Composes functions, maybe :). The returned fn returns nil if anyone does so."
  ([f] f)
  ([f g]
    (fn
      ([] (when-let [a (g)]
            (f a)))
      ([x & args] (when-let [a (apply g x args)]
                    (f a)))))
  ([f g & fs]
    (reduce maybe-comp (list* f g fs))))
