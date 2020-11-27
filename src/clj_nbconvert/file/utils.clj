(ns clj-nbconvert.file.utils
  (:require [clojure.string :as string]
            [clj-nbconvert.option :as option]))

(def path-sep (if option/windows-os? "\\" "/"))
(def path-sep-re (if option/windows-os? #"\\" #"/"))

(defn to-path-string [file]
  (.toString (.toPath file)))

(defn not-in-hidden-dir? [path]
  (not (string/includes? path (str path-sep "."))))

(defn count-path [path] (count (string/split path path-sep-re)))

(defn drop-path [c path]
  (let [path-vec (string/split path path-sep-re)]
    (string/join path-sep (subvec path-vec c))))
