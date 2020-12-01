(ns clj-nbconvert.file.utils
  (:require [clojure.string :as string])
  (:import  [java.nio.file Files]
            [java.net URI]))

(defn is-in-hidden [file]
  (->> file
       .toPath
       .iterator
       iterator-seq
       (some #(= (first (.toString %)) \.))))

(defn to-uri [path]
  (URI. 
   (str "file:///"
        (-> path
            (string/replace "\\" "/")
            (string/replace " " "%20")))))