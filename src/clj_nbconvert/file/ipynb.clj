(ns clj-nbconvert.file.ipynb
  (:require [clojure.java.io　:as io]
            [clj-nbconvert.file.utils :as file-utils]))

(def ipynb-pattern #"\.ipynb$")

(defn ipynb? [path]
  (when path
    (re-find ipynb-pattern (.toString path))))

(defn get-ipynb-files
  ([dir-path]
    (->> dir-path
         io/file
         file-seq
         (filter #(not (file-utils/in-hidden? %)))
         (filter ipynb?))))
