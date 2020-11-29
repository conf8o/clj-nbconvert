(ns clj-nbconvert.file.utils
  (:require [clojure.string :as string])
  (:import  [java.nio.file Files]
            [java.net URI]))

(defn is-in-hidden [file]
  (->> file
       .toPath
       .iterator
       iterator-seq
       (some #(Files/isHidden %))))

(defn to-uri [path]
  (-> path
      (string/replace " " "%20")
      URI.))