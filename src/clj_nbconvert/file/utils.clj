(ns clj-nbconvert.file.utils
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clj-nbconvert.option :as option])
   (:import  [java.nio.file Path]))

(defmulti count-path class)
(defmethod count-path Path [path] (.getNameCount path))
(defmethod count-path String [path] (count-path (.toPath (io/file path))))

(defn drop-path [c path]
  (.subpath path c (.getNameCount path)))
