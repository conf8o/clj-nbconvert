(ns clj-nbconvert.file.ipynb
  (:require [clj-nbconvert.option :as option]
            [clj-nbconvert.file.utils :as file-utils]))

(defn ipynb-path? [path]
  (boolean (re-find #"\.ipynb$" path)))

(defn get-ipynb-paths
  ([dir-path]
    (->> dir-path
         clojure.java.io/file
         file-seq
         (map file-utils/to-path-string)
         (filter file-utils/not-in-hidden-dir?)
         (filter ipynb-path?))))
