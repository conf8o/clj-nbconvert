(ns clj-nbconvert.file.utils
  (:require [clojure.java.io :as io])
  (:import  [java.nio.file Path Files]
            [java.io File]))

(defmulti count-path class)
(defmethod count-path Path [path] (.getNameCount path))
(defmethod count-path File [file] (count-path (.toPath file)))
(defmethod count-path String [path] (count-path (.toPath (io/file path))))

(defn drop-path [c path]
  (.subpath path c (.getNameCount path)))

(defn is-in-hidden [file]
  (->> file
       .toPath
       .iterator
       iterator-seq
       (some #(Files/isHidden %))))
