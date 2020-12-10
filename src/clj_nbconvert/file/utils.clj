(ns clj-nbconvert.file.utils
  (:require [clojure.string :as string]
            [clojure.java.io :as io])
  (:import  [java.io File]
            [java.nio.file Files]
            [java.net URI]))

(defn in-hidden? [^File file]
  (when file
    (->> file
         .toPath
         .iterator
         iterator-seq
         (some #(= (first (.toString %)) \.)))))

(defn to-uri [^CharSequence path]
  (when path
    (URI. 
     (str "file:///"
          (-> path
              (string/replace "\\" "/")
              (string/replace " " "%20"))))))

(defn resource [path]
  (.toString (.getPath (io/resource path))))
