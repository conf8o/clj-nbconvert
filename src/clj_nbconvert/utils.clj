(ns clj-nbconvert.utils)

(defn maybe-comp
  "Composes functions, maybe :). The returned fn returns nil if anyone does so."
  ([f] f)
  ([f g]
    (fn
      ([] (let [a (g)]
            (when a (f a))))
      ([x & args] (let [a (apply g x args)]
                    (when a (f a))))))
  ([f g & fs]
    (reduce maybe-comp (list* f g fs))))
