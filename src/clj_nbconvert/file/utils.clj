(ns clj-nbconvert.file.utils
  (:require [clojure.string :as string])
  (:import  [java.nio.file Files]
            [java.net URI]))

(defn in-hidden? [file]
  (when file
    (->> file
         .toPath
         .iterator
         iterator-seq
         (some #(= (first (.toString %)) \.)))))

(defn to-uri [^String path]
  (when path
    (URI. 
     (str "file:///"
          (-> path
              (string/replace "\\" "/")
              (string/replace " " "%20"))))))