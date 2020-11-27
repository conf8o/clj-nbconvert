(ns clj-nbconvert.file.ipynb
  (:require [clojure.java.io]
            [clj-nbconvert.file.utils :as file-utils]))

(def ipynb-pattern #"\.ipynb$")

(defn ipynb-path? [path]
  (boolean (re-find ipynb-pattern path)))

(defn get-ipynb-paths
  ([dir-path]
    (->> dir-path
         clojure.java.io/file
         file-seq
         (map file-utils/to-path-string)
         (filter file-utils/not-in-hidden-dir?)
         (filter ipynb-path?))))
