(ns clj-nbconvert.file.ipynb
  (:require [clojure.java.io]
            [clj-nbconvert.file.utils :as file-utils])
  (:import [java.nio.file Files]))

(def ipynb-pattern #"\.ipynb$")

(defn ipynb? [path]
  (boolean (re-find ipynb-pattern (.toString path))))

(defn get-ipynb-files
  ([dir-path]
    (->> dir-path
         clojure.java.io/file
         file-seq
         (filter #(not (.isHidden %)))
         (filter ipynb?))))
